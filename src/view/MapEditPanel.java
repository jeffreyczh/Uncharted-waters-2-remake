package view;

import game.World;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class MapEditPanel extends Panel {

    private SidePanel sidePanel;
    private MapPanel mapPanel;

    public MapEditPanel(int x, int y, int width, int height, SpriteSheet tiles) {
        super(x, y, tiles);
        mapPanel = new MapPanel(x, y, width, height, tiles);
    }

    public void render() {
        mapPanel.render();
        if(row != UNDEFINED && col != UNDEFINED) {
            Image image = sidePanel.getSelectedImage();
            if(image != null) {
                image.draw(col * 16, row * 16);
            }
        }
    }

    public void onMouseOver(Input input) {
        if(input.isMouseButtonDown(0)) {
            int selection = sidePanel.getSelection();
            if(selection != UNDEFINED) {
                mapPanel.world.setTile((int)mapPanel.worldX + col,
                                       (int)mapPanel.worldY + row,
                                       (byte) selection);
            }
        }
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

    public void setSidePanel(SidePanel sidePanel) {
        this.sidePanel = sidePanel;
    }

    public World getWorld() {
        return mapPanel.world;
    }
}
