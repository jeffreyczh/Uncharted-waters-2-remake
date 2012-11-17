package view;

import game.World;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class MapPanel {

    final World world;
    private Rectangle worldWindowBound;
    private int x;
    private int y;
    private int height;
    private int width;
    double worldX;
    double worldY;

    public MapPanel(int x, int y, int width, int height, SpriteSheet tiles) {
        world = new World(tiles);
        worldWindowBound = new Rectangle(x, y, width, height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void render() {
        world.render(worldX, worldY, x, y, width, height);
    }

    public Rectangle getMouseInteractBound() {
        return worldWindowBound;
    }

    public void moveX(double amount) {
        worldX += amount;
    }

    public void moveY(double amount) {
        if(amount < 0) {
            worldY = Math.max(0, worldY + amount);
        } else {
            worldY = Math.min(1049, worldY + amount);
        }
    }
}
