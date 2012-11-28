package view;

import game.Direction;
import game.GameMap;
import game.MoveableUnit;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public abstract class NavigatePanel extends Panel {

    static final int WIDTH = 384;
    static final int HEIGHT = 384;
    static final int NUM_COLS = WIDTH / 16;
    static final int NUM_ROWS = HEIGHT / 16;
    static final int HALF_NUM_COLS = NUM_COLS / 2;
    static final int HALF_NUM_ROWS = NUM_ROWS / 2;

    private MoveableUnit player;
    protected MapPanel mapPanel;

    public NavigatePanel(int x, int y, GameMap gameMap) throws SlickException {
        super(x, y);
        mapPanel = getMapPanel(x, y, gameMap);
    }

    @Override
    public void render(Graphics graphics) {
        mapPanel.render();
        player.render(x, y, mapPanel.mapX, mapPanel.mapY);
    }

    @Override
    public void update(Input input, int x, int y) {
        super.update(input, x, y);

        if (!(input.isMouseButtonDown(0))) {

            double oldX = player.getX();
            double oldY = player.getY();

            if (input.isKeyDown(Input.KEY_W)) player.move(Direction.UP);
            if (input.isKeyDown(Input.KEY_S)) player.move(Direction.DOWN);
            if (input.isKeyDown(Input.KEY_A)) player.move(Direction.LEFT);
            if (input.isKeyDown(Input.KEY_D)) player.move(Direction.RIGHT);


            scroll(player, oldX, oldY);
        }
    }

    public Rectangle getMouseInteractBound() {
        return mapPanel.getMouseInteractBound();
    }

    public void setPlayer(MoveableUnit player) {

        this.player = player;
        player.setMap(mapPanel.getGameMap());

        int playerX = (int) Math.floor(player.getX());
        int playerY = (int) Math.floor(player.getY());

        mapPanel.mapX = Math.max(0, playerX - HALF_NUM_COLS);
        mapPanel.mapY = Math.max(0, playerY - HALF_NUM_ROWS);
    }

    public void setMap(GameMap gameMap) {
        mapPanel.setGameMap(gameMap);
    }

    protected abstract void scroll(MoveableUnit player, double oldX, double oldY);

    protected abstract MapPanel getMapPanel(int x, int y, GameMap gameMap) throws SlickException;
}
