package com.luiges90.sq.enemy;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import com.luiges90.sq.behaviour.Behaviour;
import com.luiges90.sq.behaviour.Sneaky;

public class Mine extends BaseEnemy {

    /**
     * 
     */
    private static final long serialVersionUID = -254411048261948487L;

    private static List<Behaviour> getBehaviours() {
        List<Behaviour> b = new ArrayList<Behaviour>();
        b.add(new Sneaky(100));
        return b;
    }

    public Mine(BaseEnemy owner) {
        super(owner.getPosition(), 0, DEFAULT_SIZE / 2, -1,
                Color.rgb(Color.red(owner.getColor()), Color.green(owner.getColor()),
                        Color.blue(owner.getColor())),
                true, owner, getBehaviours());

    }

}
