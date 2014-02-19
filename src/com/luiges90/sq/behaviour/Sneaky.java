package com.luiges90.sq.behaviour;

import com.luiges90.sq.GameField;
import com.luiges90.sq.MovingEntity;
import com.luiges90.sq.Vector;

public class Sneaky implements OnFrameBehaviour, OnDestroyBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = -5816603900138173430L;

    private int range;

    public Sneaky(int range) {
        this.range = range;
    }

    @Override
    public void onFrameUpdate(MovingEntity owner, GameField field) {
        if (!field.isPlayerDestroyed() &&
                Vector.distanceSquared(field.getPlayerPosition(), owner.getPosition())
                < range * range) {
            owner.setAlpha(255);
        } else {
            owner.setAlpha(0);
        }
    }

    @Override
    public boolean isDestroyed(MovingEntity owner, GameField field) {
        return Vector.distanceSquared(field.getPlayerPosition(), owner.getPosition())
        < range * range;
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
