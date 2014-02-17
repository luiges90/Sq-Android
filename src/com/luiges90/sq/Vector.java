package com.luiges90.sq;

import java.io.Serializable;

public final class Vector implements Serializable {

    private static final long serialVersionUID = 4391253045238494933L;
    
    public final float x, y;
    public final float length;
    public final float angle; // in radians
    
    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
        this.length = (float) Math.sqrt(x * x + y * y);
        this.angle = (float) Math.atan2(y, x);
    }
    
    public static Vector fromTo(Vector from, Vector to) {
        return Vector.add(to, from.scale(-1));
    }
    
    public static Vector fromRT(float mag, float theta) {
        return new Vector(
                (float) (mag * Math.cos(theta)),
                (float) (mag * Math.sin(theta))
                );
    }
    
    public static float distanceSquared(Vector a, Vector b) {
        return (b.y - a.y) * (b.y - a.y) + (b.x - a.x) * (b.x - a.x); 
    }
    
    public static Vector add(Vector a, Vector b) {
        return new Vector(a.x + b.x, a.y + b.y);
    }
    
    public Vector scale(float s) {
        return new Vector(x * s, y * s);
    }
    
    public Vector normalize() {
        return new Vector(x / length, y / length);
    }
    
    public static Vector toDevicePosition(Vector p, int canvasSize) {
        return new Vector(
                toDevice(p.x + GameField.FIELD_SIZE, canvasSize), 
                toDevice(-p.y + GameField.FIELD_SIZE, canvasSize));
    }

    public static Vector fromDevicePosition(float x, float y, int canvasSize) {
        return new Vector(
                x / canvasSize * GameField.FIELD_SIZE * 2 - GameField.FIELD_SIZE,
                -y / canvasSize * GameField.FIELD_SIZE * 2 + GameField.FIELD_SIZE
                );
    }
    
    public static float toDevice(float val, int canvasSize) {
        return val / (GameField.FIELD_SIZE * 2) * canvasSize;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(x);
        result = prime * result + Float.floatToIntBits(y);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Vector))
            return false;
        Vector other = (Vector) obj;
        if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
            return false;
        if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Vector [x=" + x + ", y=" + y + ", length=" + length + ", angle=" + angle + "]";
    }

}
