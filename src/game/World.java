package game;

import org.newdawn.slick.SpriteSheet;

import java.awt.*;
import java.io.*;
import java.net.URLDecoder;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class World {

    public static final int OUT_OF_BOUND = -1;

    private static final int WIDTH = 2158;
    private static final int HEIGHT = 1080;
    private static final String LOCATION = "asset/map";

    private final byte[][] map;
    private SpriteSheet sheet;

    public World(SpriteSheet sheet) {
        map = new byte[HEIGHT][WIDTH];
        setSpriteSheet(sheet);
        read(getDefaultMapFile());
    }

    public void render(int x, int y, int screenWidth, int screenHeight) {

        int numRow = screenHeight / sheet.getSubImage(0,0).getHeight();
        int numCol = screenWidth / sheet.getSubImage(0,0).getWidth();

        for(int row = 0; row < numRow; row++) {
            for(int col = 0; col < numCol; col++) {
                int id = map[row + y][col + x] & 0xff;
                int sprite_row = id / 10;
                int sprite_col = id % 10;
                sheet.getSubImage(sprite_col, sprite_row).draw(col * 16, row * 16);
            }
        }
    }

    public int getTile(int col, int row) {

        if(col < 0 || col >= WIDTH || row < 0 || row >= HEIGHT) {
            return OUT_OF_BOUND;
        } else {
            return (map[row][col] & 0xff);
        }
    }

    public void setTile(int col, int row, byte id) {
        map[row][col] = id;
    }

    public void setSpriteSheet(SpriteSheet sheet) {
        this.sheet = sheet;
    }

    public void read(File file) {
        try {
            read(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            System.err.println("Map not found.");
            System.exit(1);
        }
    }

    public void read(InputStream file) {
        try {
            BufferedInputStream input = new BufferedInputStream(file);
            for(int row = 0; row < HEIGHT; row++) {
                if(input.read(map[row]) != WIDTH) {
                    throw new IOException();
                }
            }
            input.close();
        } catch (IOException e) {
            System.err.println("IO error");
            System.exit(1);
        }
    }

    private File getDefaultMapFile() {

        String decodedPath = null;
        /*try {
            String path = World.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            path = path.substring(0, path.lastIndexOf('/'));
            decodedPath = URLDecoder.decode(path, "UTF-8");
            decodedPath = decodedPath + LOCATION;
            decodedPath = LOCATION;
        } catch (UnsupportedEncodingException e) {
            System.out.println("IO ERROR");
            System.exit(1);
        }*/
        decodedPath = LOCATION;

        return new File(decodedPath);
    }

    public void save() {
        save(getDefaultMapFile());
    }

    public void save(File file) {
        try {
            BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(file));
            for(int row = 0; row < HEIGHT; row++) {
                output.write(map[row]);
            }
            output.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (IOException e) {
            System.err.println("IO Error");
        }
    }
}
