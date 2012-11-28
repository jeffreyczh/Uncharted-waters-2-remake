package view.editorPanels;

import org.newdawn.slick.SpriteSheet;
import utils.MapBuilder;
import view.MapPanel;
import view.buttons.SaveButton;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class MapEditSidePanel extends EditSidePanel {

    private static final int WIDTH = 240;
    private static final int HEIGHT = 480;
    private static final int MAX_SELECTION = 105;
    private static final int NUM_COL = 10;

    public MapEditSidePanel(int x, int y, SpriteSheet tile, SpriteSheet buttonSheet,
                                                             MapPanel panel, MapBuilder mapBuilder) {
        super(x, y, WIDTH, HEIGHT, NUM_COL, MAX_SELECTION, tile, buttonSheet);
        addButton(new SaveButton(x + 75, y + 80, buttonSheet, this, panel, mapBuilder));
    }
}
