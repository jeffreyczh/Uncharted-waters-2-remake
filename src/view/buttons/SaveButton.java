package view.buttons;

import game.Map;
import game.PortMap;
import game.World;
import org.newdawn.slick.SpriteSheet;
import utils.MapManager;
import view.MapPanel;
import view.editorPanels.EditSidePanel;

import static utils.MapManager.MAP_TYPE;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class SaveButton extends Button {

    public SaveButton(int x, int y, MAP_TYPE type, SpriteSheet sheet, EditSidePanel panel, MapPanel mapPanel) {
        super(1, x, y, type, sheet, panel, mapPanel);
    }

    public void click() {

        Map map = mapPanel.getMap();

        if(type == MAP_TYPE.WORLD) {
            MapManager.getInstance().saveWorldMap(((World) map).getMap(), panel.getMapFile());
        } else {
            MapManager.getInstance().savePortMap(((PortMap)map).getMap(), panel.getMapFile());
        }

    }

    @Override
    public void press(boolean pressed) {
        super.press(pressed);
    }
}
