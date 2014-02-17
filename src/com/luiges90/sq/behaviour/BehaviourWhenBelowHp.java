package com.luiges90.sq.behaviour;

import java.util.List;

import com.luiges90.sq.GameField;
import com.luiges90.sq.MovingEntity;
import com.luiges90.sq.enemy.BaseEnemy;

public class BehaviourWhenBelowHp implements OnFrameBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = -6510033843913135153L;
    private int threshold;
    private Behaviour behaviour;

    public BehaviourWhenBelowHp(int threshold, Behaviour behaviour) {
        this.threshold = threshold;
        this.behaviour = behaviour;
    }

    @Override
    public void onFrameUpdate(MovingEntity owner, GameField field) {
        BaseEnemy e = (BaseEnemy) owner;
        List<Behaviour> allBehaviours = e.getAllBehaviours();

        boolean hpFound = false;
        for (Behaviour i : allBehaviours) {
            if (i instanceof Hp) {
                hpFound = true;
                int hp = ((Hp) i).getHp();
                if (hp <= threshold) {
                    ((OnFrameBehaviour) behaviour).onFrameUpdate(owner, field);
                }
            }
        }

        if (!hpFound) {
            throw new IllegalArgumentException("BehaviourWhenBelowHp shall carry the Hp Behaviour.");
        }
    }

}
