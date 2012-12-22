package view.gamePanels;

import game.GameMap;
import game.MoveableUnit;
import game.World;
import org.newdawn.slick.Input;
import utils.ResourceManager;
import view.mapPanels.MapPanel;
import view.mapPanels.WorldMapPanel;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class SeaNavigatePanel extends NavigatePanel implements IView, ViewDestroyListener {

    private static final int UPPER_LIMIT_Y = World.HEIGHT - HALF_NUM_ROWS + 1;

    private ResourceManager resourceManager;

    public SeaNavigatePanel(int x, int y, GameMap gameMap, ResourceManager resourceManager) {
        super(x, y, gameMap);
        this.resourceManager = resourceManager;
    }

    protected void scroll(MoveableUnit player, double oldX, double oldY) {

        double playerY = player.getY();
        double offsetY = playerY - oldY < 0 ? -1 : 0;

        mapPanel.moveX(player.getX() - oldX);

        if (playerY > HALF_NUM_ROWS + offsetY && playerY < UPPER_LIMIT_Y + offsetY) {
            mapPanel.moveY(playerY - oldY);
        }
    }

    @Override
    public void updateThisView(Input input, int mouseX, int mouseY, boolean mouseClicked) {

        super.updateThisView(input, mouseX, mouseY, mouseClicked);

        if(mouseClicked && contains(mouseX, mouseY)) {
            AnchorSelection anchorSelection =
                    new AnchorSelection((int)(x + (getPlayer().getX() - mapPanel.mapX) * 16 - 16),
                                        (int)(y + (getPlayer().getY() - mapPanel.mapY) * 16 - 16),
                                        x, y, mapPanel.mapX, mapPanel.mapY,
                                        resourceManager.spriteMap.get("ANCHOR_BUTTON"), (World)mapPanel.getGameMap());
            addChild(anchorSelection);
            anchorSelection.addViewDestroyListener(this);
            anchorSelection.requestFocus();
        }
    }

    protected MapPanel getMapPanel(int x, int y, GameMap gameMap) {
        return new WorldMapPanel(x, y, WIDTH, HEIGHT, gameMap);
    }

    public void notifyViewDestroy() {
        releaseFocus();
    }
}
