package com.luiges90.sq.enemy;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import com.luiges90.sq.Vector;
import com.luiges90.sq.behaviour.Behaviour;
import com.luiges90.sq.behaviour.Hp;
import com.luiges90.sq.behaviour.RandomFiring;

public class MineLayerEnemy extends BaseEnemy {

    /**
     * 
     */
    private static final long serialVersionUID = 655905255764014629L;
    public static final int COLOR = Color.HSVToColor(new float[] { 100, 0.9f, 0.5f });
    
    private static List<Behaviour> getBehaviours() {
        List<Behaviour> b = new ArrayList<Behaviour>();
        b.add(new Hp(3));
        b.add(new RandomFiring(DEFAULT_FIRING_RATE / 2, 0, Mine.class));
        return b;
    }

    public MineLayerEnemy(Vector position) {
        super(position, DEFAULT_SPEED, DEFAULT_SIZE, COLOR, getBehaviours());
    }
    
}
