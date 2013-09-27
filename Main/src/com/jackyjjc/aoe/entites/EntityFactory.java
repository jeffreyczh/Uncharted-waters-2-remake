package com.jackyjjc.aoe.entites;

import com.jackyjjc.aoe.components.Animator;
import com.jackyjjc.aoe.components.DirValues;
import com.jackyjjc.aoe.world.Point;

import static com.jackyjjc.aoe.components.Attribute.*;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class EntityFactory {

    public static Entity buildShip(int x, int y) {
        return new Entity()
                .add(Location, Point.get(x, y))
                .add(Direction, DirValues.DOWN)
                .add(Animation, new Animator("ship"))
                .add(Speed, 13);
    }
}
