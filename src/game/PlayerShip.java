package game;

import org.newdawn.slick.SpriteSheet;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class PlayerShip extends ShipUnit {

    private Fleet fleet;

    public PlayerShip(int x, int y, SpriteSheet sheet) {
        super(x, y, sheet);
        setSpeed(0.8);
        fleet = new Fleet();
    }

    @Override
    public void setSpeed(double speed) {
        super.setSpeed(Math.floor(Math.min(speed * 16, 16)) / 16);
    }
}
