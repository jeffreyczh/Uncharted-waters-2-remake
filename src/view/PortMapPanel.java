package view;

import game.GameMap;
import utils.ResourceManager;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class PortMapPanel extends MapPanel {

    private final int maxX;
    private final int maxY;

    public PortMapPanel(int x, int y, int width, int height, GameMap gameMap) {
        super(x, y, width, height, gameMap);
        maxX = getGameMap().getWidth() - (width / ResourceManager.SPRITE_SIZE);
        maxY = getGameMap().getHeight() - (height / ResourceManager.SPRITE_SIZE);
    }

    public void moveX(double amount) {

        if (amount < 0) {
            mapX = Math.max(0, mapX + amount);
        } else {
            mapX = Math.min(maxX, mapX + amount);
        }
    }

    public void moveY(double amount) {
        if (amount < 0) {
            mapY = Math.max(0, mapY + amount);
        } else {
            mapY = Math.min(maxY, mapY + amount);
        }
    }
}
