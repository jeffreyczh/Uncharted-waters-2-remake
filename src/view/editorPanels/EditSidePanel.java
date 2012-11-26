package view.editorPanels;

import game.Map;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import utils.MapManager;
import view.MapPanel;
import view.Panel;
import view.buttons.Button;
import view.buttons.ExportButton;
import view.buttons.OpenButton;
import view.buttons.SaveButton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import static utils.MapManager.MAP_TYPE;

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
                         Map map, MAP_TYPE type, SpriteSheet tiles, MapPanel mapPanel) throws SlickException {
        super(x, y);
        selection = UNDEFINED;
        tileSelectionBound = new Rectangle(x + 45, y + 200, tiles.getWidth(), tiles.getHeight());
        this.tiles = tiles;
        this.width = width;
        this.height = height;
        this.numCol = numCol;
        this.maxSelection = maxSelection;

        buttons = new ArrayList<Button>();

        SpriteSheet buttonSheet = new SpriteSheet("asset/editor_buttons.png", 100, 32);
        selectSprite = buttonSheet.getSubImage(0, 3);
        buttons.add(new OpenButton(x + 75, y + 30, type, buttonSheet, this, mapPanel));
        buttons.add(new SaveButton(x + 75, y + 80, type, buttonSheet, this, mapPanel));
        buttons.add(new ExportButton(x + 75, y + 130, type, buttonSheet, mapPanel));
    }

    @Override
    public void update(Input input, int x, int y) throws SlickException {
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

    public void render(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.fillRect(x, y, width, height);

        for(Button button : buttons) {
            button.render(graphics);
        }

        selectSprite.draw(x + 45, y + 164);

        if(selection != UNDEFINED) {
            getSelectedImage().draw(x + 124, y + 173);
        }

        tiles.draw(tileSelectionBound.getX(), tileSelectionBound.getY());
    }

    public void onMouseOver(Input input) {
        if(input.isMouseButtonDown(0)) {
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

        if(selection == UNDEFINED)
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
}
