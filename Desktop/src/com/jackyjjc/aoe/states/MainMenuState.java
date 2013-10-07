package com.jackyjjc.aoe.states;

import com.badlogic.gdx.Gdx;
import com.jackyjjc.aoe.GameGraphics;
import com.jackyjjc.aoe.game.GameInput;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class MainMenuState extends AbstractState {

    private boolean set;

    @Override
    public void init() {
        set = false;
        Gdx.app.log(getClass().getCanonicalName(), "Enter State");
    }

    @Override
    public void render(GameGraphics gameGraphics, float time) {
    }

    @Override
    public void update(GameInput input) {
        if(!set) {
            enterState(StateManager.StateType.PLAY);
            set = true;
        }
    }
}
