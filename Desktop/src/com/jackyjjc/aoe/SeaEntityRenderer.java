package com.jackyjjc.aoe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jackyjjc.aoe.components.Animator;
import com.jackyjjc.aoe.components.Direction;
import com.jackyjjc.aoe.entites.Ship;
import com.jackyjjc.aoe.world.Point;
import com.jackyjjc.aoe.world.WorldEntityList;
import com.jackyjjc.aoe.world.WorldViewPort;

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
            for(Ship e : entityList.getShipsInViewPort(worldViewPort)) {

                Point p = e.location;
                Direction d = e.direction;

                //System.out.println(p);

                TextureRegion t;
                Animator a = e.animator;
                a.update(time);
                t = a.getKeyFrame(d);

                g.spriteBatch.draw(t,
                                   worldViewPort.relativeDistX(p.x()), //- worldViewPort.xOff,
                                    //FIXME: figure out why * 2
                                   (Gdx.graphics.getHeight() - pixelsPerTile * 2) - (p.y() - worldViewPort.topLeft.y()));
            }
        g.spriteBatch.end();
    }
}
