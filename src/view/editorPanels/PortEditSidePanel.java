package view.editorPanels;

import game.Map;
import org.newdawn.slick.*;
import utils.MapManager;
import view.MapPanel;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class PortEditSidePanel extends EditSidePanel {

    private static final int WIDTH = 608;
    private static final int HEIGHT = 768;
    private static final int MAX_SELECTION = 1034;
    private static final int NUM_COL = 33;

    public PortEditSidePanel(int x, int y, SpriteSheet tiles, Map map, MapPanel panel) throws SlickException {
        super(x, y, WIDTH, HEIGHT, NUM_COL, MAX_SELECTION, map, MapManager.MAP_TYPE.PORT, tiles, panel);
    }
}
