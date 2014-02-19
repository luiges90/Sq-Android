package com.luiges90.sq.enemy;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import com.luiges90.sq.Vector;
import com.luiges90.sq.behaviour.AimedFiring;
import com.luiges90.sq.behaviour.Behaviour;
import com.luiges90.sq.behaviour.Hp;

public class AimedFrequentFiringEnemy extends BaseEnemy {

    private static final long serialVersionUID = -1449647502330845271L;
    public static final int COLOR = Color.HSVToColor(new float[] { 30, 0.8f, 0.5f });

    private static List<Behaviour> getBehaviours() {
        List<Behaviour> b = new ArrayList<Behaviour>();
        b.add(new Hp(3));
        b.add(new AimedFiring(DEFAULT_FIRING_RATE / 2, DEFAULT_BULLET_SPEED, 0,
                3, (float) Math.toRadians(10), SimpleBullet.class));
        return b;
    }

    public AimedFrequentFiringEnemy(Vector position) {
        super(position, DEFAULT_SPEED, DEFAULT_SIZE, COLOR, getBehaviours());
    }

}
