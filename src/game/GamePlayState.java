package game;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import utils.PortBuilder;
import utils.ResourceManager;
import utils.WorldBuilder;
import view.LandNavigatePanel;
import view.NavigatePanel;
import view.Panel;
import view.SeaNavigatePanel;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class GamePlayState extends BasicGameState {

    private Calendar calendar;
    private Panel middlePanel;
    private int id;
    private int counter;

    private ResourceManager resourceManager;

    public GamePlayState(int id) {
        this.id = id;
        counter = 0;
    }

    public int getID() {
        return id;
    }

    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

        this.resourceManager = new ResourceManager();
        PlaceFactory placeFactory = new PlaceFactory();
        NPCFactory npcFactory = new NPCFactory(resourceManager);
        PortBuilder portBuilder = new PortBuilder(resourceManager, npcFactory, placeFactory);
        WorldBuilder worldBuilder = new WorldBuilder(resourceManager, placeFactory);


        //NavigatePanel navigatePanel = new SeaNavigatePanel(128,0, worldBuilder.buildWorld());
        NavigatePanel navigatePanel = new LandNavigatePanel(96, 8, portBuilder.buildPort(1));
        middlePanel = navigatePanel;
        //MoveableUnit player = new PlayerShip(118, 358, resourceManager.mainShipSpriteSheet);
        MoveableUnit player = new PlayerCharacter(34, 69, resourceManager.johnSpriteSheet);
        navigatePanel.setPlayer(player);
        calendar = new Calendar(new Rule(1501, 2, 27));

    }

    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        middlePanel.render(graphics);
        resourceManager.interfaceImage.draw(0, 0);
    }

    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

        Input input = gameContainer.getInput();

        calendar.tick();
        if (counter == 3) {
            middlePanel.update(input, input.getMouseX(), input.getMouseY());
            counter = 0;
        }
        counter++;
    }
}
