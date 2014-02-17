package com.luiges90.sq.enemy;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import com.luiges90.sq.Vector;
import com.luiges90.sq.behaviour.Behaviour;
import com.luiges90.sq.behaviour.Blinking;
import com.luiges90.sq.behaviour.Hp;

public class BlinkingEnemy extends BaseEnemy {

    /**
     * 
     */
    private static final long serialVersionUID = 6067457582999295614L;
    public static final int COLOR = Color.HSVToColor(new float[] { 60, 1, 0.5f });

    private static List<Behaviour> getBehaviours() {
        List<Behaviour> b = new ArrayList<Behaviour>();
        b.add(new Hp(5));
        b.add(new Blinking(30));
        return b;
    }

    public BlinkingEnemy(Vector position) {
        super(position, DEFAULT_SPEED, DEFAULT_SIZE, COLOR, getBehaviours());
    }
    
}
