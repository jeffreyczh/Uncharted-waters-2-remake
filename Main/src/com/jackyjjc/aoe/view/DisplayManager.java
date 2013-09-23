package com.jackyjjc.aoe.view;

import com.badlogic.gdx.Screen;
import com.jackyjjc.aoe.game.AOE;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public interface DisplayManager {

    void init(AOE aoe);

    Screen getStartScreen();
}
