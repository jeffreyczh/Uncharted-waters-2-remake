package editor;

import game.GameMap;
import game.NPCFactory;
import game.PlaceFactory;
import utils.MapBuilder;
import utils.ResourceManager;
import utils.WorldBuilder;
import view.editorPanels.EditPanel;
import view.editorPanels.EditSidePanel;
import view.editorPanels.MapEditPanel;
import view.editorPanels.MapEditSidePanel;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class MapEditor extends Editor {

    public GameMap gameMap;
    private MapBuilder mapBuilder;

    public MapEditor() {
        super("Map editor");
    }

    void initMapBuilder(ResourceManager resourceManager, PlaceFactory placeFactory, NPCFactory npcFactory) {
        this.mapBuilder = new WorldBuilder(resourceManager, placeFactory, null);
    }

    EditPanel getEditPanel() {
        gameMap = mapBuilder.buildMap(mapBuilder.getDefaultMapFile());
        return new MapEditPanel(0, 0, 640, 480, gameMap);
    }

    EditSidePanel getSidePanel(EditPanel panel, ResourceManager resourceManager) {
        MapEditSidePanel mapEditSidePanel = new MapEditSidePanel(640, 0, panel.getMapPanel(), mapBuilder, resourceManager);
        mapEditSidePanel.setFile(mapBuilder.getDefaultMapFile());
        return mapEditSidePanel;
    }

}
