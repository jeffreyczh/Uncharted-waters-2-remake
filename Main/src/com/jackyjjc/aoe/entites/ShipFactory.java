package com.jackyjjc.aoe.entites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.jackyjjc.aoe.Log;

import java.util.HashMap;


/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class ShipFactory {

    private HashMap<String, ShipStatus> ships;

    private ShipFactory() {};

    public static ShipFactory create() {

        Json json = new Json();
        ShipFactory factory = json.fromJson(ShipFactory.class, Gdx.files.internal("assets/ships.json"));

        return factory;
    }

    public ShipStatus get(String name) {

        if(!this.ships.containsKey(name)) {
            Log.warning("searching ship status for invalid ship name");
            return null;
        }

        return ships.get(name);
    }
}
