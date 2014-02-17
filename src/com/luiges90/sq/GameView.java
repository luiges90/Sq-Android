package com.luiges90.sq;

import java.io.Serializable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    
    public static final int FPS = 60;
    
    private GameField field;
    private Vector firingAt = new Vector(Utility.randBetween(-GameField.FIELD_SIZE, GameField.FIELD_SIZE), 
            Utility.randBetween(-GameField.FIELD_SIZE, GameField.FIELD_SIZE));

    class GameThread extends Thread {
        private SurfaceHolder holder;

        private boolean paused = false;
        private boolean running = false;
        
        private long lastUpdate = System.currentTimeMillis();

        private Object runLock = new Object();
        
        private Vector touching = null;

        public GameThread(SurfaceHolder holder) {
            this.holder = holder;
        }

        public void run() {
            while (running) {
                Canvas c = null;
                try {
                    c = holder.lockCanvas(null);
                    synchronized (holder) {
                        synchronized (runLock) {
                            if (running) {
                                doDraw(c);
                            }
                        }
                        if (System.currentTimeMillis() - lastUpdate >= 1000 / FPS) {
                            onFrameUpdate();
                            lastUpdate = System.currentTimeMillis();
                        }
                    }
                } finally {
                    if (c != null) {
                        holder.unlockCanvasAndPost(c);
                    }
                }
            }
        }
        
        private void handleTouching() {
            if (field.isPlayerDestroyed()) {
                return;
            }
            
            if (touching != null) {
                Vector playerPos = field.getPlayerPosition();
                int fireThreshold = 30;
                if (Vector.distanceSquared(playerPos, touching) < fireThreshold * fireThreshold) {
                    field.setPlayerPosition(touching);
                } else {
                    firingAt = touching;
                }
            }
            
            field.playerFire(firingAt);
        }

        private boolean onTouch(MotionEvent event) {
            if (paused) {
                return false;
            }
            
            synchronized (holder) {
                int mask = event.getActionMasked();
                int canvasSize = GameView.this.getWidth();
                if (mask == MotionEvent.ACTION_DOWN || mask == MotionEvent.ACTION_MOVE) {
                    int id = event.getPointerId(event.getActionIndex());

                    float x = event.getX(id);
                    float y = event.getY(id);
                    if (x < 0) {
                        x = 0;
                    }
                    if (y < 0) {
                        y = 0;
                    }
                    if (x > canvasSize) {
                        x = canvasSize;
                    }
                    if (y > canvasSize) {
                        y = canvasSize;
                    }

                    touching = Vector.fromDevicePosition(x, y, canvasSize);
                } else if (mask == MotionEvent.ACTION_CANCEL || mask == MotionEvent.ACTION_UP) {
                    touching = null;
                }
            }
            return true;
        }

        private void pause() {
            synchronized (holder) {
                this.paused = true;
            }
        }
        
        private void unpause() {
            synchronized (holder) {
                this.paused = false;
            }
        }

        public void setRunning(boolean running) {
            synchronized (runLock) {
                this.running = running;
            }
        }

        private void doDraw(Canvas canvas) {
            canvas.drawColor(Color.BLACK);
            field.draw(canvas);
        }

        private void onFrameUpdate() {
            if (!paused) {
                handleTouching();
                field.onFrameUpdate();
            }
        }
    }

    private GameThread thread;

    private final void init(final Context context) {
        field = new GameField(context, 
                context.getClass().getSimpleName() == "GameStandardActivity" ? 
                        Progress.MODE_STANDARD : Progress.MODE_SURVIVAL);
        
        SurfaceHolder holder = this.getHolder();
        holder.addCallback(this);

        setFocusable(true);
    }

    public GameField getField() {
        return field;
    }

    public GameView(Context context) {
        super(context);
        init(context);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }
    
    public void pause() {
        thread.pause();
    }
    
    public void unpause() {
        thread.unpause();
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (thread == null) return false;
        return thread.onTouch(event);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        if (!hasWindowFocus) {
            thread.pause();
        } else {
            thread.unpause();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new GameThread(holder);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        thread.setRunning(false);
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width, height;
        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            width = Math.min(widthSize, heightSize);
            height = Math.min(widthSize, heightSize);
        } else if (widthMode == MeasureSpec.EXACTLY) {
            width = height = widthSize;
        } else if (heightMode == MeasureSpec.EXACTLY) {
            width = height = heightSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            if (heightMode == MeasureSpec.AT_MOST) {
                width = height = Math.min(widthSize, heightSize);
            } else { // heightMode == MeasureSpec.UNSPECIFIED
                width = height = widthSize;
            }
        } else { // widthMode == MeasureSpec.UNSPECIFIED
            width = height = 240;
        }

        setMeasuredDimension(width, height);
    }
    
    private static class StoreState implements Serializable {
        private static final long serialVersionUID = 2003859385743694878L;
        Vector firingAt;
        GameField field;
    }
    
    Serializable getStoreState() {
        StoreState ss = new StoreState();
        ss.firingAt = firingAt;
        ss.field = field;
        return ss;
    }
    
    void restoreState(Context context, Serializable state) {
        StoreState ss = (StoreState) state;
        this.firingAt = ss.firingAt;
        this.field = ss.field;
        this.field.setContext(context);
        
        SurfaceHolder holder = this.getHolder();
        holder.addCallback(this);
        
        setFocusable(true);
    }
}
