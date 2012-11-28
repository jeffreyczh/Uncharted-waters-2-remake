package view.editorPanels;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import utils.MapBuilder;
import view.MapPanel;
import view.Panel;
import view.buttons.Button;
import view.buttons.OpenButton;
import view.buttons.SaveButton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class EditSidePanel extends Panel {

    private Rectangle tileSelectionBound;
    private Image selectSprite;
    private List<Button> buttons;
    private SpriteSheet tiles;

    private int width;
    private int height;
    private int numCol;
    private int selection;
    private int maxSelection;
    private File mapFile;

    public EditSidePanel(int x, int y, int width, int height, int numCol, int maxSelection,
                                                              SpriteSheet tiles, SpriteSheet buttonSheet) {
        super(x, y);
        selection = UNDEFINED;
        tileSelectionBound = new Rectangle(x + 45, y + 200, tiles.getWidth(), tiles.getHeight());
        this.tiles = tiles;
        this.width = width;
        this.height = height;
        this.numCol = numCol;
        this.maxSelection = maxSelection;

        buttons = new ArrayList<>();

        selectSprite = buttonSheet.getSubImage(0, 3);
    }

    @Override
    public void update(Input input, int x, int y) {
        super.update(input, x, y);
        for (Button button : buttons) {
            if (button.contain(x, y)) {
                if (input.isMouseButtonDown(0) || input.isMousePressed(0)) {
                    button.hover(false);
                    button.press(true);
                    if (input.isMousePressed(0)) {
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

    public void render(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.fillRect(x, y, width, height);

        for (Button button : buttons) {
            button.render(graphics);
        }

        selectSprite.draw(x + 45, y + 164);

        if (selection != UNDEFINED) {
            getSelectedImage().draw(x + 124, y + 173);
        }

        tiles.draw(tileSelectionBound.getX(), tileSelectionBound.getY());
    }

    public void onMouseOver(Input input) {
        if (input.isMouseButtonDown(0)) {
            selection = row * numCol + col;
            selection = (selection > maxSelection) ? UNDEFINED : selection;
        }
    }

    public Rectangle getMouseInteractBound() {
        return tileSelectionBound;
    }

    public int getSelection() {
        return selection;
    }

    public Image getSelectedImage() {

        if (selection == UNDEFINED)
            return null;

        int row = selection / numCol;
        int col = selection % numCol;
        return tiles.getSubImage(col, row);

    }

    public void setFile(File mapFile) {
        this.mapFile = mapFile;
    }

    public File getMapFile() {
        return mapFile;
    }

    public void addButton(Button button) {
        if(button != null) {
            buttons.add(button);
        }
    }
}
