package view;

import game.World;
import org.newdawn.slick.SpriteSheet;
import view.MapEditPanel;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class SailingPanel extends Panel {

    private MapPanel mapPanel;

    public SailingPanel(int x, int y, SpriteSheet tiles) {
        super(x, y, tiles);
        mapPanel = new MapPanel(tiles);
    }

    public void render() {
        mapPanel.render();
    }

    public void onMouseOver(Input input) {
    }

    public void moveX(int amount) {
        mapPanel.moveX(amount);
    }

    public void moveY(int amount) {
        mapPanel.moveY(amount);
    }

    public Rectangle getMouseInteractBound() {
        return mapPanel.getMouseInteractBound();
    }
}
