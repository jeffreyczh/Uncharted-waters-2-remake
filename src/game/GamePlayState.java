package game;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import view.SailingPanel;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class GamePlayState extends BasicGameState {

    private SailingPanel sailingPanel;
    private int id;

    public GamePlayState(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        sailingPanel = new SailingPanel(0,0, new SpriteSheet("asset/tiles.png", 16, 16));
    }

    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        sailingPanel.render();
    }

    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

        Input input = gameContainer.getInput();

        if(!(gameContainer.getInput().isMouseButtonDown(0))) {
            if(input.isKeyDown(Input.KEY_W)) sailingPanel.moveY(-1);
            if(input.isKeyDown(Input.KEY_S)) sailingPanel.moveY(1);
            if(input.isKeyDown(Input.KEY_A)) sailingPanel.moveX(-1);
            if(input.isKeyDown(Input.KEY_D)) sailingPanel.moveX(1);
        }
    }
}
