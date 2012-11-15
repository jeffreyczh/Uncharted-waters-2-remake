package game;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class GamePlayState extends BasicGameState {

    private World world;
    private int id;
    private int x;
    private int y;

    public GamePlayState(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        world = new World(new SpriteSheet("asset/tiles.png", 16, 16));
    }

    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        world.render(x, y, gameContainer.getWidth(), gameContainer.getHeight());
    }

    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if(gameContainer.getInput().isKeyDown(Input.KEY_UP)) y = Math.max(0, y - 1);
        if(gameContainer.getInput().isKeyDown(Input.KEY_DOWN)) y = Math.min(1049, y + 1);
        if(gameContainer.getInput().isKeyDown(Input.KEY_LEFT)) x = Math.max(0, x - 1);
        if(gameContainer.getInput().isKeyDown(Input.KEY_RIGHT)) x = Math.min(2117, x + 1);
    }
}
