package editor;

import org.newdawn.slick.*;
import view.MapEditPanel;
import view.SidePanel;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class Editor extends BasicGame {

    private static final int SPRITE_SIZE = 16;

    private MapEditPanel mapEditPanel;
    private SidePanel sidePanel;

    private int x;
    private int y;

    public Editor() {
        super("Map editor");
    }

    /**
     * This method initialize the Map Editor Interface
     * The map panel is the panel on the left of the editor
     * The side panel is the panel on the right
     *
     * @param gameContainer
     * @throws SlickException
     */
    public void init(GameContainer gameContainer) throws SlickException {
        SpriteSheet tiles = new SpriteSheet("asset/tiles.png", SPRITE_SIZE, SPRITE_SIZE);
        mapEditPanel = new MapEditPanel(0, 0, tiles);
        sidePanel = new SidePanel(640, 0, tiles, mapEditPanel.getWorld());

        mapEditPanel.setSidePanel(sidePanel);
    }

    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        sidePanel.render(graphics);
        mapEditPanel.render();
    }

    /**
     * This methods process input and update the status of the panels
     * Keyboard input will be blocked if the left mouse button is pressed
     *
     * @param gameContainer
     * @param i the amount of the time past after last update
     * @throws SlickException
     */
    public void update(GameContainer gameContainer, int i) throws SlickException {

        Input input = gameContainer.getInput();

        sidePanel.update(input, input.getMouseX(), input.getMouseY());
        mapEditPanel.update(input, input.getMouseX(), input.getMouseY());

        if(!(gameContainer.getInput().isMouseButtonDown(0))) {
            if(input.isKeyDown(Input.KEY_W)) mapEditPanel.moveY(-1);
            if(input.isKeyDown(Input.KEY_S)) mapEditPanel.moveY(1);
            if(input.isKeyDown(Input.KEY_A)) mapEditPanel.moveX(-1);
            if(input.isKeyDown(Input.KEY_D)) mapEditPanel.moveX(1);
        }
    }
}
