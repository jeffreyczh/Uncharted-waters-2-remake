package view.buttons;

import game.Map;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import view.MapPanel;
import view.editorPanels.EditSidePanel;

import static utils.MapManager.MAP_TYPE;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public abstract class Button {

    private static final int WIDTH = 100;
    private static final int HEIGHT = 32;

    private SpriteSheet sheet;
    private Rectangle bound;
    private int id;
    private boolean hovered;
    private boolean clicked;
    MAP_TYPE type;
    EditSidePanel panel;
    MapPanel mapPanel;

    public Button(int id, int x, int y, MAP_TYPE type, SpriteSheet sheet, EditSidePanel panel, MapPanel mapPanel) {
        bound = new Rectangle(x, y, WIDTH, HEIGHT);
        this.id = id;
        this.sheet = sheet;
        this.type = type;
        this.panel = panel;
        this.mapPanel = mapPanel;
        hovered = false;
    }

    public void render(Graphics graphics) {
        sheet.getSubImage(0,id).draw(bound.getX(), bound.getY());
        Color saved = graphics.getColor();
        if(hovered) {
            graphics.setColor(new Color(0, 0, 0, 0.3f));
            graphics.fillRect(bound.getX(), bound.getY(), bound.getWidth(), bound.getHeight());
        } else if(clicked) {
            graphics.setColor(new Color(0, 0, 0, 0.7f));
            graphics.fillRect(bound.getX(), bound.getY(), bound.getWidth(), bound.getHeight());
        }

        graphics.setColor(saved);
    }

    public abstract void click() throws SlickException;

    public void hover(boolean hovered) {
        this.hovered = hovered;
    }

    public void press(boolean pressed) {
        this.clicked = pressed;
    }

    public boolean contain(int x, int y) {
        return bound.contains(x, y);
    }
}
