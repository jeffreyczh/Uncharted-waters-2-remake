package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import utils.MenuBuilder;
import game.port.PortBuilder;
import utils.ResourceManager;
import utils.WorldBuilder;
import view.gamePanels.IView;
import view.gamePanels.SeaPanel;
import view.gamePanels.TownPanel;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class GamePlayState extends BasicGameState implements MapTransitionController {

    private Calendar calendar;
    private int id;
    private int counter;

    private PortBuilder portBuilder;

    private TownPanel townPanel;
    private SeaPanel seaPanel;

    private IView gamePanel;

    public GamePlayState(int id) {
        this.id = id;
        counter = 0;
    }

    public int getID() {
        return id;
    }

    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

        ResourceManager resourceManager = new ResourceManager();
        PlaceFactory placeFactory = new PlaceFactory();
        NPCFactory npcFactory = new NPCFactory(resourceManager);
        portBuilder = new PortBuilder(resourceManager, npcFactory, placeFactory);
        WorldBuilder worldBuilder = new WorldBuilder(resourceManager, placeFactory, this);

        MenuBuilder menuBuilder = new MenuBuilder(resourceManager);

        this.townPanel = new TownPanel(0, 0, portBuilder.buildPort(0), resourceManager, menuBuilder);
        this.seaPanel = new SeaPanel(0, 0, worldBuilder.buildWorld(), resourceManager, menuBuilder);

        gamePanel = seaPanel;

        calendar = new Calendar(new Rule(1501, 2, 27));

    }

    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        gamePanel.render(graphics);
    }

    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

        Input input = gameContainer.getInput();

        calendar.tick();
        if (counter == 3) {
            gamePanel.update(input, input.getMouseX(), input.getMouseY(), input.isMousePressed(Input.MOUSE_LEFT_BUTTON));
            counter = 0;
        }
        counter++;
    }

    public void enterPort(int id) {
        townPanel.enterPort(portBuilder.buildPort(id));
        gamePanel = townPanel;
    }

    public void leavePort() {
        townPanel.leavePort();
        gamePanel = seaPanel;
    }
}
