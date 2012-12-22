package view.buttons;

import utils.MapBuilder;
import utils.ResourceManager;
import view.editorPanels.EditSidePanel;
import view.mapPanels.MapPanel;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class SaveEditorButton extends EditorButton {

    public SaveEditorButton(int x, int y, EditSidePanel panel, MapPanel mapPanel,
                            MapBuilder mapBuilder, ResourceManager resourceManager) {
        super(x, y, resourceManager.spriteMap.get("EDITOR_SAVE_BUTTON"), panel, mapPanel, mapBuilder);
    }

    public void click() {
        mapBuilder.saveMap(mapPanel.getGameMap(), panel.getMapFile());
    }
}
