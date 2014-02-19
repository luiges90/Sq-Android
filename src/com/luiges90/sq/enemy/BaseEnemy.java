package com.luiges90.sq.enemy;

import java.io.IOException;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.RectF;

import com.luiges90.sq.GameField;
import com.luiges90.sq.GameView;
import com.luiges90.sq.MovingEntity;
import com.luiges90.sq.R;
import com.luiges90.sq.Utility;
import com.luiges90.sq.Vector;
import com.luiges90.sq.behaviour.Behaviour;
import com.luiges90.sq.behaviour.OnDestroyBehaviour;
import com.luiges90.sq.behaviour.OnFrameBehaviour;

public abstract class BaseEnemy extends MovingEntity {

    private static final long serialVersionUID = -4625238211828271393L;

    private static final int AVOID_RADIUS = 100;

    protected static final int DEFAULT_SIZE = 18;
    protected static final int DEFAULT_SPEED = 5;
    protected static final int DEFAULT_BULLET_SPEED = 6;
    protected static final int DEFAULT_FIRING_RATE = GameView.FPS * 3 / 2;

    private int size;
    private int drawColor;

    private transient RectF drawRect = new RectF();

    private List<Behaviour> behaviours;

    private BaseEnemy parent = null;
    private boolean bullet;

    private static final Vector initVelocity(float speed) {
        float angle = Utility.randomAngle();
        return new Vector((float) (speed * Math.cos(angle)),
                (float) (speed * Math.sin(angle)));
    }

    public static final Vector initPosition(Vector avoid) {
        Vector result;
        do {
            result = new Vector(Utility.randBetween(-GameField.FIELD_SIZE, GameField.FIELD_SIZE),
                    Utility.randBetween(-GameField.FIELD_SIZE, GameField.FIELD_SIZE));
        } while (Vector.distanceSquared(result, avoid) < AVOID_RADIUS * AVOID_RADIUS);
        return result;
    }

    protected BaseEnemy(Vector position, float speed, int size, int drawColor,
            List<Behaviour> behaviours) {
        super(initVelocity(speed), position);
        this.size = size;
        this.drawColor = drawColor;
        this.behaviours = behaviours;
        initPaint(drawColor);
    }

    protected BaseEnemy(Vector position, float speed, int size, int lifetime, int drawColor,
            List<Behaviour> behaviours) {
        super(initVelocity(speed), position, lifetime);
        this.size = size;
        this.drawColor = drawColor;
        this.behaviours = behaviours;
        initPaint(drawColor);
    }

    protected BaseEnemy(Vector position, float speed, int size, int drawColor, boolean bullet,
            BaseEnemy owner, List<Behaviour> behaviours) {
        this(position, speed, size, drawColor, behaviours);
        this.bullet = bullet;
        this.parent = owner;
    }

    protected BaseEnemy(Vector position, float speed, int size, int lifetime, int drawColor,
            boolean bullet, BaseEnemy owner, List<Behaviour> behaviours) {
        this(position, speed, size, lifetime, drawColor, behaviours);
        this.bullet = bullet;
        this.parent = owner;
    }

    private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        initPaint(drawColor);
        drawRect = new RectF();
    }

    public List<Behaviour> getAllBehaviours() {
        return behaviours;
    }

    public boolean doDestroy(GameField field) {
        boolean doDestroy = true;
        for (Behaviour b : behaviours) {
            if (b instanceof OnDestroyBehaviour) {
                doDestroy = doDestroy && ((OnDestroyBehaviour) b).isDestroyed(this, field);
            }
        }
        return doDestroy;
    }

    public void onHit(GameField field) {
        super.onHit(field);
        for (Behaviour b : behaviours) {
            if (b instanceof OnDestroyBehaviour) {
                ((OnDestroyBehaviour) b).onHit(this, field);
            }
        }
        field.getSoundPlayer().playSound(R.raw.hit);
    }

    public void onDestroy(GameField field) {
        super.onDestroy(field);
        for (Behaviour b : behaviours) {
            if (b instanceof OnDestroyBehaviour) {
                ((OnDestroyBehaviour) b).onDestroy(this, field);
            }
        }
        field.getSoundPlayer().playSound(bullet ? R.raw.bullet_destroy : R.raw.destroy);
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public float getRotation() {
        return -this.getVelocity().angle;
    }

    public void draw(Canvas canvas, int actualCanvasSize) {
        Vector devicePos = Vector.toDevicePosition(this.getPosition(), actualCanvasSize);
        float drawSize = Vector.toDevice(size, actualCanvasSize);
        float rotation = (float) Math.toDegrees(getRotation());

        canvas.save();

        canvas.rotate(rotation, devicePos.x, devicePos.y);

        drawRect.set(devicePos.x - drawSize, devicePos.y - drawSize,
                devicePos.x + drawSize, devicePos.y + drawSize);
        canvas.drawRect(drawRect, getDrawPaint());

        canvas.restore();
    }

    public void onFrameUpdate(GameField field) {
        super.onFrameUpdate(field);
        for (Behaviour b : behaviours) {
            if (b instanceof OnFrameBehaviour) {
                ((OnFrameBehaviour) b).onFrameUpdate(this, field);
            }
        }
    }

    public BaseEnemy getParent() {
        return parent;
    }

    public int getColor() {
        return this.drawColor;
    }

    public void setColor(int color) {
        this.drawColor = color;
        super.setColor(color);
    }

    public boolean isBullet() {
        return bullet;
    }

}
