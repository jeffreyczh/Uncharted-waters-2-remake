package view;

import game.World;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class SidePanel extends Panel {

    private static final int WIDTH = 240;
    private static final int HEIGHT = 480;

    private Rectangle bound;
    private Rectangle tileSelectionBound;
    private Image selectSprite;
    private List<Button> buttons;

    private int selection;

    public SidePanel(int x, int y, SpriteSheet tiles, World world) throws SlickException {
        super(x, y, tiles);
        selection = UNDEFINED;
        bound = new Rectangle(x, y, WIDTH, HEIGHT);
        tileSelectionBound = new Rectangle(x + 45, y + 250, tiles.getWidth(), tiles.getHeight());

        buttons = new ArrayList<Button>();
        SpriteSheet buttonSheet = new SpriteSheet("asset/editor_buttons.png", 100, 32);
        selectSprite = buttonSheet.getSubImage(0, 3);
        buttons.add(new OpenButton(x + 75, y + 50, world, buttonSheet));
        buttons.add(new SaveButton(x + 75, y + 100, world, buttonSheet));
        buttons.add(new ExportButton(x + 75, y + 150, world, buttonSheet));

    }

    @Override
    public void update(Input input, int x, int y) {
        super.update(input, x, y);
        for(Button button : buttons) {
            if(button.contain(x, y)) {
                if(input.isMouseButtonDown(0) || input.isMousePressed(0)) {
                    button.hover(false);
                    button.press(true);
                    if(input.isMousePressed(0)) {
                        button.click();
                    }
                } else {
                    button.hover(true);
                    button.press(false);
                }
            } else {
                button.hover(false);
                button.press(false);
            }
        }
    }

    public void onMouseOver(Input input) {
        if(input.isMouseButtonDown(0)) {
            selection = (row * 10 + col) & 0xff;
            selection = (selection > 105) ? UNDEFINED : selection;
        }
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.fillRect(x, y, WIDTH, HEIGHT);

        for(Button button : buttons) {
            button.render(graphics);
        }

        selectSprite.draw(x + 45, y + 214);

        if(selection != UNDEFINED) {
            getSelectedImage().draw(x + 124, y + 223);
        }

        tiles.draw(tileSelectionBound.getX(), tileSelectionBound.getY());
    }

    public Image getSelectedImage() {

        if(selection == UNDEFINED)
            return null;

        int row = selection / 10;
        int col = selection % 10;
        return tiles.getSubImage(col, row);

    }

    public Rectangle getMouseInteractBound() {
        return tileSelectionBound;
    }

    public Rectangle getBound() {
        return bound;
    }

    public int getSelection() {
        return selection;
    }
}
