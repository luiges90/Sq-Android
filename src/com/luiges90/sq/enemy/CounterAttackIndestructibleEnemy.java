package com.luiges90.sq.enemy;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import com.luiges90.sq.Vector;
import com.luiges90.sq.behaviour.Behaviour;
import com.luiges90.sq.behaviour.CounterAttack;

public class CounterAttackIndestructibleEnemy extends BaseEnemy {

    /**
     * 
     */
    private static final long serialVersionUID = -8599499675096221042L;
    public static final int COLOR = Color.HSVToColor(new float[] { 40, 0.9f, 0.5f });

    private static List<Behaviour> getBehaviours() {
        List<Behaviour> b = new ArrayList<Behaviour>();
        b.add(new CounterAttack(DEFAULT_BULLET_SPEED, 0, IndestructibleBullet.class));
        return b;
    }

    public CounterAttackIndestructibleEnemy(Vector position) {
        super(position, DEFAULT_SPEED, DEFAULT_SIZE, COLOR, getBehaviours());
    }

}
