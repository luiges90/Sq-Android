package com.luiges90.sq;

import java.io.IOException;



import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class PlayerBullet extends MovingEntity {
    
    /**
     * 
     */
    private static final long serialVersionUID = 2872068693271987041L;
    public static final float BULLET_SPEED = 12;
    public static final int BULLET_LIFETIME = GameView.FPS / 2;
    
    public static final int DRAW_SIZE = 9;
    
    private transient Paint drawPaint;
    
    private void initPaint() {
        drawPaint = new Paint();
        drawPaint.setColor(Color.GRAY);
        drawPaint.setStyle(Paint.Style.FILL);
    }
    
    private static final Vector initSpeed(Vector from, Vector to) {
        return Vector.fromTo(from, to).normalize().scale(BULLET_SPEED);
    }
    
    public PlayerBullet(Vector from, Vector to) {
        super(initSpeed(from, to), from, BULLET_LIFETIME);
        initPaint();
    }
    
    private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        initPaint();
    }

    @Override
    public void draw(Canvas canvas, int actualCanvasSize) {
        Vector devicePos = Vector.toDevicePosition(this.getPosition(), actualCanvasSize);
        float drawSize = Vector.toDevice(DRAW_SIZE, actualCanvasSize);
        canvas.drawCircle(devicePos.x, devicePos.y, drawSize, drawPaint);
    }

}
