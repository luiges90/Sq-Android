package com.luiges90.sq.enemy;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import com.luiges90.sq.GameView;
import com.luiges90.sq.behaviour.Behaviour;
import com.luiges90.sq.behaviour.Indestructible;

public class IndestructibleBullet extends BaseEnemy {

    /**
     * 
     */
    private static final long serialVersionUID = -3437162241288687700L;

    private static List<Behaviour> getBehaviours() {
        List<Behaviour> b = new ArrayList<Behaviour>();
        b.add(new Indestructible());
        return b;
    }

    public IndestructibleBullet(BaseEnemy owner) {
        super(owner.getPosition(), 0, DEFAULT_SIZE / 2, GameView.FPS,
                Color.rgb(Color.red(owner.getColor()), Color.green(owner.getColor()),
                        Color.blue(owner.getColor())),
                true, owner, getBehaviours());

    }

}
