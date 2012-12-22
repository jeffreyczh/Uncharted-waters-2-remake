package view.buttons;

import org.newdawn.slick.SpriteSheet;
import utils.MapBuilder;
import view.editorPanels.EditSidePanel;
import view.gamePanels.IView;
import view.mapPanels.MapPanel;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public abstract class EditorButton extends Button implements IView {

    protected EditSidePanel panel;
    protected MapPanel mapPanel;
    protected MapBuilder mapBuilder;

    public EditorButton(int x, int y, SpriteSheet sheet, EditSidePanel panel,
                        MapPanel mapPanel, MapBuilder mapBuilder) {
        super(x, y, sheet);
        this.panel = panel;
        this.mapPanel = mapPanel;
        this.mapBuilder = mapBuilder;
    }
}
