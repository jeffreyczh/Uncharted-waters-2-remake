package view;

import editor.MapEditor;
import game.Map;
import org.newdawn.slick.SlickException;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class PortMapPanel extends MapPanel {

    private final int maxX;
    private final int maxY;

    public PortMapPanel(int x, int y, int width, int height, Map map) throws SlickException {
        super(x, y, width, height, map);
        maxX = getMap().getWidth() - (width / MapEditor.SPRITE_SIZE);
        maxY = getMap().getHeight() - (height / MapEditor.SPRITE_SIZE);
    }

    public void moveX(double amount) {

        if(amount < 0) {
            mapX = Math.max(0, mapX + amount);
        } else {
            mapX = Math.min(maxX, mapX + amount);
        }
    }

    public void moveY(double amount) {
        if(amount < 0) {
            mapY = Math.max(0, mapY + amount);
        } else {
            mapY = Math.min(maxY, mapY + amount);
        }
    }
}
