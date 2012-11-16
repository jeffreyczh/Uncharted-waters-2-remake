package view;

import game.World;
import org.newdawn.slick.SpriteSheet;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class ExportButton extends Button {

    public ExportButton(int x, int y, World world, SpriteSheet sheet) {
        super(2, x, y, world, sheet);
    }

    @Override
    public void click() {
        JFileChooser chooser = new JFileChooser();
        if(chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            if(!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    System.out.println("IO Error");
                    System.exit(1);
                }
            }
            if(file.canWrite()) {
                world.save(file);
            }
        }
    }

    @Override
    public void press(boolean pressed) {
        super.press(pressed);
    }
}
