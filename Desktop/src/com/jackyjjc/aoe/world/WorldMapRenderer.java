package com.jackyjjc.aoe.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jackyjjc.aoe.GameGraphics;
import com.jackyjjc.aoe.SeaEntityRenderer;
import com.jackyjjc.aoe.game.GameCalendar;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class WorldMapRenderer {

    /*
     *FIXME: This class is coupled with WorldViewPort and world_to_tile Ratio
     *       It assumes the pixelsPerTile = world_to_tile ratio
     */

    private SeaEntityRenderer seaEntityRenderer;

    private TextureRegion[][] tileAtlas;
    private int pixelsPerTile;

    private GameCalendar calendar;
    private WorldViewPort worldViewPort;

    /**
     * TODO: Need to fix this if resizing is supported
     */
    private int windowHeight;

    public WorldMapRenderer(GameCalendar c, WorldViewPort a, WorldEntityList e, Texture t, int pixelsPerTile) {

        this.calendar = c;
        this.worldViewPort = a;
        this.windowHeight = Gdx.graphics.getHeight();

        setTileAtlas(t, pixelsPerTile);

        this.seaEntityRenderer = new SeaEntityRenderer(a, e);
    }

    public void render(GameGraphics g, float time) {

        assert (this.pixelsPerTile != 0);
        assert (this.tileAtlas != null);

        int[][] tiles = worldViewPort.getTilesInRegion();

        g.spriteBatch.begin();
        for(int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[0].length; x++) {
                g.spriteBatch.draw(lookupSprite(tiles[y][x]),
                                   x * pixelsPerTile - worldViewPort.xOff,
                                   (windowHeight - pixelsPerTile) - y * pixelsPerTile + worldViewPort.yOff);
            }
        }

        g.drawText(worldViewPort.topLeft.x() + "," + worldViewPort.topLeft.y(), 10, windowHeight - 20);
        g.drawText(calendar.getTimeString(), 30, 30);
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

