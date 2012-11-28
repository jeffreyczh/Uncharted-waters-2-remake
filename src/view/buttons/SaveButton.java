package view.buttons;

import org.newdawn.slick.SpriteSheet;
import utils.MapBuilder;
import view.MapPanel;
import view.editorPanels.EditSidePanel;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class SaveButton extends Button {

    public SaveButton(int x, int y, SpriteSheet sheet, EditSidePanel panel,
                      MapPanel mapPanel, MapBuilder mapBuilder) {
        super(1, x, y, sheet, panel, mapPanel, mapBuilder);
    }

    public void click() {
        mapBuilder.saveMap(mapPanel.getGameMap(), panel.getMapFile());
    }

    @Override
    public void press(boolean pressed) {
        super.press(pressed);
    }
}
