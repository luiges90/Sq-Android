package com.luiges90.sq.enemy;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import com.luiges90.sq.Vector;
import com.luiges90.sq.behaviour.AimedFiring;
import com.luiges90.sq.behaviour.Behaviour;
import com.luiges90.sq.behaviour.BulletRepelling;
import com.luiges90.sq.behaviour.ColorShift;
import com.luiges90.sq.behaviour.CounterAttack;
import com.luiges90.sq.behaviour.DestroyAfterOtherDestroyed;
import com.luiges90.sq.behaviour.HpTransparenting;
import com.luiges90.sq.behaviour.RandomFiring;
import com.luiges90.sq.behaviour.Split;
import com.luiges90.sq.behaviour.Teleport;
import com.luiges90.sq.behaviour.TeleportOnHit;

public class Boss5 extends BaseEnemy {

    private static final long serialVersionUID = 8466322691080866776L;
    public static final int COLOR = Color.HSVToColor(new float[] { 140, 1, 0.5f });

    private static List<Behaviour> getBehaviours() {
        List<Behaviour> b = new ArrayList<Behaviour>();
        b.add(new HpTransparenting(10));
        b.add(new ColorShift(140, 170));
        b.add(new RandomFiring(DEFAULT_FIRING_RATE, DEFAULT_BULLET_SPEED, ChasingEnemy.class));
        b.add(new DestroyAfterOtherDestroyed(ChasingEnemy.class));
        b.add(new BulletRepelling(0.4f));
        b.add(new Teleport(DEFAULT_FIRING_RATE));
        b.add(new TeleportOnHit());
        b.add(new AimedFiring(DEFAULT_FIRING_RATE / 2, DEFAULT_BULLET_SPEED * 2, 0,
                SimpleBullet.class));
        b.add(new CounterAttack(true, DEFAULT_BULLET_SPEED, 60, (float) Math.toRadians(360 / 60), 
                0, IndestructibleBullet.class));
        b.add(new Split(3, BlackBulletAttractingEnemy.class));
        return b;
    }

    public Boss5(Vector position) {
        super(position, DEFAULT_SPEED, DEFAULT_SIZE * 3, COLOR, getBehaviours());
    }
    
}
