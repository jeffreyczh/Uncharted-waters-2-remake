package com.jackyjjc.aoe.entites;

import com.jackyjjc.aoe.components.Animator;
import com.jackyjjc.aoe.components.Direction;
import com.jackyjjc.aoe.world.Point;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class EntityFactory {

    public static Ship buildShip(int x, int y) {
        return new Ship(Point.get(x, y),
                        Direction.SOUTH,
                        new Animator("ship"));
    }
}
