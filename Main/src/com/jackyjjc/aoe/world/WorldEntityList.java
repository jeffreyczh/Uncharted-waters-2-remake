package com.jackyjjc.aoe.world;

import com.jackyjjc.aoe.components.Attribute;
import com.jackyjjc.aoe.entites.Entity;
import com.jackyjjc.aoe.entites.EntityFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class WorldEntityList {

    public Entity playerShip;
    private List<Entity> ships;

    public WorldEntityList() {
        this.ships = new LinkedList<>();

        this.playerShip = EntityFactory.buildShip(3000, 2495);
        this.ships.add(playerShip);
    }

    public List<Entity> getShipsInViewPort(WorldViewPort viewPort) {

        List<Entity> list = new ArrayList<>();

        for (Entity e : ships) {

            if(!e.has(Attribute.Location)) {
                continue;
            }

            Point p = e.get(Attribute.Location, Point.class);
            if(isInRange(p, viewPort.topLeft, viewPort.width, viewPort.height)) {
                list.add(e);
            }
        }

        return list;
    }

    public boolean isInRange(Point p, Point topLeft, int width, int height) {

        boolean inside;
        Point bottomRight = topLeft.add(width, height);

        /*normal case*/
        if(topLeft.x() < bottomRight.x()) {
            inside = (topLeft.x() <= p.x() && p.x() < bottomRight.x());
        } else {
            /*need to divide it into two parts to check*/
            inside = (topLeft.x() <= p.x() || p.x() < bottomRight.x());
        }

        inside &= (topLeft.y() <= p.y() && p.y() < bottomRight.y());

        bottomRight.dispose();

        return inside;
    }
}
