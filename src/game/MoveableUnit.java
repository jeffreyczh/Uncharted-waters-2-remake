package game;

import org.newdawn.slick.SpriteSheet;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public abstract class MoveableUnit {

    private GameMap map;

    protected double x;
    protected double y;
    protected Direction direction;
    private double speed;

    protected SpriteSheet sheet;

    protected boolean blocked;

    public MoveableUnit(int x, int y, SpriteSheet sheet) {
        this.x = x;
        this.y = y;
        this.direction = Direction.DOWN;
        this.sheet = sheet;
    }

    /**
     * This method move the player by either the speed of the player
     * when the player can move to the next location or the amount
     * to the edge of the next colliding tile
     *
     * @param direction the direction the player moving to
     * @return the amount the player had moved
     */
    public void move(Direction direction) {

        blocked = true;

        if (direction == Direction.UP) {

            if (isValidMove(x, y - speed, direction)) {
                y = y - speed;
                blocked = false;
            }

        } else if (direction == Direction.DOWN) {
            if (isValidMove(x, y + speed, direction)) {
                y = y + speed;
                blocked = false;
            }

        } else if (direction == Direction.LEFT) {
            if (isValidMove(x - speed, y, direction)) {
                x = x - speed;
                blocked = false;
            }
        } else {
            if (isValidMove(x + speed, y, direction)) {
                x = x + speed;
                blocked = false;
            }
        }

        blockedMovement();

        x = map.wrapCol(x);

        this.direction = direction;

    }

    public abstract boolean isValidMove(double x, double y, Direction dir);

    protected void blockedMovement() {
    }

    public abstract void render(int screenX, int screenY, double worldX, double worldY);


    /* getters and setters */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setSprite(SpriteSheet sheet) {
        this.sheet = sheet;
    }

    public GameMap getMap() {
        return map;
    }

    public void setMap(GameMap map) {
        this.map = map;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
