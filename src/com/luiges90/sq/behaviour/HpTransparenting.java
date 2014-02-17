package com.luiges90.sq.behaviour;

import com.luiges90.sq.MovingEntity;

public class HpTransparenting extends Hp {

    /**
     * 
     */
    private static final long serialVersionUID = 8125469357878061148L;

    public HpTransparenting(int hp) {
        super(hp);
    }
    
    protected void colorChange(MovingEntity owner) {
        owner.setAlpha(getHp() * 255 / getInitHp());
    }

}
