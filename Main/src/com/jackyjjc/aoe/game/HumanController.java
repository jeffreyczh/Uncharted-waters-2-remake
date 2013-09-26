package com.jackyjjc.aoe.game;

import com.jackyjjc.aoe.components.Attribute;
import com.jackyjjc.aoe.components.DirValues;
import com.jackyjjc.aoe.components.Point;
import com.jackyjjc.aoe.entites.Entity;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class HumanController {

    private World world;
    private Entity ship;

    public HumanController(World world, Entity ship) {
        this.world = world;
        this.ship = ship;
    }

    public void update(GameInput input) {

        checkAndMoveShip(input, GameInput.GameKey.UP, 0, -1, DirValues.UP);
        checkAndMoveShip(input, GameInput.GameKey.DOWN, 0, 1, DirValues.DOWN);
        checkAndMoveShip(input, GameInput.GameKey.LEFT, -1, 0, DirValues.LEFT);
        checkAndMoveShip(input, GameInput.GameKey.RIGHT, 1, 0, DirValues.RIGHT);
    }

    public void checkAndMoveShip(GameInput input, GameInput.GameKey key,
                                 int dx, int dy, DirValues dir) {

        Point p = ship.get(Attribute.Location, Point.class);

        if(input.isKeyDown(key) && isMovableTo(world.wrapX(p.x + dx, world.getWidth()), p.y + dy)) {

            p.x = world.wrapX(p.x + dx, world.getWidth());
            p.y += dy;
            System.out.println(p.x + " " + p.y);
            ship.add(Attribute.Direction, dir);
            ship.add(Attribute.Location, p);
        }
    }

    public boolean isMovableTo(int x, int y) {

        int newX = world.wrapX(x + 1, world.getWidth());
        int newY = y + 1;

        boolean valid = world.contains(x, y) && world.isSea(x, y);
        valid &= world.contains(newX, y) && world.isSea(newX, y);
        valid &= world.contains(x, newY) && world.isSea(x, newY);
        valid &= world.contains(newX, newY) && world.isSea(newX, newY);

        return valid;
    }
}
