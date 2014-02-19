package com.luiges90.sq;

import java.io.Serializable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public abstract class MovingEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4476731801045304088L;
    private Vector velocity;
    private Vector position;

    private int lifetime;

    private int kills = 0;

    private boolean destroyed = false;
    private boolean invincible = false;

    private transient Paint drawPaint;

    protected final void initPaint(int color) {
        drawPaint = new Paint();
        drawPaint.setColor(color);
        drawPaint.setStyle(Paint.Style.FILL);
    }

    protected MovingEntity(Vector velocity, Vector position, int lifetime) {
        this.velocity = velocity;
        this.position = position;
        this.lifetime = lifetime;
    }

    protected MovingEntity(Vector velocity, Vector position) {
        this(velocity, position, -1);
    }

    protected void onFrameUpdate(GameField field) {
        this.position = Vector.add(this.position, this.velocity);
        if (this.position.x < -GameField.FIELD_SIZE) {
            this.position = new Vector(this.position.x + GameField.FIELD_SIZE * 2, this.position.y);
        }
        if (this.position.x > GameField.FIELD_SIZE) {
            this.position = new Vector(this.position.x - GameField.FIELD_SIZE * 2, this.position.y);
        }
        if (this.position.y < -GameField.FIELD_SIZE) {
            this.position = new Vector(this.position.x, this.position.y + GameField.FIELD_SIZE * 2);
        }
        if (this.position.y > GameField.FIELD_SIZE) {
            this.position = new Vector(this.position.x, this.position.y - GameField.FIELD_SIZE * 2);
        }

        if (this.lifetime > 0) {
            this.lifetime--;
            if (this.lifetime <= 0) {
                this.destroyed = true;
            }
        }
    }

    protected void onHit(GameField field) {
        // do nothing
    }

    protected void onDestroy(GameField field) {
        // do nothing
    }

    protected void respawn() {
        this.destroyed = false;
    }

    protected boolean doDestroy(GameField field) {
        return true;
    }

    public boolean destroy(GameField field) {
        this.onHit(field);
        if (!this.destroyed && !this.invincible && doDestroy(field)) {
            this.destroyed = true;
            this.onDestroy(field);
            return true;
        }
        return false;
    }

    public void applyForce(Vector force) {
        this.velocity = Vector.add(this.velocity, force);
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public abstract void draw(Canvas canvas, int actualCanvasSize);

    public Vector getVelocity() {
        return velocity;
    }

    public Vector getPosition() {
        return position;
    }

    public int getLifetime() {
        return lifetime;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    protected void setInvincible(boolean invincible) {
        this.invincible = invincible;
    }

    public boolean isInvincible() {
        return invincible;
    }

    public int getKills() {
        return kills;
    }

    public void scoreKill() {
        kills++;
    }

    public int getColor() {
        return drawPaint.getColor();
    }

    public void setColor(int color) {
        drawPaint.setColor(color);
    }

    public void setHue(float h) {
        int oldAlpha = drawPaint.getAlpha();
        float[] hsv = new float[3];
        Color.colorToHSV(getColor(), hsv);
        hsv[0] = h;
        setColor(Color.HSVToColor(hsv));
        this.setAlpha(oldAlpha);
    }

    public void setSat(float s) {
        int oldAlpha = drawPaint.getAlpha();
        float[] hsv = new float[3];
        Color.colorToHSV(getColor(), hsv);
        hsv[1] = s;
        setColor(Color.HSVToColor(hsv));
        this.setAlpha(oldAlpha);
    }

    public void setVal(float v) {
        int oldAlpha = drawPaint.getAlpha();
        float[] hsv = new float[3];
        Color.colorToHSV(getColor(), hsv);
        hsv[2] = v;
        setColor(Color.HSVToColor(hsv));
        this.setAlpha(oldAlpha);
    }

    public void setAlpha(int a) {
        drawPaint.setAlpha(a);
    }

    protected final Paint getDrawPaint() {
        return drawPaint;
    }

}
