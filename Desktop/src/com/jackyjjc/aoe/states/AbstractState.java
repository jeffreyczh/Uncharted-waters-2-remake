package com.jackyjjc.aoe.states;

import com.jackyjjc.aoe.GameGraphics;
import com.jackyjjc.aoe.game.AOE;
import com.jackyjjc.aoe.game.GameInput;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public abstract class AbstractState {

    public abstract void init();

    public abstract void render(GameGraphics gameGraphics, float time);

    public abstract void update(GameInput input);

    private StateManager stateManager;
    protected AOE aoe;

    public final void setStateManager(StateManager m) {
        this.stateManager = m;
    }

    public final void setGame(AOE aoe) {
        this.aoe = aoe;
    }

    public final void enterState(StateManager.StateType to) {
        stateManager.changeState(to);
    }
}
