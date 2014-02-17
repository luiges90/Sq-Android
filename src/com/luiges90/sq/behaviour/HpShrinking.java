package com.luiges90.sq.behaviour;

import com.luiges90.sq.GameField;
import com.luiges90.sq.MovingEntity;
import com.luiges90.sq.enemy.BaseEnemy;

public class HpShrinking extends Hp {
    
    /**
     * 
     */
    private static final long serialVersionUID = 7653066340207631296L;
    private float size = -1;
    private float reduceSize;
    private int smallest;
    
    public HpShrinking(int hp, int smallest) {
        super(hp);
        this.smallest = smallest;
    }

    public void onHit(MovingEntity owner, GameField field) {
        BaseEnemy e = (BaseEnemy) owner;
        if (size < 0) {
            size = e.getSize();
            reduceSize = (size - smallest) / getHp(); 
        }

        super.onHit(owner, field);
        
        size -= reduceSize;
        e.setSize((int) size);
    }
    
}
