package game;

import org.newdawn.slick.SpriteSheet;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class ShipUnit extends MoveableUnit {

    public ShipUnit(int x, int y, SpriteSheet sheet) {
        super(x, y, sheet);
    }

    @Override
    protected void blockedMovement() {
        if (blocked) {
            x = Math.floor(x + 0.5);
            y = Math.floor(y + 0.5);
        }
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

        int floorX = (int) Math.floor(x);
        int floorY = (int) Math.floor(y);
        int floorX1 = (int) Math.floor(x + 1.99);
        int floorY1 = (int) Math.floor(y + 1.99);

        boolean valid = map.isEnterable(floorX, floorY, dir);
        valid &= map.isEnterable(floorX + 1, floorY, dir);
        valid &= map.isEnterable(floorX1, floorY, dir);
        valid &= map.isEnterable(floorX1, floorY + 1, dir);
        valid &= map.isEnterable(floorX1, floorY1, dir);
        valid &= map.isEnterable(floorX + 1, floorY1, dir);
        valid &= map.isEnterable(floorX, floorY1, dir);
        valid &= map.isEnterable(floorX, floorY + 1, dir);

        return valid;
    }

    public void render(int screenX, int screenY, double worldX, double worldY) {

        if (x < worldX) {
            worldX = worldX - World.WIDTH;
        }

        sheet.getSubImage(direction.getID(), 0).draw((float) (screenX + (x - worldX) * 16),
                (float) (screenY + (y - worldY) * 16));
    }
}
