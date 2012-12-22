package view.editorPanels;

import utils.MapBuilder;
import utils.ResourceManager;
import view.buttons.SaveEditorButton;
import view.mapPanels.MapPanel;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class MapEditSidePanel extends EditSidePanel {

    private static final int WIDTH = 240;
    private static final int HEIGHT = 480;
    private static final int MAX_SELECTION = 105;
    private static final int NUM_COL = 10;

    public MapEditSidePanel(int x, int y, MapPanel panel, MapBuilder mapBuilder, ResourceManager resourceManager) {
        super(x, y, WIDTH, HEIGHT, NUM_COL, MAX_SELECTION,
                resourceManager.spriteMap.get("WORLD_TILES"),
                resourceManager.imageMap.get("EDITOR_SELECTED"));
        addButton(new SaveEditorButton(x + 75, y + 80, this, panel, mapBuilder, resourceManager));
    }
}
