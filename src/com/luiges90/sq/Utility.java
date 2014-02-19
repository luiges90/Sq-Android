package com.luiges90.sq;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;

import com.luiges90.sq.enemy.BaseEnemy;

public class Utility {

    private static final Random rng = new Random();

    private Utility() {
    }

    public static int dpToPx(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context
                .getResources()
                .getDisplayMetrics());
    }

    public static int randBetween(int lo, int hi) {
        if (lo > hi) {
            int t = hi;
            hi = lo;
            lo = t;
        }
        return rng.nextInt(hi - lo + 1) + lo;
    }

    public static float randBetween(float lo, float hi) {
        if (lo > hi) {
            float t = hi;
            hi = lo;
            lo = t;
        }
        return rng.nextFloat() * (hi - lo) + lo;
    }

    public static float randomAngle() {
        return randBetween(0, (float) (2 * Math.PI));
    }

    private static float doubleTriangleArea(Vector a, Vector b, Vector c) {
        return (c.x * b.y - b.x * c.y) - (c.x * a.y - a.x * c.y) + (b.x * a.y - a.x * b.y);
    }

    public static boolean isPointInSquare(Vector point, Vector sqCenter, float sqSize, float sqRot) {
        Vector a = new Vector((float) (Math.cos(Math.PI / 4 + sqRot) * sqSize + sqCenter.x),
                (float) (Math.cos(Math.PI * 3 / 4 + sqRot) * sqSize + sqCenter.y));
        Vector b = new Vector((float) (Math.cos(Math.PI * 3 / 4 + sqRot) * sqSize + sqCenter.x),
                (float) (Math.cos(Math.PI * 5 / 4 + sqRot) * sqSize + sqCenter.y));
        Vector c = new Vector((float) (Math.cos(Math.PI * 5 / 4 + sqRot) * sqSize + sqCenter.x),
                (float) (Math.cos(Math.PI * 7 / 4 + sqRot) * sqSize + sqCenter.y));
        Vector d = new Vector((float) (Math.cos(Math.PI * 7 / 4 + sqRot) * sqSize + sqCenter.x),
                (float) (Math.cos(Math.PI / 4 + sqRot) * sqSize + sqCenter.y));

        if (doubleTriangleArea(a, d, point) > 0 || doubleTriangleArea(d, c, point) > 0 ||
                doubleTriangleArea(c, b, point) > 0 || doubleTriangleArea(b, a, point) > 0) {
            return false;
        }
        return true;
    }

    public static BaseEnemy createEnemyFromClass(Class<? extends BaseEnemy> clazz, Vector position) {
        try {
            return clazz.getConstructor(Vector.class).newInstance(position);
        } catch (InstantiationException e) {
            Log.e("Wave Generator", "Failed to create instance of " + clazz.getName(), e);
        } catch (IllegalAccessException e) {
            Log.e("Wave Generator", "Failed to create instance of " + clazz.getName(), e);
        } catch (InvocationTargetException e) {
            Log.e("Wave Generator", "Failed to create instance of " + clazz.getName(), e);
        } catch (NoSuchMethodException e) {
            Log.e("Wave Generator", "Failed to create instance of " + clazz.getName(), e);
        }
        return null;
    }

    public static BaseEnemy createEnemyFromClass(Class<? extends BaseEnemy> clazz, BaseEnemy parent) {
        try {
            return clazz.getConstructor(BaseEnemy.class).newInstance(parent);
        } catch (InstantiationException e) {
            Log.e("Wave Generator", "Failed to create instance of " + clazz.getName(), e);
        } catch (IllegalAccessException e) {
            Log.e("Wave Generator", "Failed to create instance of " + clazz.getName(), e);
        } catch (InvocationTargetException e) {
            Log.e("Wave Generator", "Failed to create instance of " + clazz.getName(), e);
        } catch (NoSuchMethodException e) {
            Log.e("Wave Generator", "Failed to create instance of " + clazz.getName(), e);
        }
        return null;
    }

}
