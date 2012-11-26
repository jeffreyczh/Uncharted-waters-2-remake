package editor;

import game.Map;
import org.newdawn.slick.*;
import utils.MapManager;
import view.editorPanels.EditPanel;
import view.editorPanels.EditSidePanel;
import view.editorPanels.MapEditPanel;
import view.editorPanels.MapEditSidePanel;

import java.io.File;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class MapEditor extends Editor {

    public static final int SPRITE_SIZE = 16;
    public final File mapFile;
    public Map map;

    public MapEditor() {
        super("Map editor");
        mapFile = MapManager.getInstance().getDefaultWorldFile();
    }

    EditPanel getEditPanel() throws SlickException {
        map = MapManager.getInstance().buildWorld(mapFile);
        return new MapEditPanel(0, 0, 640, 480, map);
    }

    EditSidePanel getSidePanel(EditPanel panel) throws SlickException {
        SpriteSheet tiles = new SpriteSheet("asset/tiles.png", SPRITE_SIZE, SPRITE_SIZE);
        MapEditSidePanel sidePanel = new MapEditSidePanel(640, 0, tiles, map, panel.getMapPanel());
        sidePanel.setFile(mapFile);
        return sidePanel;
    }

}
