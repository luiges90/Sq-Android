package com.luiges90.sq.enemy;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import com.luiges90.sq.Vector;
import com.luiges90.sq.behaviour.Behaviour;
import com.luiges90.sq.behaviour.BurstAimedFiring;
import com.luiges90.sq.behaviour.Hp;

public class BurstFiringEnemy extends BaseEnemy {

    private static final long serialVersionUID = 109534790330198599L;
    public static final int COLOR = Color.HSVToColor(new float[] { 120, 1, 0.5f });

    private static List<Behaviour> getBehaviours() {
        List<Behaviour> b = new ArrayList<Behaviour>();
        b.add(new Hp(3));
        b.add(new BurstAimedFiring(DEFAULT_FIRING_RATE, DEFAULT_BULLET_SPEED, 0,
                5, 2, SimpleBullet.class));
        return b;
    }

    public BurstFiringEnemy(Vector position) {
        super(position, DEFAULT_SPEED, DEFAULT_SIZE, COLOR, getBehaviours());
    }

}
