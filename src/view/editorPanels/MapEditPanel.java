package view.editorPanels;

import game.Map;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utils.MapManager;
import view.WorldMapPanel;

import java.io.File;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class MapEditPanel extends EditPanel {

    public MapEditPanel(int x, int y, int width, int height, Map map) throws SlickException {
        super(x, y, new WorldMapPanel(x, y, width, height, map));
    }
}
