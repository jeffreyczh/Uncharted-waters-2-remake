package com.jackyjjc.aoe.entites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jackyjjc.aoe.components.Animator;
import com.jackyjjc.aoe.components.Attribute;
import com.jackyjjc.aoe.components.DirValues;
import com.jackyjjc.aoe.components.Point;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class Entity {

    private Map<Attribute, List<EntityValueChangeListener>> valueListenerMap;
    private Map<Attribute, Object> components;

    public Entity() {
        this.components = new HashMap<Attribute, Object>();
        this.valueListenerMap = new HashMap<>();
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
                notifyListeners(a);
                break;
            case Location:
                assert o instanceof Point;
                components.put(a, o);
                notifyListeners(a);
                break;
            case Animation:
                assert o instanceof Animator;
                components.put(a, o);
                notifyListeners(a);
                break;
            case Direction:
                assert o instanceof DirValues;
                components.put(a, o);
                notifyListeners(a);
                break;
            default:
                break;
        }

        return this;
    }

    public void listenOn(Attribute a, EntityValueChangeListener l) {
        List<EntityValueChangeListener> listeners = this.valueListenerMap.get(a);
        if(listeners == null) {
            listeners = new LinkedList<EntityValueChangeListener>();
            this.valueListenerMap.put(a, listeners);
        }

        listeners.add(l);
    }

    public void notifyListeners(Attribute a) {
        List<EntityValueChangeListener> listeners = this.valueListenerMap.get(a);
        if(listeners != null) {
            for (EntityValueChangeListener l : listeners) {
                l.notifyChange(this, a);
            }
        }
    }
}
