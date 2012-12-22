package view.editorPanels;

import game.GameMap;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import view.mapPanels.PortMapPanel;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class PortEditPanel extends EditPanel {

    public PortEditPanel(int x, int y, int width, int height, GameMap gameMap) {
        super(x, y, new PortMapPanel(x, y, width, height, gameMap));
    }

    @Override
    public void render(Graphics graphics, int screenHeight) {
        super.render(graphics, screenHeight);
        graphics.setColor(Color.white);
        graphics.fillRect(x, y + 480, 640, screenHeight - 480);
    }
}
