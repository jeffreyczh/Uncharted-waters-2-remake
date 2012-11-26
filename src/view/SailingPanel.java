package view;

import game.Direction;
import game.Player;
import game.World;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import utils.MapManager;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class SailingPanel extends Panel {

    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;
    private static final int NUM_ROW = HEIGHT / 16;

    private Player player;
    private MapPanel mapPanel;

    public SailingPanel(int x, int y) throws SlickException {
        super(x, y);
        mapPanel = new WorldMapPanel(x, y, WIDTH, HEIGHT, MapManager.getInstance().buildWorld());

    }

    public void render() {
        mapPanel.render();
        player.render(x, y, mapPanel.mapX, mapPanel.mapY);
    }

    public void update(Input input) {

        if(!(input.isMouseButtonDown(0))) {

            double oldX = player.getX();
            double oldY = player.getY();

            if(input.isKeyDown(Input.KEY_W)) player.move(Direction.UP, mapPanel);
            if(input.isKeyDown(Input.KEY_S)) player.move(Direction.DOWN, mapPanel);
            if(input.isKeyDown(Input.KEY_A)) player.move(Direction.LEFT, mapPanel);
            if(input.isKeyDown(Input.KEY_D)) player.move(Direction.RIGHT, mapPanel);

            mapPanel.moveX(player.getX() - oldX);

            if(player.getY() > NUM_ROW / 2 && player.getY() < World.HEIGHT - NUM_ROW / 2) {
                mapPanel.moveY(player.getY() - oldY);
            }
        }
    }

    public void onMouseOver(Input input) {
    }

    public Rectangle getMouseInteractBound() {
        return mapPanel.getMouseInteractBound();
    }

    public void setPlayer(Player player) {

        this.player = player;
        player.setWorld(mapPanel.getMap());

        int playerX = (int) Math.floor(player.getX());
        int playerY = (int) Math.floor(player.getY());

        mapPanel.mapX = Math.max(0, playerX - 20);
        mapPanel.mapY = Math.max(0, playerY - 15);
    }
}
