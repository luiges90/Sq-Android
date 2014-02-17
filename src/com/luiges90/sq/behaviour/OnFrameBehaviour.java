package com.luiges90.sq.behaviour;

import com.luiges90.sq.GameField;
import com.luiges90.sq.MovingEntity;

public interface OnFrameBehaviour extends Behaviour {

    public void onFrameUpdate(MovingEntity owner, GameField field);
    
}
