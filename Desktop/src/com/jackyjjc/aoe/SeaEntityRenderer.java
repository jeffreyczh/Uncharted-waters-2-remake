package com.jackyjjc.aoe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jackyjjc.aoe.components.Animator;
import com.jackyjjc.aoe.components.DirValues;
import com.jackyjjc.aoe.components.Point;
import com.jackyjjc.aoe.entites.Entity;
import com.jackyjjc.aoe.game.ActiveRegion;

import static com.jackyjjc.aoe.components.Attribute.*;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class SeaEntityRenderer {

    private ActiveRegion activeRegion;

    public SeaEntityRenderer(ActiveRegion a) {
        this.activeRegion = a;
    }

    public void render(GameGraphics g, float time, int pixelsPerTile) {

        g.spriteBatch.begin();
            for(Entity e : activeRegion.getShipsInRange()) {

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
                                   pixelsPerTile * activeRegion.relativeDistX(p.x),
                                   (Gdx.graphics.getHeight() - pixelsPerTile) - pixelsPerTile * (p.y - activeRegion.topLeftY));
            }
        g.spriteBatch.end();
    }
}
