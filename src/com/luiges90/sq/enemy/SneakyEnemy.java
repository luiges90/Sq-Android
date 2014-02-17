package com.luiges90.sq.enemy;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import com.luiges90.sq.Vector;
import com.luiges90.sq.behaviour.Behaviour;
import com.luiges90.sq.behaviour.Chasing;
import com.luiges90.sq.behaviour.Sneaky;

public class SneakyEnemy extends BaseEnemy {

    private static final long serialVersionUID = -4721094293577191060L;
    public static final int COLOR = Color.HSVToColor(new float[] { 130, 1, 0.5f });

    private static List<Behaviour> getBehaviours() {
        List<Behaviour> b = new ArrayList<Behaviour>();
        b.add(new Chasing(0.07f));
        b.add(new Sneaky(100));
        return b;
    }

    public SneakyEnemy(Vector position) {
        super(position, DEFAULT_SPEED / 2, DEFAULT_SIZE, COLOR, getBehaviours());
        this.setAlpha(0);
    }
    
}
