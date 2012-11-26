package view;

import editor.MapEditor;
import game.Map;
import org.newdawn.slick.SlickException;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class WorldMapPanel extends MapPanel {

    private final int maxY;

    public WorldMapPanel(int x, int y, int width, int height, Map map) throws SlickException {
        super(x, y, width, height, map);
        maxY = getMap().getHeight() - (height / MapEditor.SPRITE_SIZE);
    }

    public void moveX(double amount) {
        mapX += amount;
    }

    public void moveY(double amount) {
        if(amount < 0) {
            mapY = Math.max(0, mapY + amount);
        } else {
            mapY = Math.min(maxY, mapY + amount);
        }
    }
}
