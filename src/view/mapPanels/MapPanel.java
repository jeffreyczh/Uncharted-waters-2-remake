package view.mapPanels;

import game.GameMap;
import org.newdawn.slick.geom.Rectangle;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public abstract class MapPanel {

    private GameMap gameMap;
    private Rectangle mapWindowBound;
    private int x;
    private int y;
    private int height;
    private int width;
    public double mapX;
    public double mapY;

    public MapPanel(int x, int y, int width, int height, GameMap gameMap) {
        this.gameMap = gameMap;
        mapWindowBound = new Rectangle(x, y, width, height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void render() {
        gameMap.render(mapX, mapY, x, y, width, height);
    }

    public Rectangle getMouseInteractBound() {
        return mapWindowBound;
    }

    public abstract void moveX(double amount);

    public abstract void moveY(double amount);

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public GameMap getGameMap() {
        return gameMap;
    }
}
