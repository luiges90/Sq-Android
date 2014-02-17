package com.luiges90.sq.behaviour;

import com.luiges90.sq.GameField;
import com.luiges90.sq.MovingEntity;

public interface OnDestroyBehaviour extends Behaviour {
    
    /**
     * Whether to destroy this entity
     * @param owner
     * @param field
     * @return
     */
    public boolean isDestroyed(MovingEntity owner, GameField field);
    
    public void onHit(MovingEntity owner, GameField field);

    public void onDestroy(MovingEntity owner, GameField field);
    
}
