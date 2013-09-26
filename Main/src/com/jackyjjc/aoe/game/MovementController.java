package com.jackyjjc.aoe.game;

import com.jackyjjc.aoe.entites.Entity;

import java.util.List;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class MovementController {

    private ActiveRegion activeRegion;

    public MovementController(ActiveRegion a) {
        this.activeRegion = a;
    }

    public void update() {
        List<Entity> entities = activeRegion.getShipsInRange();

    }
}
