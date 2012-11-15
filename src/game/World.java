package game;

import org.newdawn.slick.SpriteSheet;

import java.awt.*;
import java.io.*;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class World {

    private static final int WIDTH = 2158;
    private static final int HEIGHT = 1080;
    private static final String LOCATION = "asset/map";

    private final byte[][] map;
    private SpriteSheet sheet;

    public World(SpriteSheet sheet) {
        map = new byte[HEIGHT][WIDTH];
        setSpriteSheet(sheet);

        try {
            BufferedInputStream input = new BufferedInputStream(new FileInputStream(LOCATION));
            for(int row = 0; row < HEIGHT; row++) {
                input.read(map[row]);
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.err.println("Map not found.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("IO error");
            System.exit(1);
        }
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

    public void setSpriteSheet(SpriteSheet sheet) {
        this.sheet = sheet;
    }

    public void save() {
        try {
            BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(LOCATION));
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
