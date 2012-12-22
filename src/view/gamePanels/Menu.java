package view.gamePanels;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class Menu extends View implements IView {

    private final int SPRITE_SIZE;

    private SpriteSheet menuSprite;
    private int selection;
    private List<String> options;
    private List<Rectangle> optionBounds;
    private int width;

    private Font font;

    public Menu(int x, int y, int width, SpriteSheet menuSprite) {
        super(x, y);
        this.width = width;
        this.menuSprite = menuSprite;
        SPRITE_SIZE = menuSprite.getSubImage(0, 0).getHeight();

        selection = 0;
        options = new ArrayList<>();
        optionBounds = new ArrayList<>();

        font = Utils.getFont();
    }

    //this code is messy, refractor it later
    public void renderThisView(Graphics graphics) {

        renderBackground();

        int numRow = options.size();
        Color fontColor;

        for (int i = 0; i < numRow; i++) {

            if (selection == i) {
                fontColor = new Color(243, 227, 211);
                graphics.setColor(Color.black);
                graphics.fillRect(x + 8, y + (SPRITE_SIZE * (3 * i + 2)), width - 24, 20);
            } else {
                fontColor = Color.black;
            }
            Utils.drawStringCenter(x - 5, y + (SPRITE_SIZE * (3 * i + 2)), width, options.get(i), fontColor);
        }
    }

    private void renderBackground() {

        int numCol = width / SPRITE_SIZE - 2;
        int numRow = options.size();

        //drawing the upper frame
        renderBackgroundRow(x, y, numCol, 0);
        renderBackgroundRow(x, y + SPRITE_SIZE, numCol, 1);

        for (int row = 0; row < numRow; row++) {
            int firstRowY = 3 * row + 2;
            renderBackgroundRow(x, y + SPRITE_SIZE * firstRowY, numCol, 1);
            renderBackgroundRow(x, y + SPRITE_SIZE * (firstRowY + 1), numCol, 1);
            renderBackgroundRow(x, y + SPRITE_SIZE * (firstRowY + 2), numCol, 1);
        }

        renderBackgroundRow(x, y + SPRITE_SIZE * (numRow * 3 + 2), numCol, 2);
    }

    private void renderBackgroundRow(int x, int y, int numCol, int row) {

        menuSprite.getSubImage(0, row).draw(x, y);

        for (int i = 1; i <= numCol; i++) {
            menuSprite.getSubImage(1, row).draw(x + SPRITE_SIZE * i, y);
        }

        menuSprite.getSubImage(2, row).draw(x + SPRITE_SIZE * numCol, y);
    }

    public void addOption(String option) {
        if (option != null) {
            options.add(option);
            optionBounds.add(new Rectangle(x + 8, y + (SPRITE_SIZE * (3 * (options.size() - 1) + 2)), width - 24, 24));
        }
    }

    public void updateThisView(Input input, int mouseX, int mouseY, boolean mouseClicked) {

        int numOptions = options.size();

        if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
            for (int i = 0; i < numOptions; i++) {
                if (optionBounds.get(i).contains(mouseX, mouseY)) {
                    selection = i;
                }
            }
        } else if (input.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON) || input.isKeyPressed(Input.KEY_ESCAPE)) {
            destroyView();
        }

        if (input.isKeyPressed(Input.KEY_UP)) {
            selection = selection > 0 ? selection - 1 : 0;
        } else if (input.isKeyPressed(Input.KEY_DOWN)) {
            selection = selection < numOptions - 1 ? selection + 1 : numOptions - 1;
        }
    }

}
