package view.gamePanels;

import game.Direction;
import game.MoveableUnit;
import game.PlayerCharacter;
import game.port.Port;
import game.port.PortInfo;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import utils.MenuBuilder;
import utils.ResourceManager;
import utils.Utils;
import view.buttons.ArrowButton;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class TownPanel extends GamePanel implements IView {

    private static final int MAP_X = 96;
    private static final int MAP_Y = 8;

    private PortInfo port;

    public TownPanel(int x, int y, Port port, ResourceManager resourceManager, MenuBuilder menuBuilder) {
        super(x, y,
              resourceManager.imageMap.get("LAND_INTERFACE"),
              new LandNavigatePanel(MAP_X, MAP_Y, port),
              resourceManager, menuBuilder);

        this.port = port;

        MoveableUnit player = new PlayerCharacter(34, 69, resourceManager.spriteMap.get("JF_WALK"));
        navigatePanel.setPlayer(player);
    }

    public void enterPort(Port port) {
        if (navigatePanel != null) {
            navigatePanel.setMap(port);
        }
    }

    public void leavePort() {

    }

    @Override
    public void renderThisView(Graphics graphics) {
        super.renderThisView(graphics);
        Utils.drawStringCenter(488, 7, 144, port.getName());
        Utils.drawStringCenter(488, 23, 144, port.getArea());
        Utils.drawStringRightAligned(598, 93, String.valueOf(port.getEconomy()));
        Utils.drawStringRightAligned(598, 125, String.valueOf(port.getEconomyInvest()));
        Utils.drawStringRightAligned(598, 181, String.valueOf(port.getIndustry()));
        Utils.drawStringRightAligned(598, 213, String.valueOf(port.getIndustryInvest()));
        Utils.drawStringRightAligned(598, 272, String.valueOf(port.getPriceIndexPercentage()) + "%");
    }

    public void updateThisView(Input input, int mouseX, int mouseY, boolean mouseClicked) {
    }

    public void addArrowButtons(ResourceManager resourceManager) {
        for (Direction d : Direction.values()) {
            addChild(new ArrowButton(d, resourceManager));
        }
    }
}
