package editor;

import game.NPCFactory;
import game.PlaceFactory;
import org.newdawn.slick.*;
import utils.ResourceManager;
import view.editorPanels.EditPanel;
import view.editorPanels.EditSidePanel;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public abstract class Editor extends BasicGame {

    private EditPanel editPanel;
    private EditSidePanel sidePanel;

    public Editor(String name) {
        super(name);
    }

    /**
     * This method initialize the Map PortEditor Interface
     * The map panel is the panel on the left of the editor
     * The side panel is the panel on the right
     *
     * @param gameContainer
     */
    public void init(GameContainer gameContainer) throws SlickException {
        ResourceManager resourceManager = new ResourceManager();
        initMapBuilder(resourceManager, new PlaceFactory(), new NPCFactory(resourceManager));

        editPanel = getEditPanel();
        sidePanel = getSidePanel(editPanel, resourceManager);
        editPanel.setSidePanel(sidePanel);
    }

    public void render(GameContainer gameContainer, Graphics graphics) {
        editPanel.render(graphics, gameContainer.getScreenHeight());
        sidePanel.render(graphics);
    }

    /**
     * This methods process input and update the status of the panels
     * Keyboard input will be blocked if the left mouse button is pressed
     *
     * @param gameContainer
     * @param i             the amount of the time past after last update
     */
    public void update(GameContainer gameContainer, int i) {

        Input input = gameContainer.getInput();
        boolean mouseClicked = input.isMousePressed(Input.MOUSE_LEFT_BUTTON);

        sidePanel.update(input, input.getMouseX(), input.getMouseY(), mouseClicked);
        editPanel.update(input, input.getMouseX(), input.getMouseY(), mouseClicked);

        if (!(gameContainer.getInput().isMouseButtonDown(0))) {
            if (input.isKeyDown(Input.KEY_W)) editPanel.moveY(-1);
            if (input.isKeyDown(Input.KEY_S)) editPanel.moveY(1);
            if (input.isKeyDown(Input.KEY_A)) editPanel.moveX(-1);
            if (input.isKeyDown(Input.KEY_D)) editPanel.moveX(1);
        }
    }

    abstract void initMapBuilder(ResourceManager resourceManager, PlaceFactory placeFactory, NPCFactory npcFactory);

    abstract EditPanel getEditPanel();

    abstract EditSidePanel getSidePanel(EditPanel panel, ResourceManager resourceManager);

}
