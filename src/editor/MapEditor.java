package editor;

import game.GameMap;
import game.NPCFactory;
import game.PlaceFactory;
import org.newdawn.slick.SpriteSheet;
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
        this.mapBuilder = new WorldBuilder(resourceManager, placeFactory);
    }

    EditPanel getEditPanel() {
        gameMap = mapBuilder.buildMap(mapBuilder.getDefaultMapFile());
        return new MapEditPanel(0, 0, 640, 480, gameMap);
    }

    EditSidePanel getSidePanel(EditPanel panel, ResourceManager resourceManager) {
        SpriteSheet tiles = resourceManager.worldMapSpriteSheet;
        MapEditSidePanel mapEditSidePanel = new MapEditSidePanel(640, 0, tiles, resourceManager.editorButtonSheet, panel.getMapPanel(), mapBuilder);
        mapEditSidePanel.setFile(mapBuilder.getDefaultMapFile());
        return mapEditSidePanel;
    }

}
