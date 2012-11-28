package utils;

import game.*;
import game.buildings.Place;
import org.dom4j.Document;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class PortBuilder implements MapBuilder {

    public Map<Short, boolean[]> collisionMap;

    private PlaceFactory placeFactory;
    private NPCFactory npcFactory;
    private ResourceManager resourceManager;

    private static final String PORT_FILES_LOC = "asset/ports/";
    private static final String PORT_COLLISION_FILE = PORT_FILES_LOC + "portTileCollision";
    private static final String PORT_FILE = PORT_FILES_LOC + "port_";
    private static final int NUM_COLLIDABLE_DIR = 4;

    public PortBuilder(ResourceManager resourceManager, NPCFactory npcFactory, PlaceFactory placeFactory) {
        collisionMap = new HashMap<>();
        initPortCollisionMap();
        this.resourceManager = resourceManager;
        this.placeFactory = placeFactory;
        this.npcFactory = npcFactory;
    }

    public Port buildPort(int id) {
        Document portInfo = Utils.readXML(PORT_FILE + id + ".xml");
        assert (portInfo != null);

        List<CharacterUnit> npcs = npcFactory.getNPCs(portInfo.selectNodes("/ports/npcs"));
        Map<Point, Place> buildings = placeFactory.getBuildings(portInfo.selectNodes("/ports/buildings"));

        return new Port(readPortMap(Utils.getFile(PORT_FILE + id)), resourceManager.portMapSpriteSheet,
                collisionMap, npcs, buildings);
    }

    private short[][] readPortMap(File file) {

        short[][] map = new short[Port.HEIGHT][Port.WIDTH];

        try {
            DataInputStream input = new DataInputStream(
                    new BufferedInputStream(new FileInputStream(file)));
            for (int row = 0; row < Port.HEIGHT; row++) {
                for (int col = 0; col < Port.WIDTH; col++) {
                    map[row][col] = input.readShort();
                }
            }
            input.close();
        } catch (IOException e) {
            System.err.println("port map reading error");
            System.exit(1);
        }

        return map;
    }

    private void savePortMap(short[][] map, File file) {
        try {
            DataOutputStream output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
            for (int row = 0; row < Port.HEIGHT; row++) {
                for (int col = 0; col < Port.WIDTH; col++) {
                    output.writeShort(map[row][col]);
                }
            }
            output.close();
        } catch (FileNotFoundException e) {
            System.err.println("Port file for saving is not found");
        } catch (IOException e) {
            System.err.println("Error saving the port file");
        }
    }

    private void initPortCollisionMap() {
        try {
            Scanner scanner = new Scanner(new BufferedInputStream(
                    new FileInputStream(Utils.getFile(PORT_COLLISION_FILE))));

            //read in the collision types
            int numTypes = scanner.nextInt();
            boolean[][] collisionTypes = new boolean[numTypes][4];
            for (int i = 0; i < numTypes; i++) {
                for (int j = 0; j < NUM_COLLIDABLE_DIR; j++) {
                    collisionTypes[i][j] = scanner.nextBoolean();
                }
            }

            //read in the collidable tiles and their collision type
            while (scanner.hasNext()) {
                collisionMap.put((short) scanner.nextInt(), collisionTypes[scanner.nextInt()]);
            }
        } catch (FileNotFoundException e) {
            System.err.println("initialize collision map error");
            System.exit(1);
        }
    }

    public GameMap buildMap(File file) {
        return new Port(readPortMap(file), resourceManager.portMapSpriteSheet, collisionMap, null, null);
    }

    public void saveMap(GameMap gameMap, File file) {
        savePortMap(((Port) gameMap).getMap(), file);
    }

    public File getDefaultMapFile() {
        return Utils.getFile(PORT_FILE + "0");
    }
}
