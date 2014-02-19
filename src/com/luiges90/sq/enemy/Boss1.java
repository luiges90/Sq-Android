package com.luiges90.sq.enemy;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import com.luiges90.sq.GameView;
import com.luiges90.sq.Vector;
import com.luiges90.sq.behaviour.AimedFiring;
import com.luiges90.sq.behaviour.Behaviour;
import com.luiges90.sq.behaviour.Chasing;
import com.luiges90.sq.behaviour.ColorShift;
import com.luiges90.sq.behaviour.CounterAttack;
import com.luiges90.sq.behaviour.Hp;
import com.luiges90.sq.behaviour.RandomFiring;

public class Boss1 extends BaseEnemy {

    private static final long serialVersionUID = 6277050800283162985L;

    public static final int COLOR = Color.HSVToColor(new float[] { 0, 1, 0.5f });

    private static List<Behaviour> getBehaviours() {
        List<Behaviour> b = new ArrayList<Behaviour>();
        b.add(new Hp(70));
        b.add(new ColorShift(0, 40));
        b.add(new Chasing(0.04f));
        b.add(new AimedFiring(GameView.FPS * 3 / 2, DEFAULT_BULLET_SPEED, (float) Math
                .toRadians(10),
                SimpleBullet.class));
        b.add(new RandomFiring(GameView.FPS * 3 / 2, DEFAULT_BULLET_SPEED,
                SimpleBullet.class));
        b.add(new CounterAttack(DEFAULT_BULLET_SPEED, 0, SimpleBullet.class));
        return b;
    }

    public Boss1(Vector position) {
        super(position, DEFAULT_SPEED, DEFAULT_SIZE * 3, COLOR, getBehaviours());
    }

}
