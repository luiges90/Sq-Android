package com.luiges90.sq.enemy;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import com.luiges90.sq.Vector;
import com.luiges90.sq.behaviour.Behaviour;
import com.luiges90.sq.behaviour.Hp;
import com.luiges90.sq.behaviour.Split;

public class SplitEnemy extends BaseEnemy {

    /**
     * 
     */
    private static final long serialVersionUID = -2596715764244647192L;
    public static final int COLOR = Color.HSVToColor(new float[] { 70, 1, 0.5f });

    private static List<Behaviour> getBehaviours() {
        List<Behaviour> b = new ArrayList<Behaviour>();
        b.add(new Hp(3));
        b.add(new Split(5, HpEnemy.class));
        return b;
    }

    public SplitEnemy(Vector position) {
        super(position, DEFAULT_SPEED, DEFAULT_SIZE, COLOR, getBehaviours());
    }
}
