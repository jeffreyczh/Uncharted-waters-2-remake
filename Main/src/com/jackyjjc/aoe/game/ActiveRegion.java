package com.jackyjjc.aoe.game;

import com.jackyjjc.aoe.components.Attribute;
import com.jackyjjc.aoe.components.Point;
import com.jackyjjc.aoe.entites.Entity;
import com.jackyjjc.aoe.entites.EntityValueChangeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an active region of the world
 * The world uses this active region to filter out tiles
 * and entities that are not in the region
 *
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class ActiveRegion implements EntityValueChangeListener {

    private World world;

    /*These is like a view port except it is not measured in pixel but units in game world*/
    public int topLeftX;
    public int topLeftY;
    public int numX;
    public int numY;

    public ActiveRegion(int numX, int numY, World w) {

        this.world = w;

        this.numX = numX;
        this.numY = numY;

        w.playerShip.listenOn(Attribute.Location, this);
        adjustRegionLocation(w.playerShip);
    }

    public List<Entity> getShipsInRange() {

        List<Entity> list = new ArrayList<Entity>();

        for (Entity e : world.ships) {

            if(!e.has(Attribute.Location)) {
                continue;
            }

            Point p = e.get(Attribute.Location, Point.class);
            if(inWindowRange(p.x, p.y)) {
                list.add(e);
            }
        }

        return list;
    }

    public boolean inWindowRange(int x, int y) {

        boolean inside;

        /*normal case*/
        if(topLeftX + numX < world.getWidth()) {

            inside = (topLeftX <= x && x < topLeftX + numX);

        /*need to divide it into two parts to check*/
        } else {
            inside = (topLeftX <= x && x < world.getWidth())
                    || (0 <= x && x < world.wrapX(topLeftX + numX));
        }

        inside &= (topLeftY <= y && y < topLeftY + numY);

        return inside;
    }

    public int[][] getTilesInRegion() {

        int[][] tiles = new int[numY][numX];

        for (int y = 0; y < numY; y++) {
            for(int x = 0; x < numX; x++) {
                tiles[y][x] = world.getTile(world.wrapX(topLeftX + x), topLeftY + y);
            }
        }

        return tiles;
    }

    public int relativeDistX(int x) {

        int relDist = x - topLeftX;

        if(relDist < 0) {
            relDist = x + (world.getWidth() - topLeftX);
        }

        return relDist;
    }

    @Override
    public void notifyChange(Entity e, Attribute a) {

        if(a == Attribute.Location) {
            adjustRegionLocation(e);
        }
    }

    public void adjustRegionLocation(Entity ship) {

        Point p  = ship.get(Attribute.Location, Point.class);
        int halfX = (numX - 2) / 2;
        int halfY = (numY - 2) / 2;

        if(p.x - topLeftX != halfX) {
            topLeftX = world.wrapX(p.x - halfX);
        }

        if(p.y - topLeftY != halfY) {
            topLeftY = Math.max(p.y - halfY, 0);
        }
    }
}
