package com.jackyjjc.aoe.world;

import com.badlogic.gdx.Gdx;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class World {

    public final int WIDTH;
    public final int HEIGHT;
    private final byte[][] mapData;

    public boolean isSea(Point p) {

        int tileX = convertWorldToTileX(p);
        int tileY = convertWorldToTileY(p);

        if(!containsTile(tileX, tileY)) {
            return false;
        }

        byte id = mapData[tileY][tileX];

        return id <= 33;
    }

    /*
     * The following part exposes the underlying tile-based structure to
     * the viewing and rendering component. The other components of the game
     * views the game as a continuous large map.
     */

    final int NUM_X_TILES;
    final int NUM_Y_TILES;
    final int WORLD_TO_TILE_RATIO;

    boolean containsTile(int tileX, int tileY) {
        return ((tileX >= 0 && tileX < NUM_X_TILES) && (tileY >= 0 && tileY < NUM_Y_TILES));
    }

    int getTile(int tileX, int tileY) {
        assert containsTile(tileX, tileY);
        return (mapData[tileY][tileX] & 0xff);
    }

    int convertWorldToTileX(Point p) {
        return p.x() / WORLD_TO_TILE_RATIO;
    }

    int convertWorldToTileY(Point p) {
        return p.y() / WORLD_TO_TILE_RATIO;
    }

    /* ============== Section End ============== */

    /*
     * The following part contains the formula and function for calculating
     * the geo-coordinate of a given world coordinate
     */
    private final int zeroLongitude;
    private final int newMapEnd;
    private final double tilesPerLongitude;
    private final double earthRadiusAsTile;

    public String getGeoCoordinate(Point p) {

        int tileX = convertWorldToTileX(p);
        int tileY = convertWorldToTileY(p);

        if(!containsTile(tileX, tileY)) {
            Gdx.app.log(getClass().getCanonicalName(), "accessing tile out of boundry");
            assert false;
        }

        String result;
        result = Math.round(Math.toDegrees(Math.atan2(Math.abs(tileY - NUM_Y_TILES / 2), earthRadiusAsTile)))
                + (tileY > (NUM_Y_TILES / 2) ? "s" : "n");
        result += " ";
        if(tileX >= zeroLongitude && tileX <= newMapEnd) {
            result += (int)(Math.abs(tileX - zeroLongitude) / tilesPerLongitude) + "e";
        } else {
            tileX = (tileX > newMapEnd) ? tileX - NUM_X_TILES : tileX;
            result += (int)(Math.abs(tileX - zeroLongitude) / tilesPerLongitude) + "w";
        }

        return result;
    }
    /* ============== Section End ============== */

    /*
     * The following part is the construction of the world object
     */
    World(byte[][] map, int tToW) {

        /*Init tile variables*/
        this.mapData = map;
        this.NUM_X_TILES = map[0].length;
        this.NUM_Y_TILES = map.length;
        this.WORLD_TO_TILE_RATIO = tToW;

        /*Init map variables*/
        this.WIDTH = NUM_X_TILES * WORLD_TO_TILE_RATIO;
        this.HEIGHT = NUM_Y_TILES * WORLD_TO_TILE_RATIO;

        /*Init geo variables*/
        /*FIXME: fix these magic numbers*/
        this.zeroLongitude = 180;
        this.newMapEnd = NUM_X_TILES - (NUM_X_TILES / 2 - zeroLongitude);
        this.tilesPerLongitude = NUM_X_TILES / 360.0;
        this.earthRadiusAsTile = Math.abs(260 - NUM_Y_TILES / 2) / Math.tan(Math.toRadians(51.5));

    }

}
