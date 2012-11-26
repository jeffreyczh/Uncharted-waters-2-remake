package game;

import org.newdawn.slick.SpriteSheet;

import java.io.File;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class PortMap implements Map {

    private static final int WIDTH = 96;
    private static final int HEIGHT = 96;

    private short[][] map;
    private SpriteSheet sheet;

    public PortMap(short[][] map, SpriteSheet sheet) {
        this.map = map;
        this.sheet = sheet;
    }

    public void render(double mapX, double mapY, int x, int y, int screenWidth, int screenHeight) {

        int numRow = screenHeight / sheet.getSubImage(0,0).getHeight() + 1;
        int numCol = screenWidth / sheet.getSubImage(0,0).getWidth() + 1;

        int upperLeftX = (int) Math.floor(mapX);
        int upperLeftY = (int) Math.floor(mapY);

        for(int row = 0; row < numRow; row++) {
            for(int col = 0; col < numCol; col++) {

                if(upperLeftX + col < WIDTH && upperLeftY + row < HEIGHT) {

                    int id = map[row + upperLeftY][col + upperLeftX];
                    int sprite_row = id / 33;
                    int sprite_col = id % 33;

                    sheet.getSubImage(sprite_col, sprite_row).draw((x + col * 16),
                                                                   (y + row * 16));
                }
            }
        }
    }

    public void setTile(int col, int row, int id) {
        map[row][col] = (short)id;
    }

    public double wrapCol(double x) {
        return x;
    }

    public boolean isEnterable(int x, int y) {
        return false;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public int getWidth() {
        return WIDTH;
    }

    public short[][] getMap() {
        return map;
    }

}
