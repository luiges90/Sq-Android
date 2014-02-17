package com.luiges90.sq.enemy;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import com.luiges90.sq.Vector;
import com.luiges90.sq.behaviour.AimedFiring;
import com.luiges90.sq.behaviour.Behaviour;
import com.luiges90.sq.behaviour.BehaviourWhenBelowHp;
import com.luiges90.sq.behaviour.BurstAimedFiring;
import com.luiges90.sq.behaviour.Chasing;
import com.luiges90.sq.behaviour.ColorShift;
import com.luiges90.sq.behaviour.CounterAttack;
import com.luiges90.sq.behaviour.Hp;
import com.luiges90.sq.behaviour.RandomFiring;
import com.luiges90.sq.behaviour.Sneaky;
import com.luiges90.sq.behaviour.Split;
import com.luiges90.sq.behaviour.TeleportOnHit;

public class Boss4 extends BaseEnemy {

    private static final long serialVersionUID = 8466322691080866776L;
    public static final int COLOR = Color.HSVToColor(new float[] { 110, 1, 0.5f });

    private static List<Behaviour> getBehaviours() {
        List<Behaviour> b = new ArrayList<Behaviour>();
        b.add(new Hp(40));
        b.add(new ColorShift(110, 130));
        b.add(new BehaviourWhenBelowHp(5, new Sneaky(100)));
        b.add(new BehaviourWhenBelowHp(5, new Chasing(0.07f)));
        b.add(new RandomFiring(DEFAULT_FIRING_RATE, DEFAULT_BULLET_SPEED, SimpleEnemy.class));
        b.add(new AimedFiring(DEFAULT_FIRING_RATE / 2, DEFAULT_BULLET_SPEED, 0,
                3, (float) Math.toRadians(10), IndestructibleBullet.class));
        b.add(new BurstAimedFiring(DEFAULT_FIRING_RATE, DEFAULT_BULLET_SPEED, 0,
                5, 2, SimpleBullet.class));
        b.add(new TeleportOnHit());
        b.add(new CounterAttack(DEFAULT_BULLET_SPEED, 0, IndestructibleBullet.class));
        b.add(new Split(10, DarkRandomFiringEnemy.class));
        return b;
    }

    public Boss4(Vector position) {
        super(position, DEFAULT_SPEED, DEFAULT_SIZE * 3, COLOR, getBehaviours());
    }
    
}
