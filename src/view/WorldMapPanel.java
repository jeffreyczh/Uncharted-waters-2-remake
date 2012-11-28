package view;

import game.GameMap;
import utils.ResourceManager;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class WorldMapPanel extends MapPanel {

    private final int maxY;

    public WorldMapPanel(int x, int y, int width, int height, GameMap gameMap) {
        super(x, y, width, height, gameMap);
        maxY = gameMap.getHeight() - (height / ResourceManager.SPRITE_SIZE);
    }

    public void moveX(double amount) {
        mapX += amount;
    }

    public void moveY(double amount) {
        if (amount < 0) {
            mapY = Math.max(0, mapY + amount);
        } else {
            mapY = Math.min(maxY, mapY + amount);
        }
    }
}
