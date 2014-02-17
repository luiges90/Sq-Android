package com.luiges90.sq.enemy;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import com.luiges90.sq.Vector;
import com.luiges90.sq.behaviour.AimedFiring;
import com.luiges90.sq.behaviour.Behaviour;
import com.luiges90.sq.behaviour.Teleport;

public class TeleportAimedFiringEnemy extends BaseEnemy {

    /**
     * 
     */
    private static final long serialVersionUID = 1598803849624562016L;
    public static final int COLOR = Color.HSVToColor(new float[] { 170, 1, 0.5f });

    private static List<Behaviour> getBehaviours() {
        List<Behaviour> b = new ArrayList<Behaviour>();
        b.add(new Teleport(DEFAULT_FIRING_RATE));
        b.add(new AimedFiring(DEFAULT_FIRING_RATE, DEFAULT_BULLET_SPEED, 0,
                IndestructibleBullet.class));
        return b;
    }

    public TeleportAimedFiringEnemy(Vector position) {
        super(position, DEFAULT_SPEED, DEFAULT_SIZE, COLOR, getBehaviours());
    }
    
}
