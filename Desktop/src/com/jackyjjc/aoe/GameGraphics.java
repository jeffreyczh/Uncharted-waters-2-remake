package com.jackyjjc.aoe;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jackyjjc.aoe.view.ResourceManager;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class GameGraphics {

    public ResourceManager resourceManager;
    public SpriteBatch spriteBatch;
    private BitmapFont font;

    public GameGraphics() {
        this.spriteBatch = new SpriteBatch();
        this.font = new BitmapFont();
        this.resourceManager = ResourceManager.get();

        /*Load all the textures*/
        this.resourceManager.loadTexture("world_tile_set.png");
        this.resourceManager.loadTexture("ship.png");
    }

    public void dispose() {
        this.resourceManager.dispose();
        this.spriteBatch.dispose();
        this.font.dispose();
    }

    public void drawText(String text, float x, float y) {
        this.font.draw(this.spriteBatch, text, x, y);
    }

    public BitmapFont.TextBounds getTextBound(String text) {
        return this.font.getBounds(text);
    }

    public float mid(float container, float self) {
        return (container - self) / 2;
    }
}
