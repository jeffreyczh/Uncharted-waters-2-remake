package com.jackyjjc.aoe.states;

import com.badlogic.gdx.Gdx;
import com.jackyjjc.aoe.GameGraphics;
import com.jackyjjc.aoe.game.GameInput;
import com.jackyjjc.aoe.view.ResourceManager;
import com.jackyjjc.aoe.world.WorldMapRenderer;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class GamePlayState extends AbstractState {

    private WorldMapRenderer worldMapRenderer;

    @Override
    public void init() {

        Gdx.app.log(getClass().getCanonicalName(), "Enter State");

        this.worldMapRenderer = new WorldMapRenderer(aoe.calendar,
                                                     aoe.worldViewPort,
                                                     aoe.worldEntityList,
                                                     ResourceManager.get().getTexture("tileset.png"), 16);
    }

    @Override
    public void render(GameGraphics g, float time) {
        worldMapRenderer.render(g, time);
    }

    @Override
    public void update(GameInput input) {
        aoe.update(input);
    }
}
