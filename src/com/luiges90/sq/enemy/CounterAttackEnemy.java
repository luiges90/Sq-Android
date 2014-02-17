package com.luiges90.sq.enemy;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import com.luiges90.sq.Vector;
import com.luiges90.sq.behaviour.Behaviour;
import com.luiges90.sq.behaviour.CounterAttack;

public class CounterAttackEnemy extends BaseEnemy {

    /**
     * 
     */
    private static final long serialVersionUID = 8271099992146779155L;
    public static final int COLOR = Color.HSVToColor(new float[] { 40, 1, 0.5f });

    private static List<Behaviour> getBehaviours() {
        List<Behaviour> b = new ArrayList<Behaviour>();
        b.add(new CounterAttack(DEFAULT_BULLET_SPEED, 0, SimpleBullet.class));
        return b;
    }

    public CounterAttackEnemy(Vector position) {
        super(position, DEFAULT_SPEED, DEFAULT_SIZE, COLOR, getBehaviours());
    }
    
}
