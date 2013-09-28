package com.jackyjjc.aoe.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.jackyjjc.aoe.view.DisplayManager;
import com.jackyjjc.aoe.world.*;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class AOE extends Game {

    public World world;
    public WorldViewPort worldViewPort;
    public WorldEntityList worldEntityList;

    private DisplayManager displayManager;
    private HumanController humanController;
    private ShipMovementController shipMovementController;

    public AOE(DisplayManager dm) {
        this.displayManager = dm;
    }

    @Override
    public void create() {
        /*
        * Initializing the display manager is the last step when creating the game
        * After this the game will start
        */
        displayManager.init(this);
        setScreen(displayManager.getStartScreen());
    }

    public void init() {

        /*Point must be init before initializing other things*/
        this.world = WorldFactory.buildWorld(Gdx.files.internal("assets/worldmap").file());
        Point.init(this.world.WIDTH, this.world.HEIGHT);
        this.worldEntityList = new WorldEntityList();

        this.worldViewPort = new WorldViewPort(40, 30, world);
        this.worldViewPort.follow(worldEntityList.playerShip);

        this.humanController = new HumanController(worldEntityList.playerShip);
        this.shipMovementController = new ShipMovementController(world, worldEntityList, worldViewPort);
    }

    public void update(GameInput input) {
        humanController.update(input);

        /*update the movement of all the ships*/
        this.shipMovementController.update();
    }
}
