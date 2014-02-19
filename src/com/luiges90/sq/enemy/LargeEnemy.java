package com.luiges90.sq.enemy;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import com.luiges90.sq.Vector;
import com.luiges90.sq.behaviour.Behaviour;
import com.luiges90.sq.behaviour.Hp;

public class LargeEnemy extends BaseEnemy {

    /**
     * 
     */
    private static final long serialVersionUID = 355758299228090046L;
    public static final int COLOR = Color.HSVToColor(new float[] { 0, 1, 0.5f });

    private static List<Behaviour> getBehaviours() {
        List<Behaviour> b = new ArrayList<Behaviour>();
        b.add(new Hp(5));
        return b;
    }

    public LargeEnemy(Vector position) {
        super(position, DEFAULT_SPEED, DEFAULT_SIZE * 2, COLOR, getBehaviours());
    }

}
