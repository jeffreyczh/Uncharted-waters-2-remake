package com.jackyjjc.aoe.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.jackyjjc.aoe.view.DisplayManager;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class AOE extends Game {

    public World world;
    public ActiveRegion activeRegion;

    private DisplayManager displayManager;
    private HumanController humanController;

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
        this.world = World.buildWorld(Gdx.files.internal("assets/world_map").file());
        this.activeRegion = new ActiveRegion(40, 30, world);
        this.humanController = new HumanController(world, world.playerShip);
    }

    public void update(GameInput input) {
        humanController.update(input);
    }
}
