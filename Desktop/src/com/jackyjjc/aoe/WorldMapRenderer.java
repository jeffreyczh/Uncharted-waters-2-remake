package com.jackyjjc.aoe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jackyjjc.aoe.game.ActiveRegion;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class WorldMapRenderer {

    /*
     *FIXME: This class is coupled with ActiveRegion and world_to_tile Ratio
     *       It assumes the pixelsPerTile = world_to_tile ratio
     */

    private SeaEntityRenderer seaEntityRenderer;

    private TextureRegion[][] tileAtlas;
    private int pixelsPerTile;

    private ActiveRegion activeRegion;

    /**
     * TODO: Need to fix this if resizing is supported
     */
    private int windowHeight;

    public WorldMapRenderer(ActiveRegion a, Texture t, int pixelsPerTile) {

        this.activeRegion = a;
        this.windowHeight = Gdx.graphics.getHeight();

        setTileAtlas(t, pixelsPerTile);

        this.seaEntityRenderer = new SeaEntityRenderer(a);
    }

    public void render(GameGraphics g, float time) {

        assert (this.pixelsPerTile != 0);
        assert (this.tileAtlas != null);

        int[][] tiles = activeRegion.getTilesInRegion();

        g.spriteBatch.begin();
        for(int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[0].length; x++) {
                g.spriteBatch.draw(lookupSprite(tiles[y][x]),
                                   x * pixelsPerTile - activeRegion.xOff,
                                   (windowHeight - pixelsPerTile) - y * pixelsPerTile + activeRegion.yOff);
            }
        }

        g.drawText(activeRegion.topLeftX + "," + activeRegion.topLeftY, 10, windowHeight - 20);
        g.spriteBatch.end();

        /*Render sea entity on top of the map*/
        seaEntityRenderer.render(g, time, pixelsPerTile);
    }

    /**
     * Given an id of the tile, return the corresponding tile sprite
     */
    private TextureRegion lookupSprite(int id) {

        int row = id / tileAtlas[0].length;
        int col = id % tileAtlas[0].length;

        return tileAtlas[row][col];
    }

    /**
     * set the atlas of the tile set, the tile should be squared
     * the dimension of the tile is pixelsPerTile
     *
     * @param tileAtlas the atlas contains all the tile sprites
     * @param pixelsPerTile the dimension of a single tile sprite
     */
    public void setTileAtlas(Texture tileAtlas, int pixelsPerTile) {

        assert (tileAtlas.getWidth() > pixelsPerTile && tileAtlas.getWidth() % pixelsPerTile == 0);
        assert (tileAtlas.getHeight() > pixelsPerTile && tileAtlas.getHeight() % pixelsPerTile == 0);

        this.tileAtlas = TextureRegion.split(tileAtlas, pixelsPerTile, pixelsPerTile);
        this.pixelsPerTile = pixelsPerTile;
    }

}

