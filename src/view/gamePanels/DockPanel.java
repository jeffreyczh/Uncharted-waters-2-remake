package view.gamePanels;

import utils.ResourceManager;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class DockPanel {

    private int x;
    private int y;
    private ResourceManager resourceManager;

    public DockPanel(int x, int y, ResourceManager resourceManager) {
        this.x = x;
        this.y = y;
        this.resourceManager = resourceManager;
    }

    public void render() {
        resourceManager.imageMap.get("INTERFACE").draw(x, y);
        resourceManager.spriteMap.get("HARBOUR").draw(x + 2, y + 1);
    }
}
