package com.jackyjjc.aoe.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.jackyjjc.aoe.GameGraphics;
import com.jackyjjc.aoe.game.AOE;
import com.jackyjjc.aoe.game.GameInput;
import com.jackyjjc.aoe.view.DisplayManager;


/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class DesktopMasterScreen implements DisplayManager, Screen {

    public enum StateType {

        LOADING(0),
        MAIN_MENU(1),
        PLAY(2);

        public int id;

        private StateType(int i) {
            this.id = i;
        }
    }

    private AbstractState[] gameStates;
    private AbstractState currentState;
    private GameInput input;

    @Override
    public void init(AOE aoe) {

        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.gameGraphics = new GameGraphics();

        this.gameStates = new AbstractState[StateType.values().length];
        this.gameStates[StateType.LOADING.id] = new LoadingState(this.gameGraphics.resourceManager);
        this.gameStates[StateType.MAIN_MENU.id] = new MainMenuState();
        this.gameStates[StateType.PLAY.id] = new GamePlayState();

        for (AbstractState s : this.gameStates) {
            s.setMasterScreen(this, aoe);
        }

        this.input = new GameInput();
        Gdx.input.setInputProcessor(input);

        changeState(StateType.LOADING);
    }

    @Override
    public Screen getStartScreen() {
        return this;
    }

    private GameGraphics gameGraphics;

    private static final long TICKS_PER_SEC = 60;
    private static final double MILLISEC_PER_TICK = 1000.0 / TICKS_PER_SEC;

    private double timeDiff;
    private double timeAcc;


    public void changeState(StateType to) {

        this.gameStates[to.id].init(this.gameGraphics.resourceManager);
        this.currentState = this.gameStates[to.id];

        //reset the game
        this.timeDiff = 0;
    }

    private OrthographicCamera camera;

    @Override
    public void render(float delta) {

        timeAcc += delta * 1000 - timeDiff;
        long startTime = System.currentTimeMillis();

        while(timeAcc > MILLISEC_PER_TICK) {
            /*FIXME: the time here is possible a bug*/
            currentState.update(input);
            timeAcc -= MILLISEC_PER_TICK;
            input.update();
        }


        timeDiff = (System.currentTimeMillis() - startTime) / 1000.0;

        camera.update();

        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        gameGraphics.spriteBatch.setProjectionMatrix(camera.combined);
        currentState.render(gameGraphics, delta);
    }

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
