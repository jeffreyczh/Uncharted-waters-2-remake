package view.editorPanels;

import game.Map;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import utils.MapManager;
import view.MapPanel;
import view.Panel;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class EditPanel extends Panel {

    private EditSidePanel sidePanel;
    private MapPanel mapPanel;
    int mouseX;
    int mouseY;

    public EditPanel(int x, int y, MapPanel panel) throws SlickException {
        super(x, y);
        mapPanel = panel;
    }

    public void render(Graphics graphics, int screenHeight) {
        mapPanel.render();

        if(row != UNDEFINED && col != UNDEFINED) {
            Image image = sidePanel.getSelectedImage();
            if(image != null) {
                image.draw(col * 16, row * 16);
            }
        }

        graphics.setColor(Color.white);
        graphics.drawString((int)mapPanel.getMap().wrapCol(mouseX) + " , " + mouseY, 10, 25);
    }

    public void onMouseOver(Input input) {

        if(input.isMouseButtonDown(0)) {
            int selection = sidePanel.getSelection();
            if(selection != UNDEFINED) {
                mapPanel.getMap().setTile((int) mapPanel.mapX + col,
                        (int) mapPanel.mapY + row,
                        selection);
            }
        }

        mouseX = (int) (mapPanel.mapX + col);
        mouseY = (int) (mapPanel.mapY + row);
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

    public void setSidePanel(EditSidePanel sidePanel) {
        this.sidePanel = sidePanel;
    }

    public MapPanel getMapPanel() {
        return mapPanel;
    }

}
