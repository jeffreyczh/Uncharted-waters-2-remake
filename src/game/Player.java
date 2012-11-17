package game;

import org.newdawn.slick.SpriteSheet;
import view.MapPanel;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class Player {

    private World world;
    private SpriteSheet sheet;

    private double x;
    private double y;
    private Direction direction;
    private Fleet fleet;
    private double speed;

    public Player(int x, int y, SpriteSheet sheet) {
        this.x = x;
        this.y = y;
        this.sheet = sheet;
        setSpeed(0.8);
        this.direction = Direction.DOWN;
        fleet = new Fleet();
    }

    public void render(int screenX, int screenY, double worldX, double worldY) {

        if(x < worldX) {
            worldX = worldX - World.WIDTH;
        }

        sheet.getSubImage(direction.getID(), 0).draw((float)(screenX + (x - worldX) * 16),
                                                      (float)(screenY + (y - worldY) * 16));
    }

    public void setSpeed(double speed) {
        this.speed = Math.floor(Math.min(speed * 16, 16)) / 16;
    }

    /**
     * This method move the player by either the speed of the player
     * when the player can move to the next location or the amount
     * to the edge of the next colliding tile
     *
     * @param direction the direction the player moving to
     * @return the amount the player had moved
     */
    public void move(Direction direction, MapPanel mapPanel) {

        boolean blocked = true;

        if (direction == Direction.UP) {

            if(isValidMove(x, y - speed)) {
                y = y - speed;
                blocked = false;
            }

        } else if(direction == Direction.DOWN) {
            if(isValidMove(x, y + speed))  {
                y = y + speed;
                blocked = false;
            }

        } else if(direction == Direction.LEFT) {
            if(isValidMove(x - speed, y)) {
                x = x - speed;
                blocked = false;
            }
        } else {
            if(isValidMove(x + speed, y)) {
                x = x + speed;
                blocked = false;
            }
        }

        if(blocked) {
            x = Math.floor(x + 0.5);
            y = Math.floor(y + 0.5);
        }

        x = world.wrapCol(x);

        this.direction = direction;

    }

    /**
     * Check the 8 collision points and see if the player collide with
     * any tiles in the destination
     *
     * @param x the x coordinate of the position the player moving to
     * @param y the y coordinate of the position the player moving to
     * @return
     */
    private boolean isValidMove(double x, double y) {

        int floorX = (int) Math.floor(x);
        int floorY = (int) Math.floor(y);
        int floorX1 = (int) Math.floor(x + 1.99);
        int floorY1 = (int) Math.floor(y + 1.99);

        boolean valid = world.isSea(floorX, floorY);
        valid &= world.isSea(floorX + 1, floorY);
        valid &= world.isSea(floorX1, floorY);
        valid &= world.isSea(floorX1, floorY + 1);
        valid &= world.isSea(floorX1, floorY1);
        valid &= world.isSea(floorX + 1, floorY1);
        valid &= world.isSea(floorX, floorY1);
        valid &= world.isSea(floorX, floorY + 1);

        return valid;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
