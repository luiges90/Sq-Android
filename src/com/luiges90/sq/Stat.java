package com.luiges90.sq;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.MapBuilder;
import com.luiges90.sq.enemy.BaseEnemy;

// SparseArrays are not Serializable, huh??
@SuppressLint("UseSparseArrays")
public class Stat implements Serializable {

    private static final long serialVersionUID = -6357197595813499660L;
    public static final String LOG_TAG = "sq_stat";
    public static final String FILENAME = "sq_stat";

    private Map<Class<? extends BaseEnemy>, Integer> killed, beKilled;
    private Map<Integer, Integer> clearedCount, dieCount;

    private static Stat instance;
    private transient Context context;

    public static Stat getInstance(Context context) {
        if (instance == null) {
            instance = new Stat(context);
            instance.load();
            instance.initIfNull();
        }
        return instance;
    }

    private void reset() {
        killed = new LinkedHashMap<Class<? extends BaseEnemy>, Integer>();
        beKilled = new LinkedHashMap<Class<? extends BaseEnemy>, Integer>();

        dieCount = new TreeMap<Integer, Integer>();
        clearedCount = new TreeMap<Integer, Integer>();
    }

    private void load() {
        File file = new File(context.getFilesDir(), FILENAME);
        ObjectInputStream ois = null;

        try {
            try {
                ois = new ObjectInputStream(new FileInputStream(file));
                Stat result = (Stat) ois.readObject();
                ois.close();

                killed = result.killed;
                beKilled = result.beKilled;
                dieCount = result.dieCount;
                clearedCount = result.clearedCount;
            } catch (IOException ex) {
                reset();
                if (ois != null) {
                    ois.close();
                }
            } catch (ClassNotFoundException e) {
                reset();
                if (ois != null) {
                    ois.close();
                }
            }
        } catch (IOException ex2) {
            Log.e(LOG_TAG, "Cannot close file", ex2);
        }
    }

    public void save() {
        File file = new File(context.getFilesDir(), FILENAME);
        ObjectOutputStream oos = null;

        try {
            try {
                oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(instance);
                oos.close();
            } catch (IOException ex) {
                reset();
                if (oos != null) {
                    oos.close();
                }
            }
        } catch (IOException ex2) {
            Log.e(LOG_TAG, "Cannot close file", ex2);
        }
    }

    private void initIfNull() {
        if (killed == null) {
            killed = new LinkedHashMap<Class<? extends BaseEnemy>, Integer>();
        }
        if (beKilled == null) {
            beKilled = new LinkedHashMap<Class<? extends BaseEnemy>, Integer>();
        }

        if (dieCount == null) {
            dieCount = new TreeMap<Integer, Integer>();
        }
        if (clearedCount == null) {
            clearedCount = new TreeMap<Integer, Integer>();
        }
    }

    private Stat(Context context) {
        this.context = context;
    }

    private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        stream.defaultReadObject();

        initIfNull();
    }

    public void addKilled(BaseEnemy e) {
        Class<? extends BaseEnemy> clazz = e.getClass();
        if (killed.containsKey(clazz)) {
            killed.put(clazz, killed.get(clazz) + 1);
        } else {
            killed.put(clazz, 1);
        }
        EasyTracker.getInstance(context).send(
                MapBuilder.createEvent("game", "killed enemy", clazz.getSimpleName(),
                        null)
                        .build());
    }

    public void addBeKilled(BaseEnemy e) {
        Class<? extends BaseEnemy> clazz = e.getClass();
        if (beKilled.containsKey(clazz)) {
            beKilled.put(clazz, beKilled.get(clazz) + 1);
        } else {
            beKilled.put(clazz, 1);
        }
        EasyTracker.getInstance(context).send(
                MapBuilder.createEvent("game", "destroyed by enemy", clazz.getSimpleName(),
                        null)
                        .build());
    }

    public void addDieInWave(int wave) {
        if (dieCount.containsKey(wave)) {
            dieCount.put(wave, dieCount.get(wave) + 1);
        } else {
            dieCount.put(wave, 1);
        }
        EasyTracker.getInstance(context).send(
                MapBuilder.createEvent("game", "died in wave", Integer.toString(wave),
                        null)
                        .build());
    }

    public void addCleared(int wave) {
        if (clearedCount.containsKey(wave)) {
            clearedCount.put(wave, clearedCount.get(wave) + 1);
        } else {
            clearedCount.put(wave, 1);
        }
        EasyTracker.getInstance(context).send(
                MapBuilder.createEvent("game", "cleared wave", Integer.toString(wave),
                        null)
                        .build());
    }

    public Map<Class<? extends BaseEnemy>, Integer> getKilled() {
        return killed;
    }

    public int getKilled(BaseEnemy e) {
        return getKilled(e.getClass());
    }

    public int getKilled(Class<? extends BaseEnemy> e) {
        if (killed.containsKey(e)) {
            return killed.get(e);
        } else {
            return 0;
        }
    }

    public Map<Class<? extends BaseEnemy>, Integer> getBeKilled() {
        return beKilled;
    }

    public int getBeKilled(BaseEnemy e) {
        return getBeKilled(e.getClass());
    }

    public int getBeKilled(Class<? extends BaseEnemy> e) {
        if (beKilled.containsKey(e)) {
            return beKilled.get(e);
        } else {
            return 0;
        }
    }

    public Map<Integer, Integer> getClearedWaveCounts() {
        return clearedCount;
    }

    public int getClearedInWave(int wave) {
        if (clearedCount.containsKey(wave)) {
            return clearedCount.get(wave);
        } else {
            return 0;
        }
    }

    public Map<Integer, Integer> getDieInWaveCounts() {
        return dieCount;
    }

    public int getDieInWave(int wave) {
        if (dieCount.containsKey(wave)) {
            return dieCount.get(wave);
        } else {
            return 0;
        }
    }

}
