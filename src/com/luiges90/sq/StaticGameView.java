package com.luiges90.sq;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

public class StaticGameView extends View {

    private GameField field;

    public StaticGameView(Context context) {
        super(context);
    }

    public StaticGameView(Context context, AttributeSet as) {
        super(context, as);
    }

    public StaticGameView(Context context, AttributeSet as, int i) {
        super(context, as, i);
    }

    public void setField(GameField field) {
        this.field = field;
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        field.draw(canvas, this.getWidth());
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

}
