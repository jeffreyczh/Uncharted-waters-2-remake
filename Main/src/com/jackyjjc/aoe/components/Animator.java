package com.jackyjjc.aoe.components;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jackyjjc.aoe.view.GameAnimation;
import com.jackyjjc.aoe.view.ResourceManager;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class Animator {

    private GameAnimation animation;
    private float time;

    public Animator(String name) {
        this.animation = ResourceManager.get().getAnimation(name);
        this.time = 0;
    }

    public void update(float time) {
        this.time += time;
    }

    public TextureRegion getKeyFrame(Direction dir) {
        return animation.getKeyFrame(dir, time);
    }
}
