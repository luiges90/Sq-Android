package com.luiges90.sq.enemy;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import com.luiges90.sq.Vector;
import com.luiges90.sq.behaviour.Behaviour;
import com.luiges90.sq.behaviour.RandomFiring;

public class RandomFiringFrequentEnemy extends BaseEnemy {

    private static final long serialVersionUID = 6277050800283162985L;

    public static final int COLOR = Color.HSVToColor(new float[] { 20, 0.9f, 0.5f });

    private static List<Behaviour> getBehaviours() {
        List<Behaviour> b = new ArrayList<Behaviour>();
        b.add(new RandomFiring(DEFAULT_FIRING_RATE / 2, DEFAULT_BULLET_SPEED, SimpleBullet.class));
        return b;
    }

    public RandomFiringFrequentEnemy(Vector position) {
        super(position, DEFAULT_SPEED, DEFAULT_SIZE, COLOR, getBehaviours());
    }

}
