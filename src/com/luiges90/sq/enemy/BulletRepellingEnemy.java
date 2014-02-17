package com.luiges90.sq.enemy;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import com.luiges90.sq.Vector;
import com.luiges90.sq.behaviour.Behaviour;
import com.luiges90.sq.behaviour.BulletRepelling;

public class BulletRepellingEnemy extends BaseEnemy {

    /**
     * 
     */
    private static final long serialVersionUID = 2572901447166497257L;
    public static final int COLOR = Color.HSVToColor(new float[] { 90, 1, 0.5f });

    private static List<Behaviour> getBehaviours() {
        List<Behaviour> b = new ArrayList<Behaviour>();
        b.add(new BulletRepelling(0.4f));
        return b;
    }

    public BulletRepellingEnemy(Vector position) {
        super(position, DEFAULT_SPEED, DEFAULT_SIZE, COLOR, getBehaviours());
    }
    
}
