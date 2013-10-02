package com.jackyjjc.aoe.components;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public enum Direction {

    NORTH(0, -1),
    SOUTH(0, 1),
    WEST(-1, 0),
    EAST(1, 0),
    NORTHWEST(-1, -1),
    NORTHEAST(1, -1),
    SOUTHWEST(-1, 1),
    SOUTHEAST(1, 1);

    public final int dx;
    public final int dy;

    private Direction(int x, int y) {
        this.dx = x;
        this.dy = y;
    }
}
