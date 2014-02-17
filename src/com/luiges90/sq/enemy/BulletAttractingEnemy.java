package com.luiges90.sq.enemy;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import com.luiges90.sq.Vector;
import com.luiges90.sq.behaviour.Behaviour;
import com.luiges90.sq.behaviour.BulletAttracting;
import com.luiges90.sq.behaviour.Hp;

public class BulletAttractingEnemy extends BaseEnemy {

    /**
     * 
     */
    private static final long serialVersionUID = 3507582122162370581L;
    public static final int COLOR = Color.HSVToColor(new float[] { 80, 1, 0.5f });

    private static List<Behaviour> getBehaviours() {
        List<Behaviour> b = new ArrayList<Behaviour>();
        b.add(new Hp(10));
        b.add(new BulletAttracting(0.4f));
        return b;
    }

    public BulletAttractingEnemy(Vector position) {
        super(position, DEFAULT_SPEED, DEFAULT_SIZE, COLOR, getBehaviours());
    }
    
}
