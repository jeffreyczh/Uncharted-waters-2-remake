package com.jackyjjc.aoe.game;

import com.badlogic.gdx.Gdx;
import com.jackyjjc.aoe.entites.Entity;
import com.jackyjjc.aoe.entites.EntityFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class World {

    private static final int NUM_X_TILES = 2160;
    private static final int NUM_Y_TILES = 1080;
    private static final int TILE_TO_WORLD_RATIO = 16;

    private int worldWidth = NUM_X_TILES * TILE_TO_WORLD_RATIO;
    private int worldHeight = NUM_Y_TILES * TILE_TO_WORLD_RATIO;
    private final byte[][] mapData;

    private World(byte[][] map) {

        this.mapData = map;
        this.ships = new ArrayList<>();
        addShips();
    }

    public int getTile(int tileX, int tileY) {

        if(!containsTile(tileX, tileY)) {
            Gdx.app.log(getClass().getCanonicalName(), "accessing tile out of boundry");
            assert false;
        }

        return (mapData[tileY][tileX] & 0xff);
    }

    public boolean contains(int x, int y) {
        return (x >= 0 && x < worldWidth && y >= 0 && y < worldHeight);
    }

    public boolean containsTile(int tileX, int tileY) {
        return ((tileX >= 0 && tileX < NUM_X_TILES) && (tileY >= 0 && tileY < NUM_Y_TILES));
    }

    public boolean isSea(int x, int y) {

        int tileX = convertWorldToTileX(x);
        int tileY = convertWorldToTileY(y);

        if(!containsTile(tileX, tileY)) {
            Gdx.app.log(getClass().getCanonicalName(), "accessing tile out of boundry");
            assert false;
        }

        System.out.println(tileX + "," + tileY);

        return mapData[tileY][tileX] <= 33;
    }

    /* ======================================================================================================*/
    /* This Part of the class is for calculating the geo coordinate of a given world coordinate*/
    private static final int zeroLongitude = 180;
    private static final int newMapEnd = NUM_X_TILES - (NUM_X_TILES / 2 - zeroLongitude);
    private static final double tilesPerLongitude = NUM_X_TILES / 360.0;
    private static final double earthRadiusAsTile = Math.abs(260 - NUM_Y_TILES / 2) / Math.tan(Math.toRadians(51.5));

    public String getGeoCoordinate(int x, int y) {

        int tileX = convertWorldToTileX(x);
        int tileY = convertWorldToTileY(y);

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
    /* ======================================================================================================*/

    public static World buildWorld(File file) {

        World w = null;

        if(file.exists() && file.canRead()) {
            w = new World(readWorldMap(file));
        }

        return w;
    }

    private static byte[][] readWorldMap(File file) {

        byte[][] map = null;

        try {
            BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));

            map = new byte[NUM_Y_TILES][NUM_X_TILES];

            for (int row = 0; row < NUM_Y_TILES; row++) {

                if (input.read(map[row], 0, NUM_X_TILES) != NUM_X_TILES) {
                    throw new IOException();
                }
            }
            assert (input.read() == -1);
            input.close();

        } catch (IOException e) {
            Gdx.app.error("ReadWorldMap", "IOException when reading world map");
            assert false;
        }

        return map;
    }

    public int convertWorldToTileX(int x) {
        return x / TILE_TO_WORLD_RATIO;
    }

    public int convertWorldToTileY(int y) {
        return y / TILE_TO_WORLD_RATIO;
    }

    public int getTileToWorldRatio() {
        return TILE_TO_WORLD_RATIO;
    }

    public int getHeight() {
        return worldHeight;
    }

    public int getWidth() {
        return worldWidth;
    }

    public int getNumYTiles() {
        return NUM_Y_TILES;
    }

    public int getNumXTiles() {
        return NUM_X_TILES;
    }

    public int wrapX(int x, int limit) {

        x %= limit;
        if(x < 0) {
            x += limit;
        }

        return x;
    }

    /*This is messy*/

    public Entity playerShip;
    public List<Entity> ships;

    public void addShips() {
        this.playerShip = EntityFactory.buildShip(1600, 1600);
        ships.add(this.playerShip);
    }

}
