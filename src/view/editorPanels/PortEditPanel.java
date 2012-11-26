package view.editorPanels;

import game.Map;
import org.newdawn.slick.*;
import view.PortMapPanel;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class PortEditPanel extends EditPanel {

    public PortEditPanel(int x, int y, int width, int height, Map map) throws SlickException {
        super(x, y, new PortMapPanel(x, y, width, height, map));
    }

    @Override
    public void render(Graphics graphics, int screenHeight) {
        super.render(graphics, screenHeight);
        graphics.setColor(Color.white);
        graphics.fillRect(x, y + 480, 640, screenHeight - 480);
    }
}
