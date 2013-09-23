package com.jackyjjc.aoe.game;

import com.jackyjjc.aoe.components.Attribute;
import com.jackyjjc.aoe.components.Point;
import com.jackyjjc.aoe.entites.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an active region of the world
 * The world uses this active region to filter out tiles
 * and entities that are not in the region
 *
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class ActiveRegion {

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
    }

    public void moveXBy(int x) {
        this.topLeftX = wrapX(topLeftX + x, world.getWidth());
    }

    public void moveYBy(int y) {

        /*FIXME: BUG MAP SCROLLING DOWN NOT DOWN ENOUGH*/
        int rawResult = this.topLeftY + y;

        if(rawResult < 0) {
            this.topLeftY = 0;
        } else {
            this.topLeftY = Math.min(world.getHeight() - numY, rawResult);
        }
    }

    private int wrapX(int x, int limit) {

        x %= limit;
        if(x < 0) {
            x += limit;
        }

        return x;
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
                    || (0 <= x && x < wrapX(topLeftX + numX, world.getWidth()));
        }

        inside &= (topLeftY <= y && y < topLeftY + numY);

        return inside;
    }

    public int[][] getTilesInRegion() {

        int[][] tiles = new int[numY][numX];

        for (int y = 0; y < numY; y++) {
            for(int x = 0; x < numX; x++) {
                tiles[y][x] = world.getTile(wrapX(topLeftX + x, world.getWidth()), topLeftY + y);
            }
        }

        return tiles;
    }

}
