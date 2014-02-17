package com.luiges90.sq.enemy;

import java.util.ArrayList;

import android.graphics.Color;

import com.luiges90.sq.GameView;
import com.luiges90.sq.behaviour.Behaviour;

public class SimpleBullet extends BaseEnemy {

    private static final long serialVersionUID = -4805029530450141570L;

    public SimpleBullet(BaseEnemy owner) {
        super(owner.getPosition(), 0, DEFAULT_SIZE / 2, GameView.FPS,
                Color.rgb(Color.red(owner.getColor()), Color.green(owner.getColor()),
                        Color.blue(owner.getColor())),
                true, owner, new ArrayList<Behaviour>());

    }

}
