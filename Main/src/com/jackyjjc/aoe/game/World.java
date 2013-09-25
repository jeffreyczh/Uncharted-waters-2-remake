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

    private static final int WIDTH = 2160;
    private static final int HEIGHT = 1080;

    private final byte[][] mapData;

    private World(byte[][] map) {

        this.mapData = map;
        this.ships = new ArrayList<Entity>();
        addShips();
    }

    public int getTile(int x, int y) {

        if(!contains(x, y)) {
            Gdx.app.log(getClass().getCanonicalName(), "accessing tile out of boundry");
            assert false;
        }

        return (mapData[y][x] & 0xff);
    }

    public boolean contains(int x, int y) {
        return ((x >= 0 && x < WIDTH) && (y >= 0 && y < HEIGHT));
    }

    public boolean isSea(int x, int y) {
        if(!contains(x, y)) {
            Gdx.app.log(getClass().getCanonicalName(), "accessing tile out of boundry");
            assert false;
        }

        return mapData[y][x] <= 33;
    }

    private static final int zeroLongitude = 180;
    private static final int newMapEnd = WIDTH - (WIDTH / 2 - zeroLongitude);
    private static final double tilesPerLongitude = WIDTH / 360.0;
    private static final double earthRadiusAsTile = Math.abs(260 - HEIGHT / 2) / Math.tan(Math.toRadians(51.5));

    public String getGeoCoordinate(int x, int y) {

        if(!contains(x, y)) {
            Gdx.app.log(getClass().getCanonicalName(), "accessing tile out of boundry");
            assert false;
        }

        String result;
        result = Math.round(Math.toDegrees(Math.atan2(Math.abs(y - HEIGHT / 2), earthRadiusAsTile))) + (y > (HEIGHT / 2) ? "s" : "n");
        result += " ";
        if(x >= zeroLongitude && x <= newMapEnd) {
            result += (int)(Math.abs(x - zeroLongitude) / tilesPerLongitude) + "e";
        } else {
            x = (x > newMapEnd) ? x - WIDTH : x;
            result += (int)(Math.abs(x - zeroLongitude) / tilesPerLongitude) + "w";
        }

        return result;
    }

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

            map = new byte[HEIGHT][WIDTH];

            for (int row = 0; row < HEIGHT; row++) {

                if (input.read(map[row], 0, WIDTH) != WIDTH) {
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

    public int getHeight() {
        return HEIGHT;
    }

    public int getWidth() {
        return WIDTH;
    }

    public int wrapX(int x) {

        x %= WIDTH;
        if(x < 0) {
            x += WIDTH;
        }

        return x;
    }

    /*This is messy*/

    public Entity playerShip;
    public List<Entity> ships;

    public void addShips() {
        this.playerShip = EntityFactory.buildShip(100, 100);
        ships.add(this.playerShip);
    }

}
