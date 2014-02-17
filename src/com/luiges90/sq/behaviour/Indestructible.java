package com.luiges90.sq.behaviour;

import com.luiges90.sq.GameField;
import com.luiges90.sq.MovingEntity;

public class Indestructible implements OnDestroyBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = 4379915387334458624L;

    @Override
    public boolean isDestroyed(MovingEntity owner, GameField field) {
        return false;
    }

    @Override
    public void onHit(MovingEntity owner, GameField field) {
        // no-op

    }

    @Override
    public void onDestroy(MovingEntity owner, GameField field) {
        // no-op

    }

}
