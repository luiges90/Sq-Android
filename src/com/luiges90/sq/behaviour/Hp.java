package com.luiges90.sq.behaviour;

import com.luiges90.sq.GameField;
import com.luiges90.sq.MovingEntity;

public class Hp implements OnDestroyBehaviour {
    
    /**
     * 
     */
    private static final long serialVersionUID = -3755071300328253282L;
    private int hp;
    private int initHp;
    
    public Hp(int hp) {
        this.hp = hp;
        this.initHp = hp; 
    }
    
    public int getHp() {
        return hp;
    }
    
    public int getInitHp() {
        return initHp;
    }

    @Override
    public boolean isDestroyed(MovingEntity owner, GameField field) {
        return hp <= 1;
    }
    
    protected void colorChange(MovingEntity owner) {
        owner.setVal(1.0f - this.hp / (float) this.initHp * 0.5f);
    }

    @Override
    public void onHit(MovingEntity owner, GameField field) {
        hp--;
        colorChange(owner);
    }

    @Override
    public void onDestroy(MovingEntity owner, GameField field) {
        // no-op
    }
    
    protected void revertHpLoss(MovingEntity owner) {
        hp++;
        colorChange(owner);
    }

}
