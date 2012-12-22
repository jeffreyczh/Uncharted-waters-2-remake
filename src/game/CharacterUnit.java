package game;

import org.newdawn.slick.SpriteSheet;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class CharacterUnit extends MoveableUnit {

    private int step;

    public CharacterUnit(int x, int y, SpriteSheet sheet) {
        super(x, y, sheet);

        //the speed of the characters on the land is always 1
        //to avoid nasty collision problems
        setSpeed(1);
        step = 0;
    }

    /**
     * Check the 8 collision points and see if the player collide with
     * any tiles in the destination
     *
     * @param x the x coordinate of the position the player moving to
     * @param y the y coordinate of the position the player moving to
     * @return
     */
    public boolean isValidMove(double x, double y, Direction dir) {

        GameMap map = getMap();

        boolean valid = map.isEnterable((int) x, (int) (y + 1), dir);

        //this condition prevents the character from walking on to port tile 43
        //which is for consistency with the original game
        valid &= map.isEnterable((int) (x + 1), (int) (y + 1), Direction.RIGHT);

        return valid;
    }

    @Override
    public void render(int screenX, int screenY, double worldX, double worldY) {
        sheet.getSubImage(direction.getID(), step).draw((float) (screenX + (x - worldX) * 16),
                (float) (screenY + (y - worldY) * 16));
    }

    @Override
    public void move(Direction direction) {
        super.move(direction);
        step = step == 0 ? 1 : 0;
    }

    public void wonder() {

    }
}
