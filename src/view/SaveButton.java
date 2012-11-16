package view;

import game.World;
import org.newdawn.slick.SpriteSheet;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class SaveButton extends Button {

    public SaveButton(int x, int y, World world, SpriteSheet sheet) {
        super(1, x, y, world, sheet);
    }

    public void click() {
        world.save();
    }

    @Override
    public void press(boolean pressed) {
        super.press(pressed);
    }
}
