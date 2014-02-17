package com.luiges90.sq.enemy;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import com.luiges90.sq.Vector;
import com.luiges90.sq.behaviour.Behaviour;
import com.luiges90.sq.behaviour.BulletAttracting;
import com.luiges90.sq.behaviour.HpTransparenting;

public class BlackBulletAttractingEnemy extends BaseEnemy {

    /**
     * 
     */
    private static final long serialVersionUID = -4745550777502266470L;
    public static final int COLOR = Color.HSVToColor(new float[] { 80, 0.9f, 0 });

    private static List<Behaviour> getBehaviours() {
        List<Behaviour> b = new ArrayList<Behaviour>();
        b.add(new HpTransparenting(15));
        b.add(new BulletAttracting(0.4f));
        return b;
    }

    public BlackBulletAttractingEnemy(Vector position) {
        super(position, DEFAULT_SPEED, DEFAULT_SIZE, COLOR, getBehaviours());
    }
    
}
