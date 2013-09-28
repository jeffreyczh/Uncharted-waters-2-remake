package com.jackyjjc.aoe.world;

import com.jackyjjc.aoe.components.DirValues;

import java.util.LinkedList;
import java.util.List;

/**
 * If the x, y is out of range, the nearest valid point will be created
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public final class Point {

    /*Class initialization and methods*/
    private static int worldWidth;
    private static int worldHeight;
    private static List<Point> pool;

    public static void init(int w, int h) {
        assert (pool == null);
        worldWidth = w;
        worldHeight = h;
        pool = new LinkedList<>();
    }

    public static boolean isValid(int x, int y) {
        return y >= 0 && y <= worldHeight;
    }

    public static Point get(int x, int y) {

        Point p;

        if(!pool.isEmpty()) {
            p = pool.remove(0);
            p.x = x;
            p.y = y;
        } else {
            p = new Point(x, y);
        }

        p.setX(x);
        p.setY(y);

        return p;
    }

    private int x;
    private int y;

    private Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point add(int dx, int dy) {
        return get(this.x + dx, this.y + dy);
    }

    public void setX(int x) {
        this.x = wrapX(x);
    }

    public void setY(int y) {
        this.y = wrapY(y);
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    private int wrapX(int x) {

        x %= worldWidth;
        if(x < 0) {
            x += worldWidth;
        }

        return x;
    }

    private int wrapY(int y) {
        y = Math.max(y, 0);
        y = Math.min(y, worldHeight);
        return y;
    }

    @Override
    public int hashCode() {
        return (y << 16) ^ x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        if (y != point.y) return false;

        return true;
    }

    @Override
    public String toString() {
        return "Point: " + x + "," + y;
    }

    public void dispose() {
        pool.add(0, this);
    }
}
