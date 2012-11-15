package game;

import editor.Editor;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class Game extends StateBasedGame {

    private static final float VERSION = 0.01f;
    private static final int GAME_PLAY_STATE = 0;

    public Game() {
        super("DK " + VERSION);
    }

    public void initStatesList(GameContainer gameContainer) throws SlickException {
        addState(new GamePlayState(0));
    }

    public static void main(String[] args) throws SlickException {

        org.newdawn.slick.Game g;

        if(args.equals("edit")) {
            g = new Editor();
        } else {
            g = new Game();
        }

        AppGameContainer app = new AppGameContainer(g);
        app.setDisplayMode(640,480,false);
        app.start();
    }
}
