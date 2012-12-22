package view.gamePanels;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import utils.MenuBuilder;
import utils.ResourceManager;

import java.util.List;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public abstract class GamePanel extends View implements IView {

    private static final int WIDTH = 640;
    private static final int HEIGHT = 400;

    private Image ui;
    protected NavigatePanel navigatePanel;

    public GamePanel(int x, int y, Image ui, NavigatePanel navigatePanel,
                     ResourceManager resourceManager, MenuBuilder menuBuilder) {
        super(x, y);
        this.ui = ui;
        this.navigatePanel = navigatePanel;

        addArrowButtons(resourceManager);

        addChild(navigatePanel);
        addChild(new StatusPanel(resourceManager, menuBuilder));
    }

    public void render(Graphics graphics) {
        navigatePanel.render(graphics);
        renderThisView(graphics);

        List<IView> children = getChildren();

        for (IView view : children) {
            if (view != navigatePanel) {
                view.render(graphics);
            }
        }
    }

    public void renderThisView(Graphics graphics) {
        ui.draw(0, 0);
    }

    public abstract void addArrowButtons(ResourceManager resourceManager);

}
