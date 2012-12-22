package view.buttons;

import game.Direction;
import game.Point;
import utils.ResourceManager;
import view.gamePanels.IView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class ArrowButton extends Button implements IView {

    private final static Map<Direction, Point> buttonPos;

    static {
        buttonPos = new HashMap<>();
        buttonPos.put(Direction.UP, new Point(528, 303));
        buttonPos.put(Direction.DOWN, new Point(528, 369));
        buttonPos.put(Direction.LEFT, new Point(503, 328));
        buttonPos.put(Direction.RIGHT, new Point(569, 328));
    }

    public ArrowButton(Direction direction, ResourceManager resourceManager) {
        super(buttonPos.get(direction).x, buttonPos.get(direction).y,
                resourceManager.spriteMap.get(direction + "_ARROW"));
    }

    public void click() {
    }
}
