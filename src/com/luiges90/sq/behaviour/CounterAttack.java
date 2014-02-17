package com.luiges90.sq.behaviour;

import com.luiges90.sq.GameField;
import com.luiges90.sq.MovingEntity;
import com.luiges90.sq.R;
import com.luiges90.sq.Utility;
import com.luiges90.sq.Vector;
import com.luiges90.sq.enemy.BaseEnemy;

public class CounterAttack implements OnDestroyBehaviour {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1771703954090183559L;
    private Class<? extends BaseEnemy> bullet;
    private int speed, spread;
    private float spreadAngle, aimError;
    
    private boolean onlyOnDestroy;
    
    private int soundResId;
    
    public CounterAttack(int speed, float aimError, Class<? extends BaseEnemy> bullet) {
        this(false, speed, 1, 0, aimError, R.raw.fire, bullet);
    }

    public CounterAttack(int speed, int spread, float spreadAngle, float aimError,
            Class<? extends BaseEnemy> bullet) {
        this(false, speed, spread, spreadAngle, aimError, R.raw.fire, bullet);
    }
    
    public CounterAttack(boolean onlyOnDestroy, 
            int speed, int spread, float spreadAngle, float aimError,
            Class<? extends BaseEnemy> bullet) {
        this(onlyOnDestroy, speed, spread, spreadAngle, aimError, R.raw.fire, bullet);
    }
    
    public CounterAttack(boolean onlyOnDestroy, 
            int speed, int spread, float spreadAngle, float aimError, int soundResId,
            Class<? extends BaseEnemy> bullet) {
        this.onlyOnDestroy = onlyOnDestroy;
        this.speed = speed;
        this.bullet = bullet;
        this.spread = spread;
        this.spreadAngle = spreadAngle;
        this.soundResId = soundResId;
    }

    @Override
    public boolean isDestroyed(MovingEntity owner, GameField field) {
        return true;
    }
    
    private void fire(MovingEntity owner, GameField field) {
        float angle;
        if (field.isPlayerDestroyed()) {
            angle = Utility.randomAngle();
        } else {
            angle = Vector.fromTo(owner.getPosition(), field.getPlayerPosition()).angle +
                    Utility.randBetween(-aimError, aimError);
        }

        for (int i = -this.spread + 1; i <= this.spread / 2 + 1; i += 2) {
            BaseEnemy bullet = Utility.createEnemyFromClass(this.bullet, (BaseEnemy) owner);
            bullet.setVelocity(Vector.fromRT(speed, angle + (i / 2.0f) * spreadAngle));
            field.addEnemy(bullet);
        }

        field.getSoundPlayer().playSound(soundResId);
    }

    @Override
    public void onHit(MovingEntity owner, GameField field) {
        if (!onlyOnDestroy) {
            fire(owner, field);
        }
    }

    @Override
    public void onDestroy(MovingEntity owner, GameField field) {
        if (onlyOnDestroy) {
            fire(owner, field);
        }
    }

}
    