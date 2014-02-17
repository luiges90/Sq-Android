package com.luiges90.sq.enemy;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import com.luiges90.sq.GameView;
import com.luiges90.sq.behaviour.Behaviour;
import com.luiges90.sq.behaviour.Hp;

public class LargeBullet extends BaseEnemy {

    /**
     * 
     */
    private static final long serialVersionUID = -6692591615511400363L;
    
    private static List<Behaviour> getBehaviours() {
        List<Behaviour> b = new ArrayList<Behaviour>();
        b.add(new Hp(3));
        return b;
    }

    public LargeBullet(BaseEnemy owner) {
        super(owner.getPosition(), 0, DEFAULT_SIZE, GameView.FPS,
                Color.rgb(Color.red(owner.getColor()), Color.green(owner.getColor()),
                        Color.blue(owner.getColor())),
                true, owner, getBehaviours());

    }
    
}
