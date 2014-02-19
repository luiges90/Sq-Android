package com.luiges90.sq;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;

import com.luiges90.sq.enemy.BaseEnemy;
import com.luiges90.sq.wave.StaticWave;

public class GameField implements Serializable {

    private static final int START_WAVE = 0;
    public static final boolean DEBUG = START_WAVE > 0;

    private static final long serialVersionUID = -3382715172166301763L;

    public static final int FIELD_SIZE = 300; // in all 4 directions

    private Player player;
    private List<PlayerBullet> playerBullet;

    private List<BaseEnemy> enemy;
    private List<BaseEnemy> enemiesToAdd = new ArrayList<BaseEnemy>();

    private int wave;
    private int mode;
    private int addLifeEvery = Integer.MAX_VALUE;

    private boolean drawDetails;

    private transient Paint livesPaint, scorePaint, wavePaint;
    private int textHeight;

    private transient Context context;
    private transient SoundPlayer soundPlayer;

    public void setContext(Context context) {
        this.context = context;
        this.soundPlayer = new SoundPlayer(context);
    }

    public void setGameOverListener(GameOverListener gol) {
        this.player.setGameOverListener(gol);
    }

    public void setDrawDetails(boolean drawDetails) {
        this.drawDetails = drawDetails;
    }

    private final void initPaint() {
        livesPaint = new Paint();
        livesPaint.setColor(Color.WHITE);
        livesPaint.setStyle(Style.FILL);
        livesPaint.setTextSize(36);
        livesPaint.setTextAlign(Align.LEFT);
        livesPaint.setAntiAlias(true);

        scorePaint = new Paint(livesPaint);
        scorePaint.setTextAlign(Align.RIGHT);

        Rect r = new Rect();
        livesPaint.getTextBounds("1", 0, 1, r);
        textHeight = r.bottom - r.top;

        wavePaint = new Paint();
        wavePaint.setColor(Color.DKGRAY);
        wavePaint.setStyle(Style.FILL);
        wavePaint.setTextSize(192);
        wavePaint.setTextAlign(Align.CENTER);
        wavePaint.setAntiAlias(true);
    }

    public GameField(Context context, int mode) {
        this.context = context;
        this.soundPlayer = new SoundPlayer(context);

        initPaint();

        reset(0);
    }

    public void setWave(int wave) {
        this.wave = wave;
        startWave();
    }

    public void setPlayerLives(int playerLives) {
        this.player.setLives(playerLives);
    }

    public void setAddLifeEvery(int wave) {
        this.addLifeEvery = wave;
    }

    public final void reset(int playerLives) {
        this.player = new Player();
        this.player.setLives(playerLives);

        this.playerBullet = new ArrayList<PlayerBullet>();

        this.wave = START_WAVE;

        startNextWave();
    }

    public final void resetWave(int playerLives) {
        this.player = new Player();
        this.player.setLives(playerLives);

        this.playerBullet = new ArrayList<PlayerBullet>();

        startWave();
    }

    private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        initPaint();
    }

    private void startWave() {
        if (wave % addLifeEvery == 0) {
            player.addLife();
        }
        enemy = (new StaticWave()).createWave(this);
    }

    private void startNextWave() {
        wave++;
        Progress.instance(context, mode).advanceLevel(wave);
        startWave();
    }

    private void checkComplete() {
        for (BaseEnemy e : enemy) {
            if (!e.isBullet()) {
                return;
            }
        }

        Stat.getInstance(context).addCleared(wave);
        startNextWave();
    }

    private void destroyPlayer(BaseEnemy killer) {
        if (!player.isDestroyed() && !player.isInvincible()) {
            player.destroy(this);

            BaseEnemy scorer = killer;
            while (scorer.getParent() != null) {
                scorer = scorer.getParent();
            }
            scorer.scoreKill();
            Stat.getInstance(context).addBeKilled(scorer);
            Stat.getInstance(context).addDieInWave(wave);
        }
    }

    private void destroyEnemy(BaseEnemy e) {
        boolean destroyed = e.destroy(this);
        if (destroyed && !e.isBullet()) {
            player.scoreKill();
            Stat.getInstance(context).addKilled(e);
        }
    }

    private void detectCollision() {
        for (BaseEnemy e : enemy) {
            Vector ppos = player.getPosition();
            Vector epos = e.getPosition();
            int eSize15 = e.getSize() * 3 / 2;
            if (ppos.x >= epos.x - eSize15
                    && ppos.x <= epos.x + eSize15
                    && ppos.y >= epos.y - eSize15
                    && ppos.y <= epos.y + eSize15) {
                if (Utility.isPointInSquare(ppos, e.getPosition(),
                        e.getSize(), e.getRotation())) {
                    destroyPlayer(e);
                }
            }
            for (PlayerBullet b : playerBullet) {
                Vector bpos = b.getPosition();
                if (bpos.x >= epos.x - eSize15
                        && bpos.x <= epos.x + eSize15
                        && bpos.y >= epos.y - eSize15
                        && bpos.y <= epos.y + eSize15) {
                    if (Utility.isPointInSquare(b.getPosition(), e.getPosition(),
                            e.getSize(), e.getRotation())) {
                        destroyEnemy(e);
                        continue;
                    }
                }
            }
        }

    }

    public SoundPlayer getSoundPlayer() {
        return soundPlayer;
    }

    public void onFrameUpdate() {
        player.onFrameUpdate(this);

        Iterator<PlayerBullet> pbIterator = playerBullet.iterator();
        while (pbIterator.hasNext()) {
            PlayerBullet bullet = pbIterator.next();
            if (!bullet.isDestroyed()) {
                bullet.onFrameUpdate(this);
            } else {
                pbIterator.remove();
            }
        }

        Iterator<BaseEnemy> eIterator = enemy.iterator();
        while (eIterator.hasNext()) {
            BaseEnemy enemy = eIterator.next();
            if (!enemy.isDestroyed()) {
                enemy.onFrameUpdate(this);
            } else {
                eIterator.remove();
            }
        }

        enemy.addAll(enemiesToAdd);
        enemiesToAdd.clear();

        detectCollision();
        checkComplete();
    }

    public void draw(Canvas canvas) {
        draw(canvas, canvas.getWidth());
    }

    public void draw(Canvas canvas, int actualCanvasSize) {
        if (drawDetails) {
            canvas.drawText(context.getResources().getString(R.string.lives) + player.getLives(),
                    0, textHeight, livesPaint);

            canvas.drawText(context.getResources().getString(R.string.score) + player.getKills(),
                    actualCanvasSize, textHeight, scorePaint);
        }

        wavePaint.setTextSize(Vector.toDevice(192, actualCanvasSize));
        int xCenter = (actualCanvasSize / 2);
        int yCenter = (int) ((actualCanvasSize / 2) - ((wavePaint.descent() + wavePaint.ascent()) / 2));
        canvas.drawText(Integer.toString(wave), xCenter, yCenter, wavePaint);

        for (BaseEnemy e : enemy) {
            if (!e.isDestroyed()) {
                e.draw(canvas, actualCanvasSize);
            }
        }

        for (PlayerBullet bullet : playerBullet) {
            if (!bullet.isDestroyed()) {
                bullet.draw(canvas, actualCanvasSize);
            }
        }

        if (!player.isDestroyed()) {
            player.draw(canvas, actualCanvasSize);
        }
    }

    public void addPlayerBullet(PlayerBullet b) {
        playerBullet.add(b);
    }

    public void addEnemy(BaseEnemy e) {
        enemiesToAdd.add(e);
    }

    public int getWave() {
        return wave;
    }

    public int getScore() {
        return player.getKills();
    }

    public List<PlayerBullet> getPlayerBullets() {
        return playerBullet;
    }

    public Vector getPlayerPosition() {
        return player.getPosition();
    }

    public boolean isPlayerDestroyed() {
        return player.isDestroyed();
    }

    public void setPlayerPosition(Vector p) {
        player.setPosition(p);
    }

    public void playerFire(Vector at) {
        player.fire(this, at);
    }

    public List<BaseEnemy> getAllEnemies() {
        return enemy;
    }

}
