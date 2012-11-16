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
    int worldX;
    int worldY;

    public MapPanel(SpriteSheet tiles) {
        world = new World(tiles);
        worldWindowBound = new Rectangle(0, 0, 640, 480);
    }

    public void render() {
        world.render(worldX, worldY, (int)worldWindowBound.getWidth(), (int)worldWindowBound.getHeight());
    }

    public Rectangle getMouseInteractBound() {
        return worldWindowBound;
    }

    public void moveX(int amount) {
        if(amount < 0) {
            worldX = Math.max(0, worldX + amount);
        } else {
            worldX = Math.min(2117, worldX + amount);
        }
    }

    public void moveY(int amount) {
        if(amount < 0) {
            worldY = Math.max(0, worldY + amount);
        } else {
            worldY = Math.min(1049, worldY + amount);
        }
    }
}
