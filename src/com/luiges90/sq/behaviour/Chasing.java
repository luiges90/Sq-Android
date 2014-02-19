package com.luiges90.sq.behaviour;

import com.luiges90.sq.GameField;
import com.luiges90.sq.MovingEntity;
import com.luiges90.sq.Vector;

public class Chasing implements OnFrameBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = 3995629977296004312L;
    private final float chaseFactor;

    public Chasing(float chaseFactor) {
        this.chaseFactor = chaseFactor;
    }

    public void onFrameUpdate(MovingEntity owner, GameField field) {
        if (field.isPlayerDestroyed())
            return;

        float current = owner.getVelocity().angle;
        float target = Vector.fromTo(owner.getPosition(), field.getPlayerPosition()).angle;

        float diff = Math.abs(target - current);
        float direction;
        if (target > current) {
            direction = diff > Math.PI ? -1 : 1;
        } else {
            direction = diff > Math.PI ? 1 : -1;
        }

        float oldMag = owner.getVelocity().length;
        if (diff < this.chaseFactor) {
            owner.setVelocity(Vector.fromRT(oldMag, target));
        } else {
            owner.setVelocity(Vector.fromRT(oldMag, current + this.chaseFactor * direction));
        }
    }

}
