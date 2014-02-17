package com.luiges90.sq.wave;

import java.util.List;

import com.luiges90.sq.GameField;
import com.luiges90.sq.enemy.BaseEnemy;

public interface WaveGenerator {

    public List<BaseEnemy> createWave(GameField field);
    
}
