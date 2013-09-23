package com.jackyjjc.aoe.game;/*
 * Copyright (C) 2013  Junjie CHEN
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; Version 2
 * of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

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
    }

    public void update() {

    }
}
