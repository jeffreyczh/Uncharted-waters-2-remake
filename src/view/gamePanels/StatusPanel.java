package view.gamePanels;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import utils.MenuBuilder;
import utils.ResourceManager;
import view.buttons.StatusButton;
import utils.MenuBuilder.MenuType;
import static utils.MenuBuilder.MenuType.FLEET;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */

public class StatusPanel extends View implements IView, ReleaseFocusListener {

    public static final int POS_X = 618;
    public static final int POS_Y = 0;

    public StatusPanel(ResourceManager resourceManager, MenuBuilder menuBuilder) {

        super(POS_X, POS_Y);

        StatusButton button = new StatusButton(0, 0, FLEET, resourceManager, menuBuilder);
        int buttonHeight = (int) button.getBound().getHeight();

        int yPos = 72 + POS_Y;
        for(MenuType type : MenuType.values()) {
            addChild(new StatusButton(POS_X, yPos, type, resourceManager, menuBuilder));
            yPos += buttonHeight;
        }

        for(IView view : getChildren()) {
            view.addReleaseFocusListener(this);
        }

    }

    public void renderThisView(Graphics graphics) {
    }

    public void updateThisView(Input input, int mouseX, int mouseY, boolean mouseClicked) {
    }

    public void notifyReleaseFocus() {
        releaseFocus();
    }
}