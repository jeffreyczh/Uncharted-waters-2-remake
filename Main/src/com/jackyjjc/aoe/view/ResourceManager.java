package com.jackyjjc.aoe.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class ResourceManager {

    private static ResourceManager instance = null;

    public static ResourceManager get() {
        if(instance == null) {
            instance = new ResourceManager();
        }
        return instance;
    }

    private AssetManager assetManager;
    private String path;

    private Map<String, GameAnimation> gameAnimationMap;

    private ResourceManager() {
        this.assetManager = new AssetManager();
        this.path = Gdx.files.internal("assets").path() + "/";

        this.gameAnimationMap = new HashMap<String, GameAnimation>();
    }

    public Texture getTexture(String name) {

        if(!assetManager.isLoaded(path + name)) {
            Gdx.app.error(getClass().getCanonicalName(), name + " is not loaded.");
            assert false;
        }

        return assetManager.get(path + name, Texture.class);
    }

    public void loadTexture(String name) {
        this.assetManager.load(path + name, Texture.class);
    }

    /*TODO:Hack function, later should change to XML or JSON*/
    public void loadAnimations() {
        //setup the ship animation
        TextureRegion[][] regions = TextureRegion.split(getTexture("ship.png"), 32, 32);
        this.gameAnimationMap.put("ship", new GameAnimation(regions[0], regions[1], regions[2], regions[3], 0.5f, true));
    }

    public GameAnimation getAnimation(String name) {
        GameAnimation a = null;
        if(gameAnimationMap.containsKey(name)) {
            a = gameAnimationMap.get(name);
        }
        return a;
    }

    public void dispose() {
        this.assetManager.dispose();
        this.assetManager = null;
    }

    public boolean update() {

        boolean updated = this.assetManager.update();

        if(updated) {
            loadAnimations();
        }
        return updated;
    }

    public float getProgress() {
        return this.assetManager.getProgress();
    }
}
