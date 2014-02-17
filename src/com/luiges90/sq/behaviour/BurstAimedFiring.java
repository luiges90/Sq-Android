package com.luiges90.sq.behaviour;

import com.luiges90.sq.GameField;
import com.luiges90.sq.MovingEntity;
import com.luiges90.sq.enemy.BaseEnemy;

public class BurstAimedFiring extends AimedFiring {

    /**
     * 
     */
    private static final long serialVersionUID = 6498433675140570134L;
    private int burstCount;
    private int burstCountRemain;
    private int burstCooldown;
    private int burstCooldownTimer;

    private boolean bursting = false;

    public BurstAimedFiring(int fireCooldown, int speed, float aimError,
            int burstCount, int burstCooldown, Class<? extends BaseEnemy> bullet) {
        super(fireCooldown, speed, aimError, bullet);
        this.burstCount = burstCount;
        this.burstCooldown = burstCooldown;
    }

    public BurstAimedFiring(int fireCooldown, int speed, float aimError, int spread,
            float spreadAngle,
            int burstCount, int burstCooldown, Class<? extends BaseEnemy> bullet) {
        super(fireCooldown, speed, aimError, spread, spreadAngle, bullet);
        this.burstCount = burstCount;
        this.burstCooldown = burstCooldown;
    }

    public BurstAimedFiring(int fireCooldown, int speed, float aimError, int spread,
            float spreadAngle, int soundResId,
            int burstCount, int burstCooldown, Class<? extends BaseEnemy> bullet) {
        super(fireCooldown, speed, aimError, spread, spreadAngle, soundResId, bullet);
        this.burstCount = burstCount;
        this.burstCooldown = burstCooldown;
    }

    public void onFrameUpdate(MovingEntity owner, GameField field) {
        super.onFrameUpdate(owner, field);
        if (super.hasJustFired()) {
            bursting = true;
            burstCountRemain = burstCount - 1;
            burstCooldownTimer = 0;
        } else if (bursting && burstCountRemain > 0) {
            if (burstCooldownTimer <= 0) {
                super.fire(owner, field);
                burstCountRemain--;
                burstCooldownTimer = burstCooldown;
            }
            burstCooldownTimer--;
        }
    }

}
