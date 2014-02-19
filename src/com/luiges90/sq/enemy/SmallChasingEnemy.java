package com.luiges90.sq.enemy;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import com.luiges90.sq.Vector;
import com.luiges90.sq.behaviour.Behaviour;
import com.luiges90.sq.behaviour.Chasing;

public class SmallChasingEnemy extends BaseEnemy {

    /**
     * 
     */
    private static final long serialVersionUID = -6547741310873502139L;
    public static final int COLOR = Color.HSVToColor(new float[] { 10, 1, 0.5f });

    private static List<Behaviour> getBehaviours() {
        List<Behaviour> b = new ArrayList<Behaviour>();
        b.add(new Chasing(0.06f));
        return b;
    }

    public SmallChasingEnemy(Vector position) {
        super(position, DEFAULT_SPEED, DEFAULT_SIZE / 2, COLOR, getBehaviours());
    }

}
