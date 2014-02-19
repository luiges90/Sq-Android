package com.luiges90.sq.enemy;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import com.luiges90.sq.Vector;
import com.luiges90.sq.behaviour.AimedFiring;
import com.luiges90.sq.behaviour.Behaviour;
import com.luiges90.sq.behaviour.BulletRepelling;

public class BulletRepellingFiringEnemy extends BaseEnemy {

    /**
     * 
     */
    private static final long serialVersionUID = -8681137212660672481L;
    public static final int COLOR = Color.HSVToColor(new float[] { 90, 0.9f, 0.5f });

    private static List<Behaviour> getBehaviours() {
        List<Behaviour> b = new ArrayList<Behaviour>();
        b.add(new BulletRepelling(0.4f));
        b.add(new AimedFiring(DEFAULT_FIRING_RATE, DEFAULT_BULLET_SPEED, 0, 3,
                (float) Math.toRadians(10), SimpleBullet.class));
        return b;
    }

    public BulletRepellingFiringEnemy(Vector position) {
        super(position, DEFAULT_SPEED, DEFAULT_SIZE, COLOR, getBehaviours());
    }

}
