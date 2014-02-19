package com.luiges90.sq.behaviour;

import com.luiges90.sq.GameField;
import com.luiges90.sq.MovingEntity;
import com.luiges90.sq.R;
import com.luiges90.sq.Utility;
import com.luiges90.sq.Vector;
import com.luiges90.sq.enemy.BaseEnemy;

public class AimedFiring extends RandomFiring {

    /**
     * 
     */
    private static final long serialVersionUID = 5198695669931149656L;
    private float aimError;

    public AimedFiring(int fireCooldown, int speed, float aimError,
            Class<? extends BaseEnemy> bullet) {
        super(fireCooldown, speed, 1, 0, R.raw.fire, bullet);
        this.aimError = aimError;
    }

    public AimedFiring(int fireCooldown, int speed, float aimError, int spread, float spreadAngle,
            Class<? extends BaseEnemy> bullet) {
        super(fireCooldown, speed, spread, spreadAngle, R.raw.fire, bullet);
        this.aimError = aimError;
    }

    public AimedFiring(int fireCooldown, int speed, float aimError, int spread, float spreadAngle,
            int soundResId,
            Class<? extends BaseEnemy> bullet) {
        super(fireCooldown, speed, spread, spreadAngle, soundResId, bullet);
        this.aimError = aimError;
    }

    protected float getFireAngle(MovingEntity owner, GameField field) {
        if (field.isPlayerDestroyed()) {
            return Utility.randomAngle();
        } else {
            return Vector.fromTo(owner.getPosition(), field.getPlayerPosition()).angle +
                    Utility.randBetween(-aimError, aimError);
        }
    }

}
