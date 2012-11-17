package view;

import game.Calendar;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class EnvironmentStatusPanel extends Panel {

    private static final int WIDTH = 160;
    private static final int HEIGHT = 480;

    private Rectangle bound;

    public EnvironmentStatusPanel(int x, int y, SpriteSheet tiles) {
        super(x, y, tiles);
        this.bound = new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public void render(Graphics graphics, Calendar calendar) {
        graphics.setColor(Color.black);
        graphics.fillRect(x, y, WIDTH, HEIGHT);
        graphics.setColor(Color.green);
        graphics.drawString(calendar.toString(), x + 10, y + 10);
    }

    public Rectangle getMouseInteractBound() {
        return bound;
    }

    @Override
    public void onMouseOver(Input input) {
    }
}
