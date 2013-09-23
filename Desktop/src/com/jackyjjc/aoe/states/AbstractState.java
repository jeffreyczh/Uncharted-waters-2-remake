package com.jackyjjc.aoe.states;

import com.jackyjjc.aoe.GameGraphics;
import com.jackyjjc.aoe.game.AOE;
import com.jackyjjc.aoe.view.ResourceManager;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public abstract class AbstractState {

    public abstract void init(ResourceManager resourceManager);

    public abstract void render(GameGraphics gameGraphics, float time);

    public abstract void update();

    private DesktopMasterScreen masterScreen;
    protected AOE aoe;

    public final void setMasterScreen(DesktopMasterScreen m, AOE aoe) {
        this.masterScreen = m;
        this.aoe = aoe;
    }

    public final void enterState(DesktopMasterScreen.StateType to) {
        masterScreen.changeState(to);
    }
}
