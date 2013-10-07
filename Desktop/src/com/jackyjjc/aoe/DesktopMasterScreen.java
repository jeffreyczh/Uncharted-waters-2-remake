package com.jackyjjc.aoe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.jackyjjc.aoe.game.AOE;
import com.jackyjjc.aoe.game.GameInput;
import com.jackyjjc.aoe.states.AbstractState;
import com.jackyjjc.aoe.states.StateManager;
import com.jackyjjc.aoe.view.IDisplayManager;


/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class DesktopMasterScreen implements IDisplayManager, Screen {

    /*time variables to make the game run at constant speed*/
    private static final long TICKS_PER_SEC = 60;
    private static final double MILLISEC_PER_TICK = 1000.0 / TICKS_PER_SEC;
    private double timeDiff;
    private double timeAcc = 0;

    /*variables associated with display and graphics*/
    private OrthographicCamera camera;
    private GameGraphics gameGraphics;

    /*this handles the state transition of the game*/
    private StateManager stateManager;

    /*this stores the game input*/
    private GameInput input;

    @Override
    public void init(AOE aoe) {

        /*Initialize the game states*/
        this.stateManager = new StateManager(aoe);

        /*Initialize the game input handler*/
        this.input = new GameInput();
        Gdx.input.setInputProcessor(input);

        /*Initialize the game graphics and camera*/
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.gameGraphics = new GameGraphics();
    }

    @Override
    public void render(float delta) {

        AbstractState curState = stateManager.getCurrentState();

        timeAcc += delta * 1000 - timeDiff;
        long startTime = System.currentTimeMillis();

        while(timeAcc >= MILLISEC_PER_TICK) {
            curState.update(input);
            timeAcc -= MILLISEC_PER_TICK;
            input.update();
        }

        timeDiff = (System.currentTimeMillis() - startTime) / 1000.0;

        camera.update();

        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        gameGraphics.spriteBatch.setProjectionMatrix(camera.combined);
        curState.render(gameGraphics, delta);
    }

    @Override
    public Screen getStartScreen() {
        return this;
    }

    /*The following are the required methods for the Libgdx Screen Interface*/

    @Override
    public void resize(int width, int height) {
        /*Game is not resizable for now*/
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        this.gameGraphics.dispose();
    }
}
