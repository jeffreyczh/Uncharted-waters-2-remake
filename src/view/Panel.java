package view;

import org.newdawn.slick.Input;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public abstract class Panel {

    static final int UNDEFINED = -1;

    /**
     * The x, y coordinate of the panel
     */
    final int x;
    final int y;
    SpriteSheet tiles;

    /**
     * The row and column where the cursor is on
     */
    public int row;
    public int col;

    public Panel(int x, int y, SpriteSheet tiles) {
        this.x = x;
        this.y = y;
        this.col = UNDEFINED;
        this.row = UNDEFINED;
        this.tiles = tiles;
    }

    /**
     * This method checks if the mouse in in the interact
     * region of the panel, if it is, calculate the row and
     * column of the cursor and update the corresponding field
     * If the mouse is not in the region then the row and col
     * field will be set to @see UNDEFINED
     *
     * @param input the input object of slick library
     * @param x the x coordinate of the mouse
     * @param y the y coordinate of the mouse
     */
    public void update(Input input, int x, int y) {

        Rectangle bound = getMouseInteractBound();

        if(bound.contains(x, y)) {
            row = (int) ((y - bound.getY()) / 16);
            col = (int) ((x - bound.getX()) / 16);
            onMouseOver(input);
        } else {
            row = UNDEFINED;
            col = UNDEFINED;
        }
    }

    /**
     * Get the interactive area of the panel
     *
     * @return the rectangular boundary of the interactive area
     */
    public abstract Rectangle getMouseInteractBound();

    /**
     * Call back method when the mouse is over the panel
     *
     * @param input
     */
    public abstract void onMouseOver(Input input);
}
