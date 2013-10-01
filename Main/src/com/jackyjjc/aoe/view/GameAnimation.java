package com.jackyjjc.aoe.view;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jackyjjc.aoe.components.Direction;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class GameAnimation {

    private Map<Direction, Animation> map;

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

        this.map = new HashMap<Direction, Animation>(8);
        this.map.put(Direction.UP, up);
        this.map.put(Direction.DOWN, down);
        this.map.put(Direction.LEFT, left);
        this.map.put(Direction.RIGHT, right);
        this.map.put(Direction.UPLEFT, upleft);
        this.map.put(Direction.UPRIGHT, upright);
        this.map.put(Direction.DOWNLEFT, downleft);
        this.map.put(Direction.DOWNRIGHT, downright);

        if(loop) {
            for (Animation a : this.map.values()) {
                a.setPlayMode(Animation.LOOP);
            }
        }
    }

    public TextureRegion getKeyFrame(Direction dir, float time) {
        return map.get(dir).getKeyFrame(time);
    }
}
