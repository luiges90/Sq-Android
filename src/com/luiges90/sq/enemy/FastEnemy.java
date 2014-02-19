package com.luiges90.sq.enemy;

import java.util.ArrayList;

import android.graphics.Color;

import com.luiges90.sq.Vector;
import com.luiges90.sq.behaviour.Behaviour;

public class FastEnemy extends BaseEnemy {

    /**
     * 
     */
    private static final long serialVersionUID = 355758299228090046L;
    public static final int COLOR = Color.HSVToColor(new float[] { 0, 0.9f, 0.5f });

    public FastEnemy(Vector position) {
        super(position, DEFAULT_SPEED * 3 / 2, DEFAULT_SIZE, COLOR, new ArrayList<Behaviour>());
    }

}
