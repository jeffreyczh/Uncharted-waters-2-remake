package com.jackyjjc.aoe.game;

import com.jackyjjc.aoe.components.Attribute;
import com.jackyjjc.aoe.components.DirValues;
import com.jackyjjc.aoe.entites.Entity;
import com.jackyjjc.aoe.world.World;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class HumanController {

    private Entity ship;

    public HumanController(Entity ship) {
        this.ship = ship;
    }

    public void update(GameInput input) {

        if(input.isKeyDown(GameInput.GameKey.UP)) {

            if(input.isKeyDown(GameInput.GameKey.LEFT)) {
                ship.add(Attribute.Direction, DirValues.UPLEFT);
            } else if(input.isKeyDown(GameInput.GameKey.RIGHT)) {
                ship.add(Attribute.Direction, DirValues.UPRIGHT);
            } else if(!input.isKeyDown(GameInput.GameKey.DOWN)) {
                ship.add(Attribute.Direction, DirValues.UP);
            }
        } else if(input.isKeyDown(GameInput.GameKey.DOWN)) {

            if(input.isKeyDown(GameInput.GameKey.LEFT)) {
                ship.add(Attribute.Direction, DirValues.DOWNLEFT);
            } else if(input.isKeyDown(GameInput.GameKey.RIGHT)) {
                ship.add(Attribute.Direction, DirValues.DOWNRIGHT);
            } else if(!input.isKeyDown(GameInput.GameKey.UP)) {
                ship.add(Attribute.Direction, DirValues.DOWN);
            }
        } else if(input.isKeyDown(GameInput.GameKey.LEFT)) {
            if(!input.isKeyDown(GameInput.GameKey.RIGHT)) {
                ship.add(Attribute.Direction, DirValues.LEFT);
            }
        } else if(input.isKeyDown(GameInput.GameKey.RIGHT)) {
            ship.add(Attribute.Direction, DirValues.RIGHT);
        }
    }
}
