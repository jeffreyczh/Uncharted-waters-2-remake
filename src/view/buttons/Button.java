package view.buttons;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import view.gamePanels.IView;
import view.gamePanels.View;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */

public abstract class Button extends View implements IView {

    private static final int NUM_STATUS = 3;

    private static final int NORMAL = 0;
    private static final int HOVERED = 1;
    private static final int PRESSED = 2;

    private Rectangle bound;
    private SpriteSheet buttonSheet;

    private int buttonStatus;
    private boolean destroyed;

    public Button(int x, int y, SpriteSheet buttonSheet) {
        super(x, y);

        int width = buttonSheet.getWidth();
        int height = buttonSheet.getHeight() / NUM_STATUS;

        this.bound = new Rectangle(x, y, width, height);

        this.buttonSheet = buttonSheet;
        this.buttonStatus = NORMAL;
        destroyed = false;
    }

    public void renderThisView(Graphics graphics) {
        buttonSheet.getSubImage(0, buttonStatus).draw(x, y);
    }

    public abstract void click();


    public boolean contains(int x, int y) {
        return bound.contains(x, y);
    }

    public void updateThisView(Input input, int mouseX, int mouseY, boolean mouseClicked) {

        if(destroyed) {
            destroyView();
        } else {
            buttonStatus = NORMAL;

            if (contains(mouseX, mouseY)) {
                if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
                    buttonStatus = PRESSED;
                } else {
                    buttonStatus = HOVERED;
                }

                if (mouseClicked) {
                    click();
                }
            }
        }
    }

    public Rectangle getBound() {
        return bound;
    }

    public void destroyButton() {
        destroyed = true;
    }
}