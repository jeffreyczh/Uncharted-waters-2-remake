package view;

import game.World;
import org.newdawn.slick.SpriteSheet;

import javax.swing.*;
import java.io.File;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class OpenButton extends Button {

    public OpenButton(int x, int y, World world, SpriteSheet sheet) {
        super(0, x, y, world, sheet);
    }

    @Override
    public void click() {
        JFileChooser chooser = new JFileChooser();
        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            if(file.canRead()) {
                world.read(file);
            }
        }
    }

    @Override
    public void press(boolean pressed) {
        super.press(pressed);
    }
}
