package view.gamePanels;

import game.GameMap;
import game.MoveableUnit;
import game.port.Port;
import view.mapPanels.MapPanel;
import view.mapPanels.PortMapPanel;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class LandNavigatePanel extends NavigatePanel implements IView {

    private static final int UPPER_LIMIT_X = Port.WIDTH - HALF_NUM_COLS + 1;
    private static final int UPPER_LIMIT_Y = Port.HEIGHT - HALF_NUM_ROWS + 1;

    public LandNavigatePanel(int x, int y, GameMap gameMap) {
        super(x, y, gameMap);
    }

    protected void scroll(MoveableUnit player, double oldX, double oldY) {

        double playerX = player.getX();
        double playerY = player.getY();

        double offsetX = playerX - oldX < 0 ? -1 : 0;
        double offsetY = playerY - oldY < 0 ? -1 : 0;

        if (playerX > HALF_NUM_COLS + offsetX && playerX < UPPER_LIMIT_X + offsetX) {
            mapPanel.moveX(playerX - oldX);
        }

        if (playerY > HALF_NUM_ROWS + offsetY && playerY < UPPER_LIMIT_Y + offsetY) {
            mapPanel.moveY(playerY - oldY);
        }
    }

    protected MapPanel getMapPanel(int x, int y, GameMap gameMap) {
        return new PortMapPanel(x, y, WIDTH, HEIGHT, gameMap);
    }
}
