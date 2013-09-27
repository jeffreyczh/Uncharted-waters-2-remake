package com.jackyjjc.aoe.game;

import com.jackyjjc.aoe.components.Attribute;
import com.jackyjjc.aoe.components.DirValues;
import com.jackyjjc.aoe.world.Point;
import com.jackyjjc.aoe.entites.Entity;
import com.jackyjjc.aoe.world.WorldEntityList;
import com.jackyjjc.aoe.world.WorldViewPort;
import com.jackyjjc.aoe.world.World;

import java.util.List;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class MovementController {

    private static final int TICKS_PER_HOUR = 15;
    private static final int UNITS_PER_KNOT = 5;

    private int tick;

    private World world;
    private WorldViewPort viewPort;
    private WorldEntityList worldEntityList;

    public MovementController(World world, WorldEntityList list, WorldViewPort viewPort) {
        this.world = world;
        this.viewPort = viewPort;
        this.worldEntityList = list;

        tick = 0;
    }

    public void update() {

        List<Entity> entities = worldEntityList.getShipsInViewPort(viewPort);
        for (Entity e : entities) {
            if(e.has(Attribute.Speed) && e.has(Attribute.Location) && e.has(Attribute.Direction) && tick == 0) {

                Point p = e.get(Attribute.Location, Point.class);
                DirValues dir = e.get(Attribute.Direction, DirValues.class);
                int knots = e.get(Attribute.Speed, Integer.class);
                int movement = knots * UNITS_PER_KNOT;
                double sin45 = Math.sin(Math.toRadians(45));

                /*FIXME: BUG, LOSS PRECISION*/
                int delta = movement / TICKS_PER_HOUR;
                int delta45 = (int) (sin45 * delta);

                Point dest = null;
                switch (dir) {
                    case UP:
                        dest = p.add(0, -delta);
                        break;
                    case DOWN:
                        dest = p.add(0, delta);
                        break;
                    case LEFT:
                        dest = p.add(-delta, 0);
                        break;
                    case RIGHT:
                        dest = p.add(delta, 0);
                        break;
                    case UPLEFT:
                        dest = p.add(-delta45, -delta45);
                        break;
                    case UPRIGHT:
                        dest = p.add(delta45, -delta45);
                        break;
                    case DOWNLEFT:
                        dest = p.add(-delta45, delta45);
                        break;
                    case DOWNRIGHT:
                        dest = p.add(delta45, delta45);
                        break;
                }

                if(isMovableTo(dest)) {
                    e.add(Attribute.Location, dest);
                    p.dispose();
                }
            }

            tick = (tick + 1) % 1;
        }
    }

    public boolean isMovableTo(Point p) {

        assert p != null;

        //System.out.println("========= Check ========");

        /*32 is the size of the ship, should define it in the ship property*/
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
