package view.buttons;

import game.World;
import org.newdawn.slick.SpriteSheet;
import view.gamePanels.IView;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class AnchorButton extends Button implements IView {

    private World world;
    private int mapX;
    private int mapY;

    public AnchorButton(int x, int y, SpriteSheet buttonSheet, int mapX, int mapY, World world) {
        super(x, y, buttonSheet);
        this.mapX = mapX;
        this.mapY = mapY;
        this.world = world;
    }

    public void click() {
        if(world.isLandable(mapX, mapY)) {
            world.interact(mapX, mapY);
            destroyButton();
        }
    }
}
