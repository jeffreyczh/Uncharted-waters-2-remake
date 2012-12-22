package view.gamePanels;

import game.MoveableUnit;
import game.PlayerShip;
import game.World;
import org.newdawn.slick.Input;
import utils.MenuBuilder;
import utils.ResourceManager;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class SeaPanel extends GamePanel implements IView {

    private static final int MAP_X = 96;
    private static final int MAP_Y = 8;

    public SeaPanel(int x, int y, World world, ResourceManager resourceManager, MenuBuilder menuBuilder) {
        super(x, y,
              resourceManager.imageMap.get("SEA_INTERFACE"),
              new SeaNavigatePanel(MAP_X, MAP_Y, world, resourceManager),
              resourceManager, menuBuilder);

        MoveableUnit player = new PlayerShip(118, 358, resourceManager.spriteMap.get("MAIN_SHIP"));
        navigatePanel.setPlayer(player);
    }

    public void addArrowButtons(ResourceManager resourceManager) {
    }

    public void updateThisView(Input input, int mouseX, int mouseY, boolean mouseClicked) {
    }

}
