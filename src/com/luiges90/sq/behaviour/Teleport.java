package com.luiges90.sq.behaviour;

import com.luiges90.sq.GameField;
import com.luiges90.sq.MovingEntity;
import com.luiges90.sq.R;
import com.luiges90.sq.Utility;
import com.luiges90.sq.enemy.BaseEnemy;

public class Teleport implements OnFrameBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = 4097871414661770614L;

    private int freq;
    private int timer;

    public Teleport(int freq) {
        this.freq = freq;
        this.timer = Utility.randBetween(0, freq);
    }

    @Override
    public void onFrameUpdate(MovingEntity owner, GameField field) {
        timer--;
        if (timer <= 0) {
            owner.setPosition(BaseEnemy.initPosition(field.getPlayerPosition()));
            field.getSoundPlayer().playSound(R.raw.teleport);
            timer = freq;
        }
    }

}
