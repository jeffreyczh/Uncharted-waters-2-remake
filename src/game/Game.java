package game;

import editor.MapEditor;
import editor.PortEditor;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class Game extends StateBasedGame {

    private static final float VERSION = 0.02f;
    private static final int GAME_PLAY_STATE = 0;

    public Game() {
        super("DK " + VERSION);
    }

    public void initStatesList(GameContainer gameContainer) throws SlickException {
        addState(new GamePlayState(0));
    }

    public static void main(String[] args) throws SlickException {

        org.newdawn.slick.Game g = null;
        int screenWidth = 0;
        int screenHeight = 0;

        if(args.length > 0) {
            if(args[0].equals("map_editor")) {
                g = new MapEditor();
                screenWidth = 880;
                screenHeight = 480;
            } else if(args[0].equals("port_editor")) {
                g = new PortEditor();
                screenWidth = 1248;
                screenHeight = 752;
            }
        } else {
            g = new Game();
            screenWidth = 928;
            screenHeight = 480;
        }

        AppGameContainer app = new AppGameContainer(g);
        app.setDisplayMode(screenWidth,screenHeight,false);
        app.setMaximumLogicUpdateInterval(16);
        app.setMinimumLogicUpdateInterval(16);
        app.start();
    }
}
