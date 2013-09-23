package com.jackyjjc.aoe.entites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jackyjjc.aoe.components.Animator;
import com.jackyjjc.aoe.components.Attribute;
import com.jackyjjc.aoe.components.DirValues;
import com.jackyjjc.aoe.components.Point;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class Entity {

    private Map<Attribute, Object> components;

    public Entity() {
        this.components = new HashMap<Attribute, Object>();
    }

    public boolean has(Attribute property) {
        return components.containsKey(property);
    }

    public <T> T get(Attribute c, Class<T> t) {

        T retVal = null;

        if(components.containsKey(c)) {
            retVal = (T) components.get(c);
        }

        return retVal;
    }

    public Entity add(Attribute a, Object o) {

        switch (a) {
            case Sprite:
                assert (o instanceof Texture || o instanceof TextureRegion);
                components.put(a, o);
                break;
            case Location:
                assert o instanceof Point;
                components.put(a, o);
                break;
            case Animation:
                assert o instanceof Animator;
                components.put(a, o);
                break;
            case Direction:
                assert o instanceof DirValues;
                components.put(a, o);
                break;
            default:
                break;
        }

        return this;
    }
}
