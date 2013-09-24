package com.jackyjjc.aoe.states;

import com.badlogic.gdx.Gdx;
import com.jackyjjc.aoe.GameGraphics;
import com.jackyjjc.aoe.game.GameInput;
import com.jackyjjc.aoe.view.ResourceManager;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class MainMenuState extends AbstractState {

    @Override
    public void init(ResourceManager rm) {
        Gdx.app.log(getClass().getCanonicalName(), "Enter State");
    }

    @Override
    public void render(GameGraphics gameGraphics, float time) {
    }

    @Override
    public void update(GameInput input) {
        enterState(DesktopMasterScreen.StateType.PLAY);
    }
}
