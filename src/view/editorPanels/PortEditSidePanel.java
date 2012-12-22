package view.editorPanels;

import utils.MapBuilder;
import utils.ResourceManager;
import view.buttons.OpenEditorButton;
import view.buttons.SaveEditorButton;
import view.mapPanels.MapPanel;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class PortEditSidePanel extends EditSidePanel {

    private static final int WIDTH = 608;
    private static final int HEIGHT = 768;
    private static final int MAX_SELECTION = 1034;
    private static final int NUM_COL = 33;

    public PortEditSidePanel(int x, int y, MapPanel panel, MapBuilder mapBuilder, ResourceManager resourceManager) {
        super(x, y, WIDTH, HEIGHT, NUM_COL, MAX_SELECTION,
                resourceManager.spriteMap.get("PORT_TILES"),
                resourceManager.imageMap.get("EDITOR_SELECTED"));

        addButton(new OpenEditorButton(x + 75, y + 30, this, panel, mapBuilder, resourceManager));
        addButton(new SaveEditorButton(x + 75, y + 80, this, panel, mapBuilder, resourceManager));
    }
}
