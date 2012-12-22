package main;

import editor.MapEditor;
import editor.PortEditor;
import game.GamePlayState;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class Game extends StateBasedGame {

    private static final String VERSION = "0.1.5";
    private static final int GAME_PLAY_STATE = 0;

    public Game() {
        super("DK " + VERSION);
    }

    public void initStatesList(GameContainer gameContainer) throws SlickException {
        addState(new GamePlayState(GAME_PLAY_STATE));
    }

    public static void main(String[] args) throws SlickException {

        org.newdawn.slick.Game g = null;
        int screenWidth = 0;
        int screenHeight = 0;

        if (args.length > 0) {
            if (args[0].equals("map_editor")) {
                g = new MapEditor();
                screenWidth = 880;
                screenHeight = 480;
            } else if (args[0].equals("port_editor")) {
                g = new PortEditor();
                screenWidth = 1248;
                screenHeight = 752;
            }
        } else {
            g = new Game();
            screenWidth = 640;
            screenHeight = 400;
        }

        AppGameContainer app = new AppGameContainer(g);
        app.setDisplayMode(screenWidth, screenHeight, false);
        app.setMaximumLogicUpdateInterval(16);
        app.setMinimumLogicUpdateInterval(16);
        app.start();
    }
}
