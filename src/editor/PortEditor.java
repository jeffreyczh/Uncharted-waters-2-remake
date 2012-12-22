package editor;

import game.NPCFactory;
import game.PlaceFactory;
import utils.MapBuilder;
import game.port.PortBuilder;
import utils.ResourceManager;
import view.editorPanels.EditPanel;
import view.editorPanels.EditSidePanel;
import view.editorPanels.PortEditPanel;
import view.editorPanels.PortEditSidePanel;

import java.io.File;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class PortEditor extends Editor {

    private File mapFile;
    private MapBuilder mapBuilder;

    public PortEditor() {
        super("Port editor");
    }

    void initMapBuilder(ResourceManager resourceManager, PlaceFactory placeFactory, NPCFactory npcFactory) {
        this.mapBuilder = new PortBuilder(resourceManager, npcFactory, placeFactory);
    }

    EditPanel getEditPanel() {
        mapFile = mapBuilder.getDefaultMapFile();
        return new PortEditPanel(0, 0, 640, 480, mapBuilder.buildMap(mapFile));
    }

    EditSidePanel getSidePanel(EditPanel panel, ResourceManager resourceManager) {
        PortEditSidePanel sidePanel = new PortEditSidePanel(640, 0, panel.getMapPanel(), mapBuilder, resourceManager);
        sidePanel.setFile(mapFile);
        return sidePanel;
    }

}
