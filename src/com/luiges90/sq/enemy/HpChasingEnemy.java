package com.luiges90.sq.enemy;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import com.luiges90.sq.Vector;
import com.luiges90.sq.behaviour.Behaviour;
import com.luiges90.sq.behaviour.Chasing;
import com.luiges90.sq.behaviour.Hp;

public class HpChasingEnemy extends BaseEnemy {

    /**
     * 
     */
    private static final long serialVersionUID = -4357852324430984526L;
    public static final int COLOR = Color.HSVToColor(new float[] { 10, 0.9f, 0.5f });

    private static List<Behaviour> getBehaviours() {
        List<Behaviour> b = new ArrayList<Behaviour>();
        b.add(new Chasing(0.06f));
        b.add(new Hp(5));
        return b;
    }

    public HpChasingEnemy(Vector position) {
        super(position, DEFAULT_SPEED, DEFAULT_SIZE, COLOR, getBehaviours());
    }
    
}
