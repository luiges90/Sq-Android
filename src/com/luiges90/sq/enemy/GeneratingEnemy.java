package com.luiges90.sq.enemy;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import com.luiges90.sq.Vector;
import com.luiges90.sq.behaviour.Behaviour;
import com.luiges90.sq.behaviour.Hp;
import com.luiges90.sq.behaviour.RandomFiring;

public class GeneratingEnemy extends BaseEnemy {

    private static final long serialVersionUID = -5975044194349957045L;
    public static final int COLOR = Color.HSVToColor(new float[] { 110, 1, 0.5f });

    private static List<Behaviour> getBehaviours() {
        List<Behaviour> b = new ArrayList<Behaviour>();
        b.add(new Hp(8));
        b.add(new RandomFiring(DEFAULT_FIRING_RATE, DEFAULT_BULLET_SPEED, SimpleEnemy.class));
        return b;
    }

    public GeneratingEnemy(Vector position) {
        super(position, DEFAULT_SPEED, DEFAULT_SIZE, COLOR, getBehaviours());
    }

}
