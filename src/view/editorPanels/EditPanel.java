package view.editorPanels;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
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

    public EditPanel(int x, int y, MapPanel panel) {
        super(x, y);
        mapPanel = panel;
    }

    public void render(Graphics graphics, int screenHeight) {
        mapPanel.render();

        if (row != UNDEFINED && col != UNDEFINED) {
            Image image = sidePanel.getSelectedImage();
            if (image != null) {
                image.draw(col * 16, row * 16);
            }
        }

        graphics.setColor(Color.white);
        graphics.drawString((int) mapPanel.getGameMap().wrapCol(mouseX) + " , " + mouseY, 10, 25);
    }

    public void onMouseOver(Input input) {

        if (input.isMouseButtonDown(0)) {
            int selection = sidePanel.getSelection();
            if (selection != UNDEFINED) {
                mapPanel.getGameMap().setTile((int) mapPanel.mapX + col,
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
