package com.luiges90.sq.behaviour;

import com.luiges90.sq.GameField;
import com.luiges90.sq.MovingEntity;
import com.luiges90.sq.Utility;
import com.luiges90.sq.Vector;
import com.luiges90.sq.enemy.BaseEnemy;

public class Split implements OnDestroyBehaviour {

    private static final long serialVersionUID = 7341162037814181555L;

    private int count;
    private Class<? extends BaseEnemy> splitTo;

    public Split(int count, Class<? extends BaseEnemy> splitTo) {
        this.count = count;
        this.splitTo = splitTo;
    }

    @Override
    public boolean isDestroyed(MovingEntity owner, GameField field) {
        return true;
    }

    @Override
    public void onHit(MovingEntity owner, GameField field) {
        // no-op
    }

    @Override
    public void onDestroy(MovingEntity owner, GameField field) {
        for (int i = 0; i < count; ++i) {
            BaseEnemy splited = Utility.createEnemyFromClass(splitTo, owner.getPosition());
            splited.setVelocity(Vector.fromRT(owner.getVelocity().length, Utility.randomAngle()));
            field.addEnemy(splited);
        }
    }

}
