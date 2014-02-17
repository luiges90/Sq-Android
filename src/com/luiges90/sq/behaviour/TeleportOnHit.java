package com.luiges90.sq.behaviour;

import com.luiges90.sq.GameField;
import com.luiges90.sq.MovingEntity;
import com.luiges90.sq.R;
import com.luiges90.sq.enemy.BaseEnemy;

public class TeleportOnHit implements OnDestroyBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = 1443782866814193676L;

    @Override
    public boolean isDestroyed(MovingEntity owner, GameField field) {
        return true;
    }

    @Override
    public void onHit(MovingEntity owner, GameField field) {
        owner.setPosition(BaseEnemy.initPosition(field.getPlayerPosition()));
        field.getSoundPlayer().playSound(R.raw.teleport);
    }

    @Override
    public void onDestroy(MovingEntity owner, GameField field) {
        // no-op
        
    }

}
