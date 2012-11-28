package view;

import game.GameMap;
import game.MoveableUnit;
import game.World;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class SeaNavigatePanel extends NavigatePanel {

    private static final int UPPER_LIMIT_Y = World.HEIGHT - HALF_NUM_ROWS + 1;

    public SeaNavigatePanel(int x, int y, GameMap gameMap) throws SlickException {
        super(x, y, gameMap);
    }

    public void onMouseOver(Input input) {
    }

    protected void scroll(MoveableUnit player, double oldX, double oldY) {

        double playerY = player.getY();
        double offsetY = playerY - oldY < 0 ? -1 : 0;

        mapPanel.moveX(player.getX() - oldX);

        if (playerY > HALF_NUM_ROWS + offsetY && playerY < UPPER_LIMIT_Y + offsetY) {
            mapPanel.moveY(playerY - oldY);
        }
    }

    protected MapPanel getMapPanel(int x, int y, GameMap gameMap) throws SlickException {
        return new WorldMapPanel(x, y, WIDTH, HEIGHT, gameMap);
    }
}
