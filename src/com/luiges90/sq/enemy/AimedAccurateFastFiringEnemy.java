package com.luiges90.sq.enemy;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import com.luiges90.sq.Vector;
import com.luiges90.sq.behaviour.AimedFiring;
import com.luiges90.sq.behaviour.Behaviour;

public class AimedAccurateFastFiringEnemy extends BaseEnemy {

    private static final long serialVersionUID = 6277050800283162985L;

    public static final int COLOR = Color.HSVToColor(new float[] { 30, 0.6f, 0.5f });

    private static List<Behaviour> getBehaviours() {
        List<Behaviour> b = new ArrayList<Behaviour>();
        b.add(new AimedFiring(DEFAULT_FIRING_RATE / 2, DEFAULT_BULLET_SPEED * 2, 0,
                SimpleBullet.class));
        return b;
    }

    public AimedAccurateFastFiringEnemy(Vector position) {
        super(position, DEFAULT_SPEED, DEFAULT_SIZE, COLOR, getBehaviours());
    }
}
