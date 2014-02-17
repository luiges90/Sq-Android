package com.luiges90.sq.enemy;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import com.luiges90.sq.GameView;
import com.luiges90.sq.Vector;
import com.luiges90.sq.behaviour.Behaviour;
import com.luiges90.sq.behaviour.Blinking;
import com.luiges90.sq.behaviour.ColorShift;
import com.luiges90.sq.behaviour.CounterAttack;
import com.luiges90.sq.behaviour.Hp;
import com.luiges90.sq.behaviour.RandomFiring;
import com.luiges90.sq.behaviour.TeleportOnHit;

public class Boss2 extends BaseEnemy {

    /**
     * 
     */
    private static final long serialVersionUID = 1234493897450373933L;
    public static final int COLOR = Color.HSVToColor(new float[] { 50, 1, 0.5f });

    private static List<Behaviour> getBehaviours() {
        List<Behaviour> b = new ArrayList<Behaviour>();
        b.add(new Hp(20));
        b.add(new ColorShift(50, 60));
        b.add(new Blinking(30));
        b.add(new TeleportOnHit());
        b.add(new CounterAttack(DEFAULT_BULLET_SPEED, 0, IndestructibleBullet.class));
        b.add(new RandomFiring(GameView.FPS * 3 / 4, DEFAULT_BULLET_SPEED, 3, 
                (float) Math.toRadians(10), SimpleBullet.class));
        return b;
    }

    public Boss2(Vector position) {
        super(position, DEFAULT_SPEED, DEFAULT_SIZE * 3, COLOR, getBehaviours());
    }
    
}
