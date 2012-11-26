package view.buttons;

import game.Map;
import game.World;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import utils.MapManager;
import view.MapPanel;
import view.editorPanels.EditSidePanel;

import static utils.MapManager.MAP_TYPE;

import javax.swing.*;
import java.io.File;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class OpenButton extends Button {

    public OpenButton(int x, int y, MAP_TYPE type, SpriteSheet sheet, EditSidePanel panel, MapPanel mapPanel) {
        super(0, x, y, type, sheet, panel, mapPanel);
    }

    @Override
    public void click() throws SlickException {
        JFileChooser chooser = new JFileChooser();
        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            if(file.canRead()) {
                if(type == MAP_TYPE.PORT) {
                    mapPanel.setMap(MapManager.getInstance().buildPort(file));
                } else if(type == MAP_TYPE.WORLD) {
                    mapPanel.setMap(MapManager.getInstance().buildWorld(file));
                }
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
