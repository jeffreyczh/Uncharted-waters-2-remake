package view.mapPanels;

import game.GameMap;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class PortMapPanel extends MapPanel {

    private final int maxX;
    private final int maxY;

    public PortMapPanel(int x, int y, int width, int height, GameMap gameMap) {
        super(x, y, width, height, gameMap);
        maxX = getGameMap().getWidth() - (width / 16);
        maxY = getGameMap().getHeight() - (height / 16);
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
