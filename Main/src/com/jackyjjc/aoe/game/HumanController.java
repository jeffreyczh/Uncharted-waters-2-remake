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
                ship.direction = Direction.UPLEFT;
            } else if(input.isKeyDown(GameInput.GameKey.RIGHT)) {
                ship.direction = Direction.UPRIGHT;
            } else if(!input.isKeyDown(GameInput.GameKey.DOWN)) {
                ship.direction = Direction.UP;
            }
        } else if(input.isKeyDown(GameInput.GameKey.DOWN)) {

            if(input.isKeyDown(GameInput.GameKey.LEFT)) {
                ship.direction = Direction.DOWNLEFT;
            } else if(input.isKeyDown(GameInput.GameKey.RIGHT)) {
                ship.direction = Direction.DOWNRIGHT;
            } else if(!input.isKeyDown(GameInput.GameKey.UP)) {
                ship.direction = Direction.DOWN;
            }
        } else if(input.isKeyDown(GameInput.GameKey.LEFT)) {
            if(!input.isKeyDown(GameInput.GameKey.RIGHT)) {
                ship.direction = Direction.LEFT;
            }
        } else if(input.isKeyDown(GameInput.GameKey.RIGHT)) {
            ship.direction = Direction.RIGHT;
        }
    }
}
