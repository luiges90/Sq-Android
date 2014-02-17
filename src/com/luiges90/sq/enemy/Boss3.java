package com.luiges90.sq.enemy;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import com.luiges90.sq.Vector;
import com.luiges90.sq.behaviour.AimedFiring;
import com.luiges90.sq.behaviour.Behaviour;
import com.luiges90.sq.behaviour.BulletRepelling;
import com.luiges90.sq.behaviour.ColorShift;
import com.luiges90.sq.behaviour.HpShrinking;
import com.luiges90.sq.behaviour.RandomFiring;
import com.luiges90.sq.behaviour.Split;

public class Boss3 extends BaseEnemy {

    /**
     * 
     */
    private static final long serialVersionUID = -3293384947243593738L;
    public static final int COLOR = Color.HSVToColor(new float[] { 70, 1, 0.5f });

    private static List<Behaviour> getBehaviours() {
        List<Behaviour> b = new ArrayList<Behaviour>();
        b.add(new HpShrinking(30, DEFAULT_SIZE / 2));
        b.add(new ColorShift(70, 100));
        b.add(new Split(5, LargeFiringEnemy.class));
        b.add(new Split(5, SplitEnemy.class));
        b.add(new Split(5, BulletAttractingEnemy.class));
        b.add(new BulletRepelling(0.4f));
        b.add(new RandomFiring(DEFAULT_FIRING_RATE / 2, 0, Mine.class));
        b.add(new AimedFiring(DEFAULT_FIRING_RATE / 2, DEFAULT_BULLET_SPEED, 
                (float) Math.toRadians(5), 
                3, (float) Math.toRadians(10), SimpleBullet.class));
        return b;
    }

    public Boss3(Vector position) {
        super(position, DEFAULT_SPEED, DEFAULT_SIZE * 3, COLOR, getBehaviours());
    }
    
}
