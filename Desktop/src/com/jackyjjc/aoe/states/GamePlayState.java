package com.jackyjjc.aoe.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.jackyjjc.aoe.GameGraphics;
import com.jackyjjc.aoe.WorldMapRenderer;
import com.jackyjjc.aoe.game.GameInput;
import com.jackyjjc.aoe.view.ResourceManager;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class GamePlayState extends AbstractState {

    private WorldMapRenderer worldMapRenderer;

    @Override
    public void init(ResourceManager rm) {
        Gdx.app.log(getClass().getCanonicalName(), "Enter State");

        this.worldMapRenderer = new WorldMapRenderer(aoe.activeRegion,
                                                     rm.getTexture("world_tile_set.png"), 16);
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
