package com.luiges90.sq.behaviour;

import com.luiges90.sq.GameField;
import com.luiges90.sq.MovingEntity;

public class ColorShift implements OnFrameBehaviour {
    
    /**
     * 
     */
    private static final long serialVersionUID = 7599128845322915710L;
    private float h1, h2;
    private int t;
    
    private float freq = 45;
    
    public ColorShift(float h1, float h2) {
        this.h1 = Math.min(h1, h2);
        this.h2 = Math.max(h1, h2);
        this.t = 0;
    }

    public void onFrameUpdate(MovingEntity owner, GameField field) {
        t++;
        
        double halfHeight = (h2 - h1) / 2;
        float h = (float) (Math.sin(t / freq) * halfHeight + h1 + halfHeight); 
        
        owner.setHue(h);
    }
    
}
