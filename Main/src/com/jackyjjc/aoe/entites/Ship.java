package com.jackyjjc.aoe.entites;

import com.jackyjjc.aoe.components.Animator;
import com.jackyjjc.aoe.components.Direction;
import com.jackyjjc.aoe.world.Point;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class Ship {

    public Point location;
    public Direction direction;
    public Animator animator;
    public int speed;
    public int excessMovement;

    public Ship(Point loc, Direction dir, Animator ani) {
        this.location = loc;
        this.direction = dir;
        this.animator = ani;
        this.speed = 8;
        this.excessMovement = 0;
    }


}
