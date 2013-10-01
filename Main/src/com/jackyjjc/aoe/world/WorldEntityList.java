package com.jackyjjc.aoe.world;

import com.jackyjjc.aoe.entites.EntityFactory;
import com.jackyjjc.aoe.entites.Ship;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class WorldEntityList {

    public Ship playerShip;
    private List<Ship> ships;

    public WorldEntityList() {
        this.ships = new LinkedList<>();

        this.playerShip = EntityFactory.buildShip(3480, 121);
        this.ships.add(playerShip);
    }

    public List<Ship> getShipsInViewPort(WorldViewPort viewPort) {

        List<Ship> list = new ArrayList<>();

        for (Ship e : ships) {

            Point p = e.location;
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
