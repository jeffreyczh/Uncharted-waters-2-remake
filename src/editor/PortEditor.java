package editor;

import game.Map;
import org.newdawn.slick.*;
import utils.MapManager;
import view.editorPanels.EditPanel;
import view.editorPanels.EditSidePanel;
import view.editorPanels.PortEditPanel;
import view.editorPanels.PortEditSidePanel;

import java.io.File;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class PortEditor extends Editor {

    public static final int SPRITE_SIZE = 16;
    private final File mapFile;
    private Map map;

    public PortEditor() {
        super("Port editor");
        mapFile = MapManager.getInstance().getDefaultPortFile();
    }

    EditPanel getEditPanel() throws SlickException {
        map = MapManager.getInstance().buildPort(mapFile);
        return new PortEditPanel(0, 0, 640, 480, map);
    }

    EditSidePanel getSidePanel(EditPanel panel) throws SlickException {
        SpriteSheet tiles = new SpriteSheet("asset/portTile.png", SPRITE_SIZE, SPRITE_SIZE);
        PortEditSidePanel sidePanel = new PortEditSidePanel(640, 0, tiles, map, panel.getMapPanel());
        sidePanel.setFile(mapFile);
        return sidePanel;
    }

}
