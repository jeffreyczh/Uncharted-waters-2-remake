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
        int screenWidth;
        int screenHeight;

        if(args.length > 0 && args[0].equals("editor")) {
            g = new Editor();
            screenWidth = 880;
            screenHeight = 480;
        } else {
            g = new Game();
            screenWidth = 640;
            screenHeight = 480;
        }

        AppGameContainer app = new AppGameContainer(g);
        app.setDisplayMode(screenWidth,screenHeight,false);
        app.start();
    }
}
