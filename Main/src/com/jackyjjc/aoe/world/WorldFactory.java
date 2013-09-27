package com.jackyjjc.aoe.world;

import com.badlogic.gdx.Gdx;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * This is the static factory class which is in charge of constructing the world object
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class WorldFactory {

    /*TODO: move this into an external JSON file*/
    private static final int WORLD_TO_TILE_RATIO = 16;

    public static World buildWorld(File file) {

        World w = null;

        if(file.exists() && file.canRead()) {
            w = new World(readWorldMap(file), WORLD_TO_TILE_RATIO);
        }

        return w;
    }

    /**
     * This function reads the binary tile map from the file and returns
     * a 2D byte array representation of the map.
     *
     * @param file the binary world map file
     * @return 2d byte array world map
     */
    private static byte[][] readWorldMap(File file) {

        byte[][] map = null;

        try {
            BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));

            int width = getIntFromStream(input);
            int height = getIntFromStream(input);

            assert(width > 0 && height > 0 && width == 2160 && height == 1080);

            map = new byte[height][width];

            for (int row = 0; row < height; row++) {

                if (input.read(map[row], 0, width) != width) {
                    throw new IOException();
                }
            }
            input.close();

        } catch (IOException e) {
            Gdx.app.error("ReadWorldMap", "IOException when reading world map");
            assert false;
        }

        return map;
    }

    /**
     * This function read in the first four bytes from the input stream and
     * it turns those four bytes into an int and return back to the caller
     *
     * @param inputStream the input stream contains the integer store in bytes
     * @return the integer it reads from the stream
     * @throws IOException not enough bytes or stream io exception
     */
    private static int getIntFromStream(BufferedInputStream inputStream) throws IOException {

        int retVal;

        /* read in 4 bytes which would represent an integer */
        final int INT_SIZE = 4;
        byte[] intBuffer = new byte[INT_SIZE];

        if(inputStream.read(intBuffer, 0, INT_SIZE) == INT_SIZE) {
            retVal = ByteBuffer.wrap(intBuffer).getInt();
        } else {
            throw new IOException();
        }

        return retVal;
    }
}
