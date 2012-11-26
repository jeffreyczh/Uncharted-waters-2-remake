package utils;

import game.Point;
import game.PortMap;
import game.World;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class MapManager {

    public enum MAP_TYPE {
        WORLD,
        PORT
    }

    public static final int WORLD_WIDTH = 2158;
    public static final int WORLD_HEIGHT = 1080;
    private static final int PORT_WIDTH = 96;
    private static final int PORT_HEIGHT = 96;
    private static final int LANDTITUTE = 7;
    private static final int LONGITUTE = 9;

    private Document ports;
    private Map<Point, Integer> portMap;

    private static final String PORTS_LOCATION = "asset/ports/";
    private static final String WORLD_MAP_LOC = "asset/map";
    private static final String WORLD_MAP_SPRITE_LOC = "asset/tiles.png";
    private static final String PORT_MAP_SPRITE_LOC = "asset/portTile.png";
    private static final int SPRITE_SIZE = 16;

    private static MapManager singletonObject = null;

    public static MapManager getInstance() {
        if(singletonObject == null) {
            singletonObject = new MapManager();
        }

        return singletonObject;
    }

    private MapManager() {
        readPorts(getFile(PORTS_LOCATION + "ports.xml"));
    }

    public File getDefaultWorldFile() {
        return getFile(WORLD_MAP_LOC);
    }

    public File getDefaultPortFile() {
        return getFile(PORTS_LOCATION + "port_" + 0);
    }

    public World buildWorld() throws SlickException {
        return buildWorld(getFile(WORLD_MAP_LOC));
    }

    public World buildWorld(File file) throws SlickException {
        return new World(readWorldMap(file),
                        new SpriteSheet(WORLD_MAP_SPRITE_LOC, SPRITE_SIZE, SPRITE_SIZE));
    }

    public PortMap buildPort(int id) throws SlickException {
        String portMapLocation = PORTS_LOCATION + "port_" + id;
        return new PortMap(readPortMap(getFile(portMapLocation)),
                        new SpriteSheet(PORT_MAP_SPRITE_LOC, SPRITE_SIZE, SPRITE_SIZE));
    }

    public PortMap buildPort(File file) throws SlickException {
        return new PortMap(readPortMap(file),
                new SpriteSheet(PORT_MAP_SPRITE_LOC, SPRITE_SIZE, SPRITE_SIZE));
    }

    private File getFile(String location) {

        String decodedPath = null;
        try {
            String path = World.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            path = path.substring(0, path.lastIndexOf('/'));
            decodedPath = URLDecoder.decode(path, "UTF-8");
            decodedPath = decodedPath + location;
            decodedPath = location;
        } catch (UnsupportedEncodingException e) {
            System.out.println("IO ERROR");
            System.exit(1);
        }

        return new File(decodedPath);
    }

    private byte[][] readWorldMap(File file) {

        byte[][] map = new byte[WORLD_HEIGHT][WORLD_WIDTH];

        try {
            BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));
            for(int row = 0; row < WORLD_HEIGHT; row++) {

                if(input.read(map[row]) != WORLD_WIDTH) {
                    throw new IOException();
                }
                for(int col = 0; col < WORLD_WIDTH; col++) {
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

    public void saveWorldMap(byte[][] map, File file) {
        try {
            BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(file));
            for(int row = 0; row < WORLD_HEIGHT; row++) {
                output.write(map[row]);
            }
            output.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (IOException e) {
            System.err.println("IO Error");
        }
    }

    public void savePortMap(short[][] map, File file) {
        try {
            DataOutputStream output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
            for(int row = 0; row < PORT_HEIGHT; row++) {
                for(int col = 0; col < PORT_WIDTH; col++) {
                    output.writeShort(map[row][col]);
                }
            }
            output.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (IOException e) {
            System.err.println("IO Error");
        }
    }

    private short[][] readPortMap(File file) {

        short[][] map = new short[PORT_HEIGHT][PORT_WIDTH];

        try {
            DataInputStream input = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
            for(int row = 0; row < PORT_HEIGHT; row++) {
                for(int col = 0; col < PORT_WIDTH; col++) {
                    map[row][col] = input.readShort();
                }
            }
            input.close();
        } catch (IOException e) {
            System.err.println("port IO error");
            System.exit(1);
        }

        return map;
    }

    private void readPorts(File file) {

        SAXReader reader = new SAXReader();
        try {
            ports = reader.read(file);
        } catch (DocumentException e) {
            System.err.println("Port List Reading Error");
            System.exit(1);
        }

        portMap = new HashMap<>();

        assert(ports != null);
        List<Node> portList = ports.selectNodes("/ports/port");
        System.out.println("num ports: " + portList.size());

        for(Node node : portList) {
            String[] portLoc = node.selectSingleNode("//location").getText().split(",");
            int x = Integer.parseInt(portLoc[0]);
            int y = Integer.parseInt(portLoc[1]);
            int portID = Integer.parseInt(node.selectSingleNode("//map_id").getText());

            portMap.put(new Point(x, y), portID);
            portMap.put(new Point(x + 1, y), portID);
            portMap.put(new Point(x + 1, y + 1), portID);
            portMap.put(new Point(x, y + 1), portID);
        }

    }
}
