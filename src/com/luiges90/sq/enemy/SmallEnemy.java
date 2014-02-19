package com.luiges90.sq.enemy;

import java.util.ArrayList;

import android.graphics.Color;

import com.luiges90.sq.Vector;
import com.luiges90.sq.behaviour.Behaviour;

public class SmallEnemy extends BaseEnemy {

    /**
     * 
     */
    private static final long serialVersionUID = 355758299228090046L;
    public static final int COLOR = Color.HSVToColor(new float[] { 0, 1, 0.5f });

    public SmallEnemy(Vector position) {
        super(position, DEFAULT_SPEED, DEFAULT_SIZE / 2, COLOR, new ArrayList<Behaviour>());
    }

}
