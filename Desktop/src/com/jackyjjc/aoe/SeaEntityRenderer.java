package com.jackyjjc.aoe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jackyjjc.aoe.components.Animator;
import com.jackyjjc.aoe.components.DirValues;
import com.jackyjjc.aoe.world.Point;
import com.jackyjjc.aoe.entites.Entity;
import com.jackyjjc.aoe.world.WorldEntityList;
import com.jackyjjc.aoe.world.WorldViewPort;

import static com.jackyjjc.aoe.components.Attribute.*;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class SeaEntityRenderer {

    private WorldViewPort worldViewPort;
    private WorldEntityList entityList;

    public SeaEntityRenderer(WorldViewPort a, WorldEntityList e) {
        this.worldViewPort = a;
        this.entityList = e;
    }

    public void render(GameGraphics g, float time, int pixelsPerTile) {

        g.spriteBatch.begin();
            for(Entity e : entityList.getShipsInViewPort(worldViewPort)) {

                if(!e.has(Location) || !e.has(Direction)) {
                    continue;
                }

                Point p = e.get(Location, Point.class);
                DirValues d = e.get(Direction, DirValues.class);

                TextureRegion t = null;
                if(e.has(Sprite)) {
                    t = e.get(Sprite, TextureRegion.class);
                } else if(e.has(Animation)) {
                    Animator a = e.get(Animation, Animator.class);
                    a.update(time);
                    t = a.getKeyFrame(d);
                }

                g.spriteBatch.draw(t,
                                   worldViewPort.relativeDistX(p.x()),
                                   (Gdx.graphics.getHeight() - pixelsPerTile) - (p.y() - worldViewPort.topLeft.y()));
            }
        g.spriteBatch.end();
    }
}
