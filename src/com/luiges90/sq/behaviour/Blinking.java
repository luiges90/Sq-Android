package com.luiges90.sq.behaviour;

import com.luiges90.sq.GameField;
import com.luiges90.sq.MovingEntity;
import com.luiges90.sq.Utility;

public class Blinking implements OnFrameBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = -1641674293999612205L;
    
    private int freq;
    private int freqTimer;
    
    public Blinking(int freq) {
        this.freq = freq;
        this.freqTimer = Utility.randBetween(0, freq);
    }

    @Override
    public void onFrameUpdate(MovingEntity owner, GameField field) {
        freqTimer++;
        if (freqTimer % (freq * 2) < freq) {
            owner.setAlpha(0);
        } else {
            owner.setAlpha(255);
        }
    }

}
