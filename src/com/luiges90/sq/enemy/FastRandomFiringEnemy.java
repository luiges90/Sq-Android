package com.luiges90.sq.enemy;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import com.luiges90.sq.Vector;
import com.luiges90.sq.behaviour.Behaviour;
import com.luiges90.sq.behaviour.RandomFiring;

public class FastRandomFiringEnemy extends BaseEnemy {

    /**
     * 
     */
    private static final long serialVersionUID = 8704247707549980619L;
    public static final int COLOR = Color.HSVToColor(new float[] { 20, 0.8f, 0.5f });

    private static List<Behaviour> getBehaviours() {
        List<Behaviour> b = new ArrayList<Behaviour>();
        b.add(new RandomFiring(DEFAULT_FIRING_RATE, DEFAULT_BULLET_SPEED, SimpleBullet.class));
        return b;
    }

    public FastRandomFiringEnemy(Vector position) {
        super(position, DEFAULT_SPEED * 2, DEFAULT_SIZE, COLOR, getBehaviours());
    }
    
}
