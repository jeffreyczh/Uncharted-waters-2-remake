package view.editorPanels;

import game.GameMap;
import view.WorldMapPanel;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class MapEditPanel extends EditPanel {

    public MapEditPanel(int x, int y, int width, int height, GameMap gameMap) {
        super(x, y, new WorldMapPanel(x, y, width, height, gameMap));
    }
}
