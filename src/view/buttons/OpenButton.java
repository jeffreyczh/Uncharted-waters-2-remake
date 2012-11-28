package view.buttons;

import org.newdawn.slick.SpriteSheet;
import utils.MapBuilder;
import utils.Utils;
import view.MapPanel;
import view.editorPanels.EditSidePanel;

import javax.swing.*;
import java.io.File;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class OpenButton extends Button {

    public OpenButton(int x, int y, SpriteSheet sheet, EditSidePanel panel,
                      MapPanel mapPanel, MapBuilder mapBuilder) {
        super(0, x, y, sheet, panel, mapPanel, mapBuilder);
    }

    @Override
    public void click() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(Utils.getFile(mapBuilder.getDefaultMapFile().getPath()));
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            if (file.canRead()) {
                mapPanel.setGameMap(mapBuilder.buildMap(file));
                panel.setFile(file);
            } else {
                System.err.println("Cannot open file: file cannot be read");
            }
        }
    }

    @Override
    public void press(boolean pressed) {
        super.press(pressed);
    }
}
