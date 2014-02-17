package com.luiges90.sq.behaviour;

import com.luiges90.sq.GameField;
import com.luiges90.sq.MovingEntity;
import com.luiges90.sq.enemy.BaseEnemy;

public class DestroyAfterOtherDestroyed implements OnDestroyBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = -5765528929616017924L;
    private Class<? extends BaseEnemy> target;

    public DestroyAfterOtherDestroyed(Class<? extends BaseEnemy> target) {
        this.target = target;
    }

    @Override
    public boolean isDestroyed(MovingEntity owner, GameField field) {
        for (BaseEnemy e : field.getAllEnemies()) {
            if (e.getClass() == target) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onHit(MovingEntity owner, GameField field) {
        for (BaseEnemy e : field.getAllEnemies()) {
            if (e.getClass() == target) {
                for (Behaviour b : ((BaseEnemy) owner).getAllBehaviours()) {
                    if (b instanceof Hp) {
                        ((Hp) b).revertHpLoss(owner);
                    }
                }
                return;
            }
        }
    }

    @Override
    public void onDestroy(MovingEntity owner, GameField field) {
        // no-op
    }

}
