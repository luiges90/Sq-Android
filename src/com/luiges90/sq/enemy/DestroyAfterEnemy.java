package com.luiges90.sq.enemy;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import com.luiges90.sq.Vector;
import com.luiges90.sq.behaviour.AimedFiring;
import com.luiges90.sq.behaviour.Behaviour;
import com.luiges90.sq.behaviour.DestroyAfterOtherDestroyed;
import com.luiges90.sq.behaviour.Hp;

public class DestroyAfterEnemy extends BaseEnemy {

    /**
     * 
     */
    private static final long serialVersionUID = -8335696762172862219L;
    public static final int COLOR = Color.HSVToColor(new float[] { 140, 1, 0.5f });

    private static List<Behaviour> getBehaviours() {
        List<Behaviour> b = new ArrayList<Behaviour>();
        b.add(new Hp(3));
        b.add(new AimedFiring(DEFAULT_FIRING_RATE, DEFAULT_BULLET_SPEED, 0,
                SimpleBullet.class));
        b.add(new DestroyAfterOtherDestroyed(DestroyTarget.class));
        return b;
    }

    public DestroyAfterEnemy(Vector position) {
        super(position, DEFAULT_SPEED, DEFAULT_SIZE, COLOR, getBehaviours());
    }
    
}
