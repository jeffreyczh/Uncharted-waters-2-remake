package com.jackyjjc.aoe.game;

import com.jackyjjc.aoe.components.Direction;
import com.jackyjjc.aoe.entites.Ship;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class HumanController {

    private Ship ship;

    public HumanController(Ship ship) {
        this.ship = ship;
    }

    public void update(GameInput input) {

        if(input.isKeyDown(GameInput.GameKey.UP)) {

            if(input.isKeyDown(GameInput.GameKey.LEFT)) {
                ship.direction = Direction.NORTHWEST;
            } else if(input.isKeyDown(GameInput.GameKey.RIGHT)) {
                ship.direction = Direction.NORTHEAST;
            } else if(!input.isKeyDown(GameInput.GameKey.DOWN)) {
                ship.direction = Direction.NORTH;
            }

        } else if(input.isKeyDown(GameInput.GameKey.DOWN)) {

            if(input.isKeyDown(GameInput.GameKey.LEFT)) {
                ship.direction = Direction.SOUTHWEST;
            } else if(input.isKeyDown(GameInput.GameKey.RIGHT)) {
                ship.direction = Direction.SOUTHEAST;
            } else if(!input.isKeyDown(GameInput.GameKey.UP)) {
                ship.direction = Direction.SOUTH;
            }

        } else if(input.isKeyDown(GameInput.GameKey.LEFT)) {

            if(!input.isKeyDown(GameInput.GameKey.RIGHT)) {
                ship.direction = Direction.WEST;
            }

        } else if(input.isKeyDown(GameInput.GameKey.RIGHT)) {
            ship.direction = Direction.EAST;
        }
    }
}
