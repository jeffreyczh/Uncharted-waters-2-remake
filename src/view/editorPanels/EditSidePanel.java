package view.editorPanels;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import view.buttons.EditorButton;
import view.mapPanels.Panel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class EditSidePanel extends Panel {

    private Rectangle tileSelectionBound;
    private Image selectSprite;
    private List<EditorButton> editorButtons;
    private SpriteSheet tiles;

    private int width;
    private int height;
    private int numCol;
    private int selection;
    private int maxSelection;
    private File mapFile;

    public EditSidePanel(int x, int y, int width, int height, int numCol, int maxSelection,
                         SpriteSheet tiles, Image selectSprite) {
        super(x, y);
        selection = UNDEFINED;
        tileSelectionBound = new Rectangle(x + 45, y + 200, tiles.getWidth(), tiles.getHeight());
        this.tiles = tiles;
        this.width = width;
        this.height = height;
        this.numCol = numCol;
        this.maxSelection = maxSelection;

        editorButtons = new ArrayList<>();

        this.selectSprite = selectSprite;
    }

    @Override
    public void update(Input input, int mouseX, int mouseY, boolean mouseClicked) {
        super.update(input, mouseX, mouseY, mouseClicked);
        for (EditorButton editorButton : editorButtons) {
            editorButton.update(input, mouseX, mouseY, mouseClicked);
        }
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.fillRect(x, y, width, height);

        for (EditorButton editorButton : editorButtons) {
            editorButton.render(graphics);
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

    public void addButton(EditorButton editorButton) {
        if (editorButton != null) {
            editorButtons.add(editorButton);
        }
    }
}
