package view.buttons;

import game.Map;
import game.PortMap;
import game.World;
import org.newdawn.slick.SpriteSheet;
import utils.MapManager;
import view.MapPanel;

import static utils.MapManager.MAP_TYPE;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class ExportButton extends Button {

    public ExportButton(int x, int y, MAP_TYPE type, SpriteSheet sheet, MapPanel mapPanel) {
        super(2, x, y, type, sheet, null, mapPanel);
    }

    @Override
    public void click() {
        JFileChooser chooser = new JFileChooser();
        Map map = mapPanel.getMap();
        if(chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            if(!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    System.err.println("Export IO Error");
                    System.exit(1);
                }
            }
            if(file.canWrite()) {
                if(type == MAP_TYPE.WORLD) {
                    MapManager.getInstance().saveWorldMap(((World) map).getMap(), file);
                } else if(type == MAP_TYPE.PORT) {
                    MapManager.getInstance().savePortMap(((PortMap)map).getMap(), file);
                }
            } else {
                System.err.println("Output file can not be written");
            }
        }
    }

    @Override
    public void press(boolean pressed) {
        super.press(pressed);
    }
}
