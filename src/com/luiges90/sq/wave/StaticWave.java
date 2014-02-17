package com.luiges90.sq.wave;

import java.util.ArrayList;
import java.util.List;

import com.luiges90.sq.GameField;
import com.luiges90.sq.Utility;
import com.luiges90.sq.Vector;
import com.luiges90.sq.enemy.AimedAccurateFastFiringEnemy;
import com.luiges90.sq.enemy.AimedAccurateFiringEnemy;
import com.luiges90.sq.enemy.AimedFiringEnemy;
import com.luiges90.sq.enemy.AimedFrequentFiringEnemy;
import com.luiges90.sq.enemy.AimedIndestructibleFiringEnemy;
import com.luiges90.sq.enemy.BaseEnemy;
import com.luiges90.sq.enemy.BlinkingEnemy;
import com.luiges90.sq.enemy.Boss1;
import com.luiges90.sq.enemy.Boss2;
import com.luiges90.sq.enemy.Boss3;
import com.luiges90.sq.enemy.Boss4;
import com.luiges90.sq.enemy.Boss5;
import com.luiges90.sq.enemy.BulletAttractingEnemy;
import com.luiges90.sq.enemy.BulletRepellingEnemy;
import com.luiges90.sq.enemy.BulletRepellingFiringEnemy;
import com.luiges90.sq.enemy.BurstFiringEnemy;
import com.luiges90.sq.enemy.ChasingEnemy;
import com.luiges90.sq.enemy.ChasingFiringEnemy;
import com.luiges90.sq.enemy.CounterAttackEnemy;
import com.luiges90.sq.enemy.CounterAttackIndestructibleEnemy;
import com.luiges90.sq.enemy.DarkChasingEnemy;
import com.luiges90.sq.enemy.DarkRandomFiringEnemy;
import com.luiges90.sq.enemy.DestroyAfterEnemy;
import com.luiges90.sq.enemy.DestroyTarget;
import com.luiges90.sq.enemy.ExplodeEnemy;
import com.luiges90.sq.enemy.FastEnemy;
import com.luiges90.sq.enemy.FastRandomFiringEnemy;
import com.luiges90.sq.enemy.GeneratingChasingEnemy;
import com.luiges90.sq.enemy.GeneratingEnemy;
import com.luiges90.sq.enemy.HpAimedFiringEnemy;
import com.luiges90.sq.enemy.HpChasingEnemy;
import com.luiges90.sq.enemy.HpEnemy;
import com.luiges90.sq.enemy.LargeEnemy;
import com.luiges90.sq.enemy.LargeFiringEnemy;
import com.luiges90.sq.enemy.MineLayerEnemy;
import com.luiges90.sq.enemy.RandomFiringEnemy;
import com.luiges90.sq.enemy.RandomFiringFrequentEnemy;
import com.luiges90.sq.enemy.RandomFiringSpreadEnemy;
import com.luiges90.sq.enemy.SimpleEnemy;
import com.luiges90.sq.enemy.SmallChasingEnemy;
import com.luiges90.sq.enemy.SmallEnemy;
import com.luiges90.sq.enemy.SneakyEnemy;
import com.luiges90.sq.enemy.SplitEnemy;
import com.luiges90.sq.enemy.TeleporCounterAttackEnemy;
import com.luiges90.sq.enemy.TeleportAimedFiringEnemy;
import com.luiges90.sq.enemy.TeleportOnHitEnemy;
import com.luiges90.sq.enemy.BlackBulletAttractingEnemy;

public class StaticWave implements WaveGenerator {
    
    public static final int WAVE_COUNT = 100;

    @SuppressWarnings("unchecked")
    private Class<BaseEnemy>[][] getWaveData(GameField f) {
        Class<BaseEnemy>[][] data = new Class[WAVE_COUNT][];

        data[0] = new Class[] {
                SimpleEnemy.class, SimpleEnemy.class, SimpleEnemy.class
        };
        data[1] = new Class[] {
                SimpleEnemy.class, SimpleEnemy.class,
                RandomFiringEnemy.class, RandomFiringEnemy.class
        };
        data[2] = new Class[] {
                ChasingEnemy.class, ChasingEnemy.class, ChasingEnemy.class
        };
        data[3] = new Class[] {
                SimpleEnemy.class, SimpleEnemy.class,
                ChasingEnemy.class, ChasingEnemy.class,
                RandomFiringEnemy.class, RandomFiringEnemy.class
        };
        data[4] = new Class[] {
                AimedFiringEnemy.class, AimedFiringEnemy.class, AimedFiringEnemy.class
        };
        data[5] = new Class[] {
                ChasingEnemy.class, ChasingEnemy.class,
                RandomFiringEnemy.class, RandomFiringEnemy.class,
                AimedFiringEnemy.class, AimedFiringEnemy.class
        };
        data[6] = new Class[] {
                HpEnemy.class, HpEnemy.class, HpEnemy.class, HpEnemy.class, HpEnemy.class
        };
        data[7] = new Class[] {
                HpEnemy.class, HpEnemy.class, HpEnemy.class,
                ChasingEnemy.class, ChasingEnemy.class,
                AimedFiringEnemy.class, AimedFiringEnemy.class
        };
        data[8] = new Class[] {
                LargeEnemy.class, LargeEnemy.class, LargeEnemy.class, LargeEnemy.class,
                LargeEnemy.class
        };
        data[9] = new Class[] {
                SmallEnemy.class, SmallEnemy.class, SmallEnemy.class, SmallEnemy.class,
                SmallEnemy.class
        };
        data[10] = new Class[] {
                LargeEnemy.class, LargeEnemy.class, LargeEnemy.class,
                SmallEnemy.class, SmallEnemy.class, SmallEnemy.class
        };
        data[11] = new Class[] {
                FastEnemy.class, FastEnemy.class, FastEnemy.class, FastEnemy.class
        };
        data[12] = new Class[] {
                LargeEnemy.class, LargeEnemy.class, SmallEnemy.class, SmallEnemy.class,
                FastEnemy.class, FastEnemy.class
        };
        data[13] = new Class[] {
                LargeEnemy.class, SmallEnemy.class, FastEnemy.class,
                ChasingEnemy.class, AimedFiringEnemy.class,
                ChasingEnemy.class, AimedFiringEnemy.class
        };
        data[14] = new Class[] {
                CounterAttackEnemy.class, CounterAttackEnemy.class,
                CounterAttackEnemy.class, CounterAttackEnemy.class
        };
        data[15] = new Class[] {
                CounterAttackEnemy.class, CounterAttackEnemy.class,
                AimedFiringEnemy.class, AimedFiringEnemy.class,
                RandomFiringEnemy.class, RandomFiringEnemy.class
        };
        data[16] = new Class[] {
                CounterAttackEnemy.class, CounterAttackEnemy.class, CounterAttackEnemy.class,
                LargeEnemy.class, LargeEnemy.class, LargeEnemy.class, LargeEnemy.class,
        };
        data[17] = new Class[] {
                AimedAccurateFiringEnemy.class, AimedAccurateFiringEnemy.class,
                AimedAccurateFiringEnemy.class, AimedAccurateFiringEnemy.class,
        };
        data[18] = new Class[] {
                AimedAccurateFiringEnemy.class, AimedAccurateFiringEnemy.class,
                ChasingEnemy.class, ChasingEnemy.class,
                FastEnemy.class, FastEnemy.class, FastEnemy.class
        };
        data[19] = new Class[] {
                Boss1.class
        };
        data[20] = new Class[] {
                TeleportOnHitEnemy.class, TeleportOnHitEnemy.class, TeleportOnHitEnemy.class,
                TeleportOnHitEnemy.class, TeleportOnHitEnemy.class,
        };
        data[21] = new Class[] {
                TeleportOnHitEnemy.class, TeleportOnHitEnemy.class, TeleportOnHitEnemy.class,
                AimedAccurateFiringEnemy.class, AimedAccurateFiringEnemy.class,
                AimedAccurateFiringEnemy.class
        };
        data[22] = new Class[] {
                BlinkingEnemy.class, BlinkingEnemy.class, BlinkingEnemy.class,
                BlinkingEnemy.class, BlinkingEnemy.class, BlinkingEnemy.class,
        };
        data[23] = new Class[] {
                BlinkingEnemy.class, BlinkingEnemy.class,
                ChasingEnemy.class, ChasingEnemy.class,
                AimedAccurateFiringEnemy.class, AimedAccurateFiringEnemy.class,
        };
        data[24] = new Class[] {
                HpChasingEnemy.class, HpChasingEnemy.class,
                HpChasingEnemy.class, HpChasingEnemy.class,
        };
        data[25] = new Class[] {
                ChasingFiringEnemy.class, ChasingFiringEnemy.class,
                ChasingFiringEnemy.class, ChasingFiringEnemy.class,
        };
        data[26] = new Class[] {
                HpChasingEnemy.class, HpChasingEnemy.class, HpChasingEnemy.class,
                ChasingFiringEnemy.class, ChasingFiringEnemy.class, ChasingFiringEnemy.class,
        };
        data[27] = new Class[] {
                HpChasingEnemy.class, HpChasingEnemy.class,
                ChasingFiringEnemy.class, ChasingFiringEnemy.class,
                TeleportOnHitEnemy.class, TeleportOnHitEnemy.class, TeleportOnHitEnemy.class,
        };
        data[28] = new Class[] {
                HpChasingEnemy.class, HpChasingEnemy.class,
                ChasingFiringEnemy.class, ChasingFiringEnemy.class,
                BlinkingEnemy.class, BlinkingEnemy.class, BlinkingEnemy.class,
        };
        data[29] = new Class[] {
                LargeEnemy.class, SmallEnemy.class, FastEnemy.class,
                BlinkingEnemy.class, TeleportOnHitEnemy.class,
                HpChasingEnemy.class, ChasingFiringEnemy.class
        };
        data[30] = new Class[] {
                CounterAttackIndestructibleEnemy.class,
                CounterAttackIndestructibleEnemy.class,
                CounterAttackIndestructibleEnemy.class,
                CounterAttackIndestructibleEnemy.class,
                CounterAttackIndestructibleEnemy.class,
                CounterAttackIndestructibleEnemy.class,
        };
        data[31] = new Class[] {
                CounterAttackIndestructibleEnemy.class, CounterAttackIndestructibleEnemy.class,
                BlinkingEnemy.class, BlinkingEnemy.class,
                TeleportOnHitEnemy.class, TeleportOnHitEnemy.class,
        };
        data[32] = new Class[] {
                CounterAttackIndestructibleEnemy.class, CounterAttackIndestructibleEnemy.class,
                HpChasingEnemy.class, HpChasingEnemy.class,
                AimedAccurateFiringEnemy.class, AimedAccurateFiringEnemy.class,
        };
        data[33] = new Class[] {
                RandomFiringFrequentEnemy.class, RandomFiringFrequentEnemy.class,
                RandomFiringFrequentEnemy.class, RandomFiringFrequentEnemy.class,
                RandomFiringFrequentEnemy.class, RandomFiringFrequentEnemy.class,
        };
        data[34] = new Class[] {
                RandomFiringSpreadEnemy.class, RandomFiringSpreadEnemy.class,
                RandomFiringSpreadEnemy.class, RandomFiringSpreadEnemy.class,
                RandomFiringSpreadEnemy.class, RandomFiringSpreadEnemy.class,
        };
        data[35] = new Class[] {
                HpAimedFiringEnemy.class, HpAimedFiringEnemy.class,
                HpAimedFiringEnemy.class, HpAimedFiringEnemy.class,
                HpAimedFiringEnemy.class, HpAimedFiringEnemy.class,
        };
        data[36] = new Class[] {
                RandomFiringFrequentEnemy.class, RandomFiringFrequentEnemy.class,
                RandomFiringFrequentEnemy.class,
                RandomFiringSpreadEnemy.class, RandomFiringSpreadEnemy.class,
                RandomFiringSpreadEnemy.class,
                HpAimedFiringEnemy.class, HpAimedFiringEnemy.class,
        };
        data[37] = new Class[] {
                RandomFiringFrequentEnemy.class, RandomFiringSpreadEnemy.class,
                HpAimedFiringEnemy.class,
                RandomFiringFrequentEnemy.class, RandomFiringSpreadEnemy.class,
                HpAimedFiringEnemy.class,
                TeleportOnHitEnemy.class, BlinkingEnemy.class,
                CounterAttackIndestructibleEnemy.class
        };
        data[38] = new Class[] {
                LargeEnemy.class, SmallEnemy.class, FastEnemy.class, 
                LargeEnemy.class, SmallEnemy.class, FastEnemy.class,
                TeleportOnHitEnemy.class,
                RandomFiringFrequentEnemy.class, RandomFiringFrequentEnemy.class,
                RandomFiringSpreadEnemy.class, RandomFiringSpreadEnemy.class,
        };
        data[39] = new Class[] {
                Boss2.class
        };
        data[40] = new Class[] {
                SplitEnemy.class, SplitEnemy.class, SplitEnemy.class,
                SplitEnemy.class, SplitEnemy.class, SplitEnemy.class,
        };
        data[41] = new Class[] {
                SplitEnemy.class, SplitEnemy.class, SplitEnemy.class,
                HpChasingEnemy.class, HpChasingEnemy.class,
                HpAimedFiringEnemy.class, HpAimedFiringEnemy.class,
        };
        data[42] = new Class[] {
                BulletAttractingEnemy.class, BulletAttractingEnemy.class,
                BulletAttractingEnemy.class, BulletAttractingEnemy.class,
                BulletAttractingEnemy.class, BulletAttractingEnemy.class,
                BulletAttractingEnemy.class, BulletAttractingEnemy.class,
        };
        data[43] = new Class[] {
                BulletRepellingEnemy.class, BulletRepellingEnemy.class,
                BulletRepellingEnemy.class, BulletRepellingEnemy.class,
                BulletRepellingEnemy.class, BulletRepellingEnemy.class,
                BulletRepellingEnemy.class, BulletRepellingEnemy.class,
        };
        data[44] = new Class[] {
                BulletAttractingEnemy.class, BulletAttractingEnemy.class,
                BulletAttractingEnemy.class, BulletAttractingEnemy.class,
                BulletRepellingEnemy.class, BulletRepellingEnemy.class,
                BulletRepellingEnemy.class, BulletRepellingEnemy.class,
        };
        data[45] = new Class[] {
                BulletAttractingEnemy.class, BulletAttractingEnemy.class,
                BulletAttractingEnemy.class, BulletAttractingEnemy.class,
                RandomFiringFrequentEnemy.class, RandomFiringFrequentEnemy.class,
                RandomFiringSpreadEnemy.class, RandomFiringSpreadEnemy.class,
        };
        data[46] = new Class[] {
                BulletRepellingEnemy.class, BulletRepellingEnemy.class, 
                BulletRepellingEnemy.class, BulletRepellingEnemy.class,
                TeleportOnHitEnemy.class, TeleportOnHitEnemy.class,
                CounterAttackIndestructibleEnemy.class, CounterAttackIndestructibleEnemy.class
        };
        data[47] = new Class[] {
                LargeFiringEnemy.class, LargeFiringEnemy.class, LargeFiringEnemy.class,
                LargeFiringEnemy.class, LargeFiringEnemy.class, LargeFiringEnemy.class,
        };
        data[48] = new Class[] {
                SmallChasingEnemy.class, SmallChasingEnemy.class, SmallChasingEnemy.class,
                SmallChasingEnemy.class, SmallChasingEnemy.class, SmallChasingEnemy.class,
                SmallChasingEnemy.class, SmallChasingEnemy.class, SmallChasingEnemy.class,
        };
        data[49] = new Class[] {
                LargeFiringEnemy.class, LargeFiringEnemy.class, LargeFiringEnemy.class,
                LargeFiringEnemy.class, LargeFiringEnemy.class,
                SmallChasingEnemy.class, SmallChasingEnemy.class, SmallChasingEnemy.class,
                SmallChasingEnemy.class, SmallChasingEnemy.class
        };
        data[50] = new Class[] {
                LargeFiringEnemy.class, LargeFiringEnemy.class, LargeFiringEnemy.class,
                TeleportOnHitEnemy.class, TeleportOnHitEnemy.class, TeleportOnHitEnemy.class, 
                SplitEnemy.class, SplitEnemy.class, SplitEnemy.class
        };
        data[51] = new Class[] {
                SmallChasingEnemy.class, SmallChasingEnemy.class, SmallChasingEnemy.class,
                HpAimedFiringEnemy.class, CounterAttackIndestructibleEnemy.class,
                HpAimedFiringEnemy.class, CounterAttackIndestructibleEnemy.class,
                BulletAttractingEnemy.class, BulletRepellingEnemy.class
        };
        data[52] = new Class[] {
                BlinkingEnemy.class, BlinkingEnemy.class, BlinkingEnemy.class,
                SmallChasingEnemy.class, SmallChasingEnemy.class, SmallChasingEnemy.class,
                SmallChasingEnemy.class, SmallChasingEnemy.class, SmallChasingEnemy.class,
                HpChasingEnemy.class, HpChasingEnemy.class,
        };
        data[53] = new Class[] {
                FastRandomFiringEnemy.class, FastRandomFiringEnemy.class,
                FastRandomFiringEnemy.class, FastRandomFiringEnemy.class,
                FastRandomFiringEnemy.class, FastRandomFiringEnemy.class,
                FastRandomFiringEnemy.class, FastRandomFiringEnemy.class,
        };
        data[54] = new Class[] {
                LargeFiringEnemy.class, LargeFiringEnemy.class,
                SmallChasingEnemy.class, SmallChasingEnemy.class,
                FastRandomFiringEnemy.class, FastRandomFiringEnemy.class,
                BlinkingEnemy.class, BlinkingEnemy.class,
                TeleportOnHitEnemy.class, TeleportOnHitEnemy.class
        };
        data[55] = new Class[] {
                FastRandomFiringEnemy.class, FastRandomFiringEnemy.class,
                FastRandomFiringEnemy.class, FastRandomFiringEnemy.class,
                CounterAttackIndestructibleEnemy.class, CounterAttackIndestructibleEnemy.class,
                CounterAttackIndestructibleEnemy.class, CounterAttackIndestructibleEnemy.class,
                BulletAttractingEnemy.class, BulletRepellingEnemy.class
        };
        data[56] = new Class[] {
                MineLayerEnemy.class, MineLayerEnemy.class, MineLayerEnemy.class,
                MineLayerEnemy.class, MineLayerEnemy.class, MineLayerEnemy.class,
                MineLayerEnemy.class, MineLayerEnemy.class, MineLayerEnemy.class,
        };
        data[57] = new Class[] {
                MineLayerEnemy.class, MineLayerEnemy.class, MineLayerEnemy.class,
                HpChasingEnemy.class, HpChasingEnemy.class,
                HpChasingEnemy.class, HpChasingEnemy.class,
                HpAimedFiringEnemy.class, HpAimedFiringEnemy.class,
                HpAimedFiringEnemy.class, HpAimedFiringEnemy.class,
        };
        data[58] = new Class[] {
                SplitEnemy.class, BulletAttractingEnemy.class, BulletRepellingEnemy.class,
                LargeFiringEnemy.class, SmallChasingEnemy.class, FastRandomFiringEnemy.class,
                MineLayerEnemy.class,
                SplitEnemy.class, BulletAttractingEnemy.class, BulletRepellingEnemy.class,
                LargeFiringEnemy.class, SmallChasingEnemy.class, FastRandomFiringEnemy.class,
                MineLayerEnemy.class,
                BlinkingEnemy.class, RandomFiringSpreadEnemy.class, TeleportOnHitEnemy.class
        };
        data[59] = new Class[] {
                Boss3.class
        };
        data[60] = new Class[] {
                GeneratingEnemy.class, GeneratingEnemy.class, GeneratingEnemy.class,
                GeneratingEnemy.class, GeneratingEnemy.class,
        };
        data[61] = new Class[] {
                GeneratingEnemy.class, GeneratingEnemy.class, GeneratingEnemy.class,
                SplitEnemy.class, SplitEnemy.class, SplitEnemy.class,
                ChasingFiringEnemy.class, ChasingFiringEnemy.class, SplitEnemy.class,
        };
        data[62] = new Class[] {
                DarkRandomFiringEnemy.class, DarkRandomFiringEnemy.class,
                DarkRandomFiringEnemy.class, DarkRandomFiringEnemy.class,
                DarkRandomFiringEnemy.class, DarkRandomFiringEnemy.class,
                DarkRandomFiringEnemy.class, DarkRandomFiringEnemy.class,
        };
        data[63] = new Class[] {
                DarkRandomFiringEnemy.class, DarkRandomFiringEnemy.class,
                DarkRandomFiringEnemy.class, DarkRandomFiringEnemy.class,
                BlinkingEnemy.class, BlinkingEnemy.class, BlinkingEnemy.class,
                RandomFiringFrequentEnemy.class,
                RandomFiringSpreadEnemy.class
        };
        data[64] = new Class[] {
                GeneratingEnemy.class, GeneratingEnemy.class, GeneratingEnemy.class,
                GeneratingEnemy.class, GeneratingEnemy.class,
                DarkRandomFiringEnemy.class, DarkRandomFiringEnemy.class,
                DarkRandomFiringEnemy.class, DarkRandomFiringEnemy.class,
                DarkRandomFiringEnemy.class,
        };
        data[65] = new Class[] {
                TeleporCounterAttackEnemy.class, TeleporCounterAttackEnemy.class,
                TeleporCounterAttackEnemy.class, TeleporCounterAttackEnemy.class,
                TeleporCounterAttackEnemy.class, TeleporCounterAttackEnemy.class,
                TeleporCounterAttackEnemy.class, TeleporCounterAttackEnemy.class,
        };
        data[66] = new Class[] {
                TeleporCounterAttackEnemy.class, TeleporCounterAttackEnemy.class,
                TeleporCounterAttackEnemy.class, TeleporCounterAttackEnemy.class,
                MineLayerEnemy.class, MineLayerEnemy.class, MineLayerEnemy.class,
                BulletRepellingEnemy.class, BulletAttractingEnemy.class
        };
        data[67] = new Class[] {
                TeleporCounterAttackEnemy.class, TeleporCounterAttackEnemy.class,
                TeleporCounterAttackEnemy.class, TeleporCounterAttackEnemy.class,
                DarkRandomFiringEnemy.class, DarkRandomFiringEnemy.class,
                GeneratingEnemy.class, GeneratingEnemy.class, GeneratingEnemy.class
        };
        data[68] = new Class[] {
                BurstFiringEnemy.class, BurstFiringEnemy.class, BurstFiringEnemy.class,
                BurstFiringEnemy.class, BurstFiringEnemy.class, BurstFiringEnemy.class,
                BurstFiringEnemy.class, BurstFiringEnemy.class,
        };
        data[69] = new Class[] {
                AimedFrequentFiringEnemy.class, AimedFrequentFiringEnemy.class,
                AimedFrequentFiringEnemy.class, AimedFrequentFiringEnemy.class,
                AimedFrequentFiringEnemy.class, AimedFrequentFiringEnemy.class,
                AimedFrequentFiringEnemy.class, AimedFrequentFiringEnemy.class,
        };
        data[70] = new Class[] {
                AimedIndestructibleFiringEnemy.class, AimedIndestructibleFiringEnemy.class,
                AimedIndestructibleFiringEnemy.class, AimedIndestructibleFiringEnemy.class,
                AimedIndestructibleFiringEnemy.class, AimedIndestructibleFiringEnemy.class,
                AimedIndestructibleFiringEnemy.class, AimedIndestructibleFiringEnemy.class,
        };
        data[71] = new Class[] {
                BurstFiringEnemy.class, BurstFiringEnemy.class, BurstFiringEnemy.class,
                AimedFrequentFiringEnemy.class, AimedFrequentFiringEnemy.class, 
                AimedFrequentFiringEnemy.class,
                AimedIndestructibleFiringEnemy.class, AimedIndestructibleFiringEnemy.class, 
                AimedIndestructibleFiringEnemy.class,
        };
        data[72] = new Class[] {
                BurstFiringEnemy.class, AimedFrequentFiringEnemy.class,
                AimedIndestructibleFiringEnemy.class,
                BurstFiringEnemy.class, AimedFrequentFiringEnemy.class,
                AimedIndestructibleFiringEnemy.class,
                HpChasingEnemy.class, HpChasingEnemy.class,
                TeleportOnHitEnemy.class, TeleportOnHitEnemy.class,
        };
        data[73] = new Class[] {
                BurstFiringEnemy.class, AimedFrequentFiringEnemy.class,
                AimedIndestructibleFiringEnemy.class,
                BurstFiringEnemy.class, AimedFrequentFiringEnemy.class,
                AimedIndestructibleFiringEnemy.class,
                BulletAttractingEnemy.class, BulletAttractingEnemy.class,
                BulletRepellingEnemy.class, BulletRepellingEnemy.class,
        };
        data[74] = new Class[] {
                BurstFiringEnemy.class, AimedFrequentFiringEnemy.class,
                AimedIndestructibleFiringEnemy.class,
                BurstFiringEnemy.class, AimedFrequentFiringEnemy.class,
                AimedIndestructibleFiringEnemy.class,
                FastRandomFiringEnemy.class,
                LargeFiringEnemy.class, SmallChasingEnemy.class,
                FastRandomFiringEnemy.class,
                LargeFiringEnemy.class, SmallChasingEnemy.class,
        };
        data[75] = new Class[] {
                SneakyEnemy.class, SneakyEnemy.class, SneakyEnemy.class,
                SneakyEnemy.class, SneakyEnemy.class, SneakyEnemy.class,
                SneakyEnemy.class, SneakyEnemy.class, SneakyEnemy.class,
                SneakyEnemy.class, SneakyEnemy.class, SneakyEnemy.class,
        };
        data[76] = new Class[] {
                SneakyEnemy.class, SneakyEnemy.class, SneakyEnemy.class, SneakyEnemy.class,
                BlinkingEnemy.class, BlinkingEnemy.class, BlinkingEnemy.class,
                BlinkingEnemy.class, BlinkingEnemy.class, BlinkingEnemy.class,
        };
        data[77] = new Class[] {
                SneakyEnemy.class, SneakyEnemy.class, SneakyEnemy.class, SneakyEnemy.class,
                MineLayerEnemy.class, MineLayerEnemy.class, MineLayerEnemy.class,
                MineLayerEnemy.class, MineLayerEnemy.class, MineLayerEnemy.class,
        };
        data[78] = new Class[] {
                GeneratingEnemy.class, DarkRandomFiringEnemy.class,
                TeleporCounterAttackEnemy.class,
                BurstFiringEnemy.class, AimedFrequentFiringEnemy.class,
                AimedIndestructibleFiringEnemy.class, SneakyEnemy.class,
                GeneratingEnemy.class, DarkRandomFiringEnemy.class,
                TeleporCounterAttackEnemy.class,
                BurstFiringEnemy.class, AimedFrequentFiringEnemy.class,
                AimedIndestructibleFiringEnemy.class, SneakyEnemy.class,
        };
        data[79] = new Class[] {
                Boss4.class
        };
        data[80] = new Class[] {
                DestroyAfterEnemy.class, DestroyAfterEnemy.class, DestroyAfterEnemy.class,
                DestroyAfterEnemy.class, DestroyAfterEnemy.class, DestroyAfterEnemy.class,
                DestroyTarget.class, DestroyTarget.class, DestroyTarget.class,
        };
        data[81] = new Class[] {
                DestroyAfterEnemy.class, DestroyAfterEnemy.class,
                DestroyTarget.class, DestroyTarget.class,
                DestroyAfterEnemy.class, DestroyAfterEnemy.class,
                DestroyTarget.class, DestroyTarget.class,
                LargeFiringEnemy.class, SmallChasingEnemy.class,
                BurstFiringEnemy.class, RandomFiringFrequentEnemy.class
        };
        data[82] = new Class[] {
                DarkChasingEnemy.class, DarkChasingEnemy.class, DarkChasingEnemy.class,
                DarkChasingEnemy.class, DarkChasingEnemy.class, DarkChasingEnemy.class,
                DarkChasingEnemy.class, DarkChasingEnemy.class, DarkChasingEnemy.class,
        };
        data[83] = new Class[] {
                BlackBulletAttractingEnemy.class, BlackBulletAttractingEnemy.class,
                BlackBulletAttractingEnemy.class, BlackBulletAttractingEnemy.class,
                BlackBulletAttractingEnemy.class, BlackBulletAttractingEnemy.class,
                BlackBulletAttractingEnemy.class, BlackBulletAttractingEnemy.class,
                BlackBulletAttractingEnemy.class, BlackBulletAttractingEnemy.class,
        };
        data[84] = new Class[] {
                DarkChasingEnemy.class, DarkChasingEnemy.class, 
                DarkRandomFiringEnemy.class, DarkRandomFiringEnemy.class,
                BlackBulletAttractingEnemy.class, BlackBulletAttractingEnemy.class,
                SneakyEnemy.class, SneakyEnemy.class, SneakyEnemy.class,
        };
        data[85] = new Class[] {
                DarkChasingEnemy.class, DarkChasingEnemy.class,
                DarkRandomFiringEnemy.class, DarkRandomFiringEnemy.class,
                BlackBulletAttractingEnemy.class, BlackBulletAttractingEnemy.class,
                CounterAttackIndestructibleEnemy.class, CounterAttackIndestructibleEnemy.class,
        };
        data[86] = new Class[] {
                GeneratingChasingEnemy.class, GeneratingChasingEnemy.class,
                GeneratingChasingEnemy.class, GeneratingChasingEnemy.class,
                GeneratingChasingEnemy.class, GeneratingChasingEnemy.class,
                GeneratingChasingEnemy.class, GeneratingChasingEnemy.class,
        };
        data[87] = new Class[] {
                GeneratingChasingEnemy.class, GeneratingChasingEnemy.class,
                GeneratingChasingEnemy.class, GeneratingChasingEnemy.class,
                DestroyAfterEnemy.class, DestroyAfterEnemy.class, DestroyAfterEnemy.class,
                DestroyTarget.class, DestroyTarget.class, DestroyTarget.class
        };
        data[88] = new Class[] {
                ExplodeEnemy.class, ExplodeEnemy.class, ExplodeEnemy.class,
                ExplodeEnemy.class, ExplodeEnemy.class, ExplodeEnemy.class,
                ExplodeEnemy.class, ExplodeEnemy.class, ExplodeEnemy.class,
        };
        data[89] = new Class[] {
                ExplodeEnemy.class, ExplodeEnemy.class, ExplodeEnemy.class,
                ExplodeEnemy.class, ExplodeEnemy.class, ExplodeEnemy.class,
                TeleportOnHitEnemy.class, TeleportOnHitEnemy.class,
                CounterAttackIndestructibleEnemy.class, CounterAttackIndestructibleEnemy.class,
        };
        data[90] = new Class[] {
                ExplodeEnemy.class, ExplodeEnemy.class, ExplodeEnemy.class,
                AimedIndestructibleFiringEnemy.class, AimedIndestructibleFiringEnemy.class, 
                AimedIndestructibleFiringEnemy.class,
                AimedFrequentFiringEnemy.class, AimedFrequentFiringEnemy.class,
                AimedFrequentFiringEnemy.class,
                FastRandomFiringEnemy.class, FastRandomFiringEnemy.class,
                FastRandomFiringEnemy.class,
        };
        data[91] = new Class[] {
                TeleportAimedFiringEnemy.class, TeleportAimedFiringEnemy.class,
                TeleportAimedFiringEnemy.class, TeleportAimedFiringEnemy.class,
                TeleportAimedFiringEnemy.class, TeleportAimedFiringEnemy.class,
                TeleportAimedFiringEnemy.class, TeleportAimedFiringEnemy.class,
                TeleportAimedFiringEnemy.class, TeleportAimedFiringEnemy.class,
                TeleportAimedFiringEnemy.class, TeleportAimedFiringEnemy.class,
        };
        data[92] = new Class[] {
                TeleportAimedFiringEnemy.class, TeleportAimedFiringEnemy.class,
                FastRandomFiringEnemy.class, FastRandomFiringEnemy.class,
                ExplodeEnemy.class, ExplodeEnemy.class,
                SplitEnemy.class, SplitEnemy.class,
                RandomFiringSpreadEnemy.class, RandomFiringSpreadEnemy.class, 
                BurstFiringEnemy.class, BurstFiringEnemy.class
        };
        data[93] = new Class[] {
                BulletRepellingFiringEnemy.class, BulletRepellingFiringEnemy.class,
                BulletRepellingFiringEnemy.class, BulletRepellingFiringEnemy.class,
                BulletRepellingFiringEnemy.class, BulletRepellingFiringEnemy.class,
                BulletRepellingFiringEnemy.class, BulletRepellingFiringEnemy.class,
                BulletRepellingFiringEnemy.class, BulletRepellingFiringEnemy.class,
        };
        data[94] = new Class[] {
                BulletRepellingFiringEnemy.class, BulletRepellingFiringEnemy.class,
                BulletRepellingFiringEnemy.class, BulletRepellingFiringEnemy.class,
                BlackBulletAttractingEnemy.class, BlackBulletAttractingEnemy.class,
                GeneratingChasingEnemy.class, GeneratingChasingEnemy.class,
                DestroyAfterEnemy.class, DestroyTarget.class,
                DestroyAfterEnemy.class, DestroyTarget.class,
        };
        data[95] = new Class[] {
                BulletRepellingFiringEnemy.class, BulletRepellingFiringEnemy.class,
                BlackBulletAttractingEnemy.class, BlackBulletAttractingEnemy.class,
                BlackBulletAttractingEnemy.class, BlackBulletAttractingEnemy.class,
                ExplodeEnemy.class, ExplodeEnemy.class,
                ExplodeEnemy.class, ExplodeEnemy.class,
                TeleportAimedFiringEnemy.class, TeleportAimedFiringEnemy.class, 
        };
        data[96] = new Class[] {
                AimedAccurateFastFiringEnemy.class, AimedAccurateFastFiringEnemy.class,
                AimedAccurateFastFiringEnemy.class, AimedAccurateFastFiringEnemy.class,
                AimedAccurateFastFiringEnemy.class, AimedAccurateFastFiringEnemy.class,
                AimedAccurateFastFiringEnemy.class, AimedAccurateFastFiringEnemy.class,
                AimedAccurateFastFiringEnemy.class, AimedAccurateFastFiringEnemy.class,
        };
        data[97] = new Class[] {
                AimedAccurateFastFiringEnemy.class, AimedAccurateFastFiringEnemy.class,
                AimedAccurateFastFiringEnemy.class, AimedAccurateFastFiringEnemy.class,
                AimedAccurateFastFiringEnemy.class, AimedAccurateFastFiringEnemy.class,
                ExplodeEnemy.class, ExplodeEnemy.class, ExplodeEnemy.class,
                DarkChasingEnemy.class, DarkChasingEnemy.class, DarkChasingEnemy.class,
        };
        data[98] = new Class[] {
                DestroyAfterEnemy.class, DestroyTarget.class, DarkChasingEnemy.class, 
                BlackBulletAttractingEnemy.class, GeneratingChasingEnemy.class, 
                ExplodeEnemy.class, TeleportAimedFiringEnemy.class, 
                BulletRepellingFiringEnemy.class, AimedAccurateFastFiringEnemy.class,
                DestroyAfterEnemy.class, DestroyTarget.class, DarkChasingEnemy.class, 
                BlackBulletAttractingEnemy.class, GeneratingChasingEnemy.class, 
                ExplodeEnemy.class, TeleportAimedFiringEnemy.class, 
                BulletRepellingFiringEnemy.class, AimedAccurateFastFiringEnemy.class
        };
        data[99] = new Class[] {
                Boss5.class
        };

        return data;
    }

    @Override
    public List<BaseEnemy> createWave(GameField field) {
        int wave = field.getWave() - 1;

        Class<BaseEnemy>[][] waveData = getWaveData(field);

        Class<BaseEnemy>[] enemies = waveData[wave % waveData.length];

        List<BaseEnemy> result = new ArrayList<BaseEnemy>();
        for (int i = 0; i < wave / waveData.length + 1; ++i) {
            for (int j = 0; j < enemies.length; ++j) {
                Class<BaseEnemy> clazz = enemies[j];

                Vector position = BaseEnemy.initPosition(field.getPlayerPosition());
                result.add(Utility.createEnemyFromClass(clazz, position));
            }
        }

        return result;
    }

}
