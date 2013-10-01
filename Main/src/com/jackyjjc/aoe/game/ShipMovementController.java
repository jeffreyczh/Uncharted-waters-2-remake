package com.jackyjjc.aoe.game;

import com.jackyjjc.aoe.components.Direction;
import com.jackyjjc.aoe.entites.Ship;
import com.jackyjjc.aoe.world.Point;
import com.jackyjjc.aoe.world.WorldEntityList;
import com.jackyjjc.aoe.world.WorldViewPort;
import com.jackyjjc.aoe.world.World;

import java.util.List;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class ShipMovementController {

    private static final int TICKS_PER_HOUR = 15;
    private static final int UNITS_PER_KNOT = 5;

    private int tick;

    private World world;
    private WorldViewPort viewPort;
    private WorldEntityList worldEntityList;

    public ShipMovementController(World world, WorldEntityList list, WorldViewPort viewPort) {
        this.world = world;
        this.viewPort = viewPort;
        this.worldEntityList = list;

        tick = 0;
    }

    public void update() {

        List<Ship> entities = worldEntityList.getShipsInViewPort(viewPort);
        for (Ship e : entities) {

            Point p = e.location;
            Direction dir = e.direction;
            int knots = e.speed;
            int movement = knots * UNITS_PER_KNOT;
            double sin45 = Math.sin(Math.toRadians(45));

            /*FIXME: BUG, LOSS PRECISION*/
            int delta = movement / TICKS_PER_HOUR;
            int delta45 = (int) (sin45 * delta);
            double d = 0;
            Point dest = p;

            int i = 0;
            while(i < delta) {
                p = dest;
                d += sin45;
                switch (dir) {
                    case UP:
                        dest = p.add(0, -1);
                        break;
                    case DOWN:
                        dest = p.add(0, 1);
                        break;
                    case LEFT:
                        dest = p.add(-1, 0);
                        break;
                    case RIGHT:
                        dest = p.add(1, 0);
                        break;
                    case UPLEFT:
                        if(d >= 1) {
                            dest = p.add(-1, -1);
                            d -= 1;
                        }
                        break;
                    case UPRIGHT:
                        if(d >= 1) {
                            dest = p.add(1, -1);
                            d -= 1;
                        }
                        break;
                    case DOWNLEFT:
                        if(d >= 1) {
                            dest = p.add(-1, 1);
                            d -= 1;
                        }
                        break;
                    case DOWNRIGHT:
                        if(d >= 1) {
                            dest = p.add(1, 1);
                            d -= 1;
                        }
                        break;
                }

                if(isMovableTo(dest) && dest != p) {
                    e.location = dest;
                    p.dispose();
                }
                i++;
            }

            tick = (tick + 1) % 1;
        }
    }

    public boolean isMovableTo(Point p) {

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
