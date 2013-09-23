package com.jackyjjc.aoe.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.jackyjjc.aoe.GameGraphics;
import com.jackyjjc.aoe.WorldMapRenderer;
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
        g.spriteBatch.begin();
        g.drawText(aoe.world.getGeoCoordinate(118, 358), 100, 100);
        g.spriteBatch.end();
    }

    @Override
    public void update() {
        aoe.update();
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) aoe.activeRegion.moveXBy(1);
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) aoe.activeRegion.moveXBy(-1);
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) aoe.activeRegion.moveYBy(-1);
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) aoe.activeRegion.moveYBy(1);
    }
}
