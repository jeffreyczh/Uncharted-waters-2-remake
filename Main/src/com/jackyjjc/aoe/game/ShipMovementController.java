package com.jackyjjc.aoe.game;

import com.jackyjjc.aoe.entites.Ship;
import com.jackyjjc.aoe.world.Point;
import com.jackyjjc.aoe.world.World;
import com.jackyjjc.aoe.world.WorldEntityList;
import com.jackyjjc.aoe.world.WorldViewPort;

import java.util.List;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class ShipMovementController {

    private static final int TICKS_PER_HOUR = 15;
    private static final int UNITS_PER_KNOT = 5;

    private World world;
    private WorldViewPort viewPort;
    private WorldEntityList worldEntityList;

    public ShipMovementController(World world, WorldEntityList list, WorldViewPort viewPort) {

        this.world = world;
        this.viewPort = viewPort;
        this.worldEntityList = list;
    }

    public void update() {

        List<Ship> entities = worldEntityList.getShipsInViewPort(viewPort);
        for (Ship e : entities) {

            int movementThisHour = e.speed * UNITS_PER_KNOT + e.excessMovement;
            int movementThisTick = movementThisHour / TICKS_PER_HOUR;

            /*Save the excess movement that cannot be made this turn*/
            e.excessMovement = movementThisTick % TICKS_PER_HOUR;

            while(movementThisTick >= 1) {
                Point dest = e.location.add(e.direction.dx, e.direction.dy);

                if(!tryMoveTo(e, dest)) {
                    /*If it can move in two directions, try to move in only one of them*/
                    if(Math.abs(e.direction.dx) == Math.abs(e.direction.dy)) {
                        Point dest1 = e.location.add(e.direction.dx, 0);
                        Point dest2 = e.location.add(0, e.direction.dy);

                        /*tryMoveTo function actually change e.location, use short circuit to prevent multiple eval*/
                        boolean temp = tryMoveTo(e, dest1) || tryMoveTo(e, dest2);
                    }
                }
                movementThisTick--;
            }
        }
    }

    private boolean tryMoveTo(Ship ship, Point dest) {

        if(dest != ship.location && isMovableTo(dest)) {
            ship.location.dispose();
            ship.location = dest;
            return true;
        }

        return false;
    }

    private boolean isMovableTo(Point p) {

        assert p != null;

        /* 32 is the size of the ship, should define it in the ship property or JSON file */
        return isMovableToHelper(p, 0, 0)
               && isMovableToHelper(p, 16, 0)
               && isMovableToHelper(p, 31, 0)
               && isMovableToHelper(p, 0, 16)
               && isMovableToHelper(p, 0, 31)
               && isMovableToHelper(p, 31, 16)
               && isMovableToHelper(p, 16, 31)
               && isMovableToHelper(p, 16, 16)
               && isMovableToHelper(p, 31, 31);
    }

    private boolean isMovableToHelper(Point p, int dx, int dy) {

        boolean valid = Point.isValid(p.x() + dx, p.y() + dy);

        if(valid) {
            Point newP = p.add(dx, dy);
            valid &= world.isSea(newP);
            newP.dispose();
        }

        return valid;
    }
}
