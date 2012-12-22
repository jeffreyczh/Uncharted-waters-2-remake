package utils;

import game.*;

import java.io.*;
import java.util.Map;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class WorldBuilder implements MapBuilder {

    public static final int WORLD_WIDTH = 2158;
    public static final int WORLD_HEIGHT = 1080;
    private static final int LANDTITUTE = 7;
    private static final int LONGITUTE = 9;

    private static final String PORTS_LOCATION = "asset/ports/";
    private static final String WORLD_MAP_LOC = "asset/map";

    private MapTransitionController mapTransitionController;
    private ResourceManager resourceManager;
    private Map<Point, Integer> ports;

    public WorldBuilder(ResourceManager resourceManager, PlaceFactory placeFactory,
                                                         MapTransitionController mapTransitionController) {
        this.resourceManager = resourceManager;
        this.mapTransitionController = mapTransitionController;
        this.ports = placeFactory.getPorts(PORTS_LOCATION + "ports.xml");
    }

    public World buildWorld() {
        return (World) buildMap(getDefaultMapFile());
    }

    private byte[][] readWorldMap(File file) {

        byte[][] map = new byte[WORLD_HEIGHT][WORLD_WIDTH];

        try {
            BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));
            for (int row = 0; row < WORLD_HEIGHT; row++) {

                if (input.read(map[row]) != WORLD_WIDTH) {
                    throw new IOException();
                }
                for (int col = 0; col < WORLD_WIDTH; col++) {
                    map[row][col] = (byte) (map[row][col] & 0xFF);
                }
            }
            input.close();
        } catch (IOException e) {
            System.err.println("IO error");
            System.exit(1);
        }

        return map;
    }

    private void saveWorldMap(byte[][] map, File file) {

        try {
            BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(file));
            for (int row = 0; row < WORLD_HEIGHT; row++) {
                output.write(map[row]);
            }
            output.close();
        } catch (FileNotFoundException e) {
            System.err.println("World map file is not found");
        } catch (IOException e) {
            System.err.println("Error Saving the world map");
        }
    }

    public GameMap buildMap(File file) {
        return new World(readWorldMap(file), resourceManager.spriteMap.get("WORLD_TILES"), ports, mapTransitionController);
    }

    public void saveMap(GameMap gameMap, File file) {
        saveWorldMap(((World) gameMap).getMap(), file);
    }

    public File getDefaultMapFile() {
        return Utils.getFile(WORLD_MAP_LOC);
    }
}
