package view.buttons;

import utils.MenuBuilder;
import utils.ResourceManager;
import view.gamePanels.IView;
import view.gamePanels.ReleaseFocusListener;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class StatusButton extends Button implements IView, ReleaseFocusListener {

    private MenuBuilder.MenuType type;
    private MenuBuilder menuBuilder;

    public StatusButton(int x, int y, MenuBuilder.MenuType type,
                        ResourceManager resourceManager, MenuBuilder menuBuilder) {
        super(x, y, resourceManager.spriteMap.get(type.toString() + "_BUTTON"));
        this.menuBuilder = menuBuilder;
        this.type = type;
    }

    public void click() {
        IView menu = menuBuilder.buildMenu(type);
        addChild(menu);
        menu.addReleaseFocusListener(this);
        menu.requestFocus();
    }

    public void notifyReleaseFocus() {
        releaseFocus();
    }
}
