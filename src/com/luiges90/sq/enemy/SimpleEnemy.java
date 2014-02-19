package com.luiges90.sq.enemy;

import java.util.ArrayList;

import android.graphics.Color;

import com.luiges90.sq.Vector;
import com.luiges90.sq.behaviour.Behaviour;

public class SimpleEnemy extends BaseEnemy {

    private static final long serialVersionUID = -2025092356925407674L;

    public static final int COLOR = Color.HSVToColor(new float[] { 0, 1, 0.5f });

    public SimpleEnemy(Vector position) {
        super(position, DEFAULT_SPEED, DEFAULT_SIZE, COLOR, new ArrayList<Behaviour>());
    }

    public SimpleEnemy(BaseEnemy owner) {
        super(owner.getPosition(), DEFAULT_SPEED, DEFAULT_SIZE, -1, COLOR,
                false, owner, new ArrayList<Behaviour>());
    }

}
