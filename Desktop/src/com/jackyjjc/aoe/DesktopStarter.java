package com.jackyjjc.aoe;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jackyjjc.aoe.game.AOE;
import com.jackyjjc.aoe.states.DesktopMasterScreen;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class DesktopStarter {

    private static final float VERSION = 0.1f;

    public static void main(String[] args) {

        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Age of Exploration v" + VERSION;
        cfg.useGL20 = true;
        cfg.vSyncEnabled = false;
        cfg.resizable = false;
        cfg.width = 640;
        cfg.height = 480;

        new LwjglApplication(new AOE(new DesktopMasterScreen()), cfg);
    }
}
