package com.jackyjjc.aoe.world;

import com.jackyjjc.aoe.components.Attribute;
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
public class WorldViewPort implements EntityValueChangeListener {

    private World world;

    /*
     * Variables describes the view port.
     * These variables are measured in game units.
     */
    public Point topLeft;
    public int width;
    public int height;

    /*
     *
     */
    public int xOff;
    public int yOff;
    public int numXTiles;
    public int numYTiles;

    public WorldViewPort(int numXTiles, int numYTiles, World w) {

        this.world = w;
        this.topLeft = Point.get(0, 0);

        this.numXTiles = numXTiles;
        this.numYTiles = numYTiles;
        this.width = numXTiles * w.WORLD_TO_TILE_RATIO;
        this.height = numYTiles * w.WORLD_TO_TILE_RATIO;
    }

    public void follow(Entity playerShip) {
        playerShip.listenOn(Attribute.Location, this);
        adjustRegionLocation(playerShip);
    }

    public int[][] getTilesInRegion() {

        /*TODO: need one more some time*/

        int[][] tiles = new int[numYTiles + (yOff == 0 ? 0 : 1)][numXTiles + (xOff == 0 ? 0 : 1)];

        int tileX = world.convertWorldToTileX(topLeft);
        int tileY = world.convertWorldToTileY(topLeft);

        for (int y = 0; y < tiles.length; y++) {
            for(int x = 0; x < tiles[0].length; x++) {
                tiles[y][x] = world.getTile((tileX + x) % world.NUM_X_TILES, tileY + y);
            }
        }

        return tiles;
    }

    public int relativeDistX(int x) {

        int relDist = x - topLeft.x();

        if(relDist < 0) {
            relDist = x + (world.WIDTH - topLeft.x());
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
        int halfX = (width - 32) / 2;
        int halfY = (height - 32) / 2;

        if(p.x() - topLeft.x() != halfX) {
            this.topLeft.setX(p.x() - halfX);
        }

        if(p.y() - topLeft.y() != halfY) {
            this.topLeft.setY(p.y() - halfY);
        }

        this.xOff = topLeft.x() % world.WORLD_TO_TILE_RATIO;
        this.yOff = topLeft.y() % world.WORLD_TO_TILE_RATIO;
    }

}
