package view.editorPanels;

import org.newdawn.slick.SpriteSheet;
import utils.MapBuilder;
import view.MapPanel;
import view.buttons.OpenButton;
import view.buttons.SaveButton;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class PortEditSidePanel extends EditSidePanel {

    private static final int WIDTH = 608;
    private static final int HEIGHT = 768;
    private static final int MAX_SELECTION = 1034;
    private static final int NUM_COL = 33;

    public PortEditSidePanel(int x, int y, SpriteSheet tiles, SpriteSheet buttonSheet, MapPanel panel, MapBuilder mapBuilder) {
        super(x, y, WIDTH, HEIGHT, NUM_COL, MAX_SELECTION, tiles, buttonSheet);
        addButton(new OpenButton(x + 75, y + 30, buttonSheet, this, panel, mapBuilder));
        addButton(new SaveButton(x + 75, y + 80, buttonSheet, this, panel, mapBuilder));
    }
}
