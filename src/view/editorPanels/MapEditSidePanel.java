package view.editorPanels;

import game.Map;
import org.newdawn.slick.*;
import utils.MapManager;
import view.MapPanel;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class MapEditSidePanel extends EditSidePanel {

    private static final int WIDTH = 240;
    private static final int HEIGHT = 480;
    private static final int MAX_SELECTION = 105;
    private static final int NUM_COL = 10;

    public MapEditSidePanel(int x, int y, SpriteSheet tiles, Map map, MapPanel panel) throws SlickException {
        super(x, y, WIDTH, HEIGHT, NUM_COL, MAX_SELECTION, map, MapManager.MAP_TYPE.WORLD, tiles, panel);
    }
}
