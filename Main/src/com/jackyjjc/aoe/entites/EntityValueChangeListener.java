package com.jackyjjc.aoe.entites;

import com.jackyjjc.aoe.components.Attribute;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public interface EntityValueChangeListener {

    public void notifyChange(Entity e, Attribute a);
}