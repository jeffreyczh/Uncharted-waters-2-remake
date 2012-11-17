package game;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import view.EnvironmentStatusPanel;
import view.SailingPanel;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class GamePlayState extends BasicGameState {

    private static final int START_X = 118;
    private static final int START_Y = 358;

    private Calendar calendar;
    private EnvironmentStatusPanel envPanel;
    private SailingPanel sailingPanel;
    private int id;

    public GamePlayState(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        sailingPanel = new SailingPanel(128,0, new SpriteSheet("asset/tiles.png", 16, 16));
        Player player = new Player(START_X, START_Y, new SpriteSheet("asset/mainship.png", 32, 32));
        sailingPanel.setPlayer(player);
        envPanel = new EnvironmentStatusPanel(768, 0, null);
        calendar = new Calendar(new Rule(1501,2,27));

    }

    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        sailingPanel.render();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, 128, 480);
        envPanel.render(graphics, calendar);
    }

    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        calendar.tick(sailingPanel, gameContainer.getInput());
    }
}
