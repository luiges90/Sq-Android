package com.luiges90.sq;

import java.io.IOException;

import android.graphics.Canvas;
import android.graphics.Color;

public class Player extends MovingEntity {

    /**
     * 
     */
    private static final long serialVersionUID = -5846821385299359555L;
    public static final int RESPAWN_TIME = GameView.FPS * 4;
    public static final int RESPAWN_INVINCIBLE_TIME = GameView.FPS * 4;
    
    public static final int DRAW_SIZE = 18;
    
    public static final int FIRE_COOLDOWN_TIME = GameView.FPS / 15;
    
    public static final int MAX_SPEED = 10;
    
    private int respawnCountdown;
    private int invincibleCountdown;
    
    private int fireCountdown;
    
    private int lives = 0;
    
    private transient GameOverListener gameOver;

    public final void setGameOverListener(GameOverListener gol) {
        gameOver = gol;
    }

    public Player() {
        super(new Vector(0, 0), new Vector(0, 0));
        initPaint(Color.GRAY);
    }
    
    private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        initPaint(Color.GRAY);
    }
    
    public void onFrameUpdate(GameField field) {
        super.onFrameUpdate(field);

        if (this.respawnCountdown > 0) {
            this.respawnCountdown--;
            if (this.respawnCountdown <= 0) {
                if (this.lives > 0) {
                    this.respawn();
                    this.setInvincible(true);
                    this.invincibleCountdown = RESPAWN_INVINCIBLE_TIME;
                } else {
                    gameOver.onGameOver(field);
                }
            }
        }
        
        if (this.invincibleCountdown > 0) {
            this.invincibleCountdown--;
            if (this.invincibleCountdown <= 0) {
                this.setInvincible(false);
            }
        }
        
        this.fireCountdown--;
    }
    
    public void onDestroy(GameField field) {
        super.onDestroy(field);
        field.getSoundPlayer().playSound(R.raw.player_destroy);
        this.lives--;
        this.respawnCountdown = RESPAWN_TIME;
    }
    
    public void draw(Canvas canvas, int actualCanvasSize) {
        if (this.invincibleCountdown > 0 && 
                (this.invincibleCountdown / (GameView.FPS / 4)) % 2 == 0) {
            setAlpha(0);
        } else {
            setAlpha(255);
        }
        
        Vector devicePos = Vector.toDevicePosition(this.getPosition(), actualCanvasSize);
        float drawSize = Vector.toDevice(DRAW_SIZE, actualCanvasSize);
        canvas.drawCircle(devicePos.x, devicePos.y, drawSize, getDrawPaint());
    }
    
    public void fire(GameField f, Vector at) {
        if (this.fireCountdown < 0) {
            this.fireCountdown = FIRE_COOLDOWN_TIME;
            
            PlayerBullet bullet = new PlayerBullet(this.getPosition(), at);
            f.addPlayerBullet(bullet);
            f.getSoundPlayer().playSound(R.raw.player_fire);
        }
    }
    
    public int getLives() {
        return lives;
    }
    
    public void addLife() {
        lives++;
    }
    
    public void setLives(int lives) {
        this.lives = lives;
    }

}
