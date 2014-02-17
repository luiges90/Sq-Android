package com.luiges90.sq.behaviour;

import com.luiges90.sq.GameField;
import com.luiges90.sq.MovingEntity;
import com.luiges90.sq.PlayerBullet;
import com.luiges90.sq.Vector;

public class BulletRepelling implements OnFrameBehaviour {

    private static final long serialVersionUID = 130425408843865882L;

    private float force;

    public BulletRepelling(float force) {
        this.force = force;
    }

    @Override
    public void onFrameUpdate(MovingEntity owner, GameField field) {
        for (PlayerBullet pb : field.getPlayerBullets()) {
            Vector f = Vector.fromTo(owner.getPosition(), pb.getPosition()).normalize()
                    .scale(force);
            pb.applyForce(f);
        }
    }

}
