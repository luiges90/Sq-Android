package com.luiges90.sq.behaviour;

import com.luiges90.sq.GameField;
import com.luiges90.sq.MovingEntity;
import com.luiges90.sq.R;
import com.luiges90.sq.Utility;
import com.luiges90.sq.Vector;
import com.luiges90.sq.enemy.BaseEnemy;

public class RandomFiring implements OnFrameBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = 7829574061662711604L;
    private int fireCooldown, speed, spread;
    private float spreadAngle;
    private Class<? extends BaseEnemy> bullet;

    private boolean justFired = false;

    private int fireCooldownTimer;
    private int soundResId;

    public RandomFiring(int fireCooldown, int speed, Class<? extends BaseEnemy> bullet) {
        this(fireCooldown, speed, 1, 0, R.raw.fire, bullet);
    }

    public RandomFiring(int fireCooldown, int speed, int spread, float spreadAngle,
            Class<? extends BaseEnemy> bullet) {
        this(fireCooldown, speed, spread, spreadAngle, R.raw.fire, bullet);
    }

    public RandomFiring(int fireCooldown, int speed, int spread, float spreadAngle, int soundResId,
            Class<? extends BaseEnemy> bullet) {
        this.fireCooldown = fireCooldown;
        this.speed = speed;
        this.bullet = bullet;
        this.spread = spread;
        this.spreadAngle = spreadAngle;
        this.soundResId = soundResId;

        this.fireCooldownTimer = Utility.randBetween(0, fireCooldown);
    }

    protected float getFireAngle(MovingEntity owner, GameField field) {
        return Utility.randomAngle();
    }

    protected void fire(MovingEntity owner, GameField field) {
        float angle = getFireAngle(owner, field);
        for (int i = -this.spread + 1; i <= this.spread / 2 + 1; i += 2) {
            BaseEnemy bullet = Utility.createEnemyFromClass(this.bullet, (BaseEnemy) owner);
            bullet.setVelocity(Vector.fromRT(speed, angle + (i / 2.0f) * spreadAngle));
            field.addEnemy(bullet);
        }

        field.getSoundPlayer().playSound(soundResId);
    }

    public void onFrameUpdate(MovingEntity owner, GameField field) {
        if (this.fireCooldownTimer <= 0) {
            this.fireCooldownTimer = this.fireCooldown;

            this.fire(owner, field);

            justFired = true;
        } else {
            justFired = false;
        }

        this.fireCooldownTimer--;
    }

    public boolean hasJustFired() {
        return justFired;
    }
}
