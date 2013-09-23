package com.jackyjjc.aoe.view;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jackyjjc.aoe.components.DirValues;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class GameAnimation {

    private Map<DirValues, Animation> map;

    public GameAnimation(TextureRegion[] up, TextureRegion[] down,
                         TextureRegion[] left, TextureRegion[] right, float t, boolean loop) {

        Animation l = new Animation(t, left);
        Animation r = new Animation(t, right);

        setMap(new Animation(t, up), new Animation(t, down), l, r, l, r, l, r, loop);
    }

    private void setMap(Animation up, Animation down,
                        Animation left, Animation right,
                        Animation upleft, Animation upright,
                        Animation downleft, Animation downright, boolean loop) {

        this.map = new HashMap<DirValues, Animation>(8);
        this.map.put(DirValues.UP, up);
        this.map.put(DirValues.DOWN, down);
        this.map.put(DirValues.LEFT, left);
        this.map.put(DirValues.RIGHT, right);
        this.map.put(DirValues.UPLEFT, upleft);
        this.map.put(DirValues.UPRIGHT, upright);
        this.map.put(DirValues.DOWNLEFT, downleft);
        this.map.put(DirValues.DOWNRIGHT, downright);

        if(loop) {
            for (Animation a : this.map.values()) {
                a.setPlayMode(Animation.LOOP);
            }
        }
    }

    public TextureRegion getKeyFrame(DirValues dir, float time) {
        return map.get(dir).getKeyFrame(time);
    }
}
