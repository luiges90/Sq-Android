package com.luiges90.sq.enemy;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import com.luiges90.sq.Vector;
import com.luiges90.sq.behaviour.Behaviour;
import com.luiges90.sq.behaviour.Hp;

public class HpEnemy extends BaseEnemy {

    /**
     * 
     */
    private static final long serialVersionUID = -5394445544194385515L;
    public static final int COLOR = Color.HSVToColor(new float[] { 0, 1, 0.5f });

    private static List<Behaviour> getBehaviours() {
        List<Behaviour> b = new ArrayList<Behaviour>();
        b.add(new Hp(3));
        return b;
    }

    public HpEnemy(Vector position) {
        super(position, DEFAULT_SPEED, DEFAULT_SIZE, COLOR, getBehaviours());
    }

}
