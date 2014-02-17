package com.luiges90.sq.enemy;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import com.luiges90.sq.Vector;
import com.luiges90.sq.behaviour.Behaviour;
import com.luiges90.sq.behaviour.Chasing;

public class DarkChasingEnemy extends BaseEnemy {

    private static final long serialVersionUID = 6046657256989261210L;

    public static final int COLOR = Color.HSVToColor(new float[] { 20, 1, 0.05f });

    private static List<Behaviour> getBehaviours() {
        List<Behaviour> b = new ArrayList<Behaviour>();
        b.add(new Chasing(0.04f));
        return b;
    }

    public DarkChasingEnemy(Vector position) {
        super(position, DEFAULT_SPEED, DEFAULT_SIZE, COLOR, getBehaviours());
    }

}
