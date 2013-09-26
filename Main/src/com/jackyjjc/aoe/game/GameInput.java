package com.jackyjjc.aoe.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class GameInput implements InputProcessor {

    private class KeyState {

        public boolean down;
        public int presses;

        public void down() {
            this.down = true;
        }

        public void release() {
            this.down = false;
            this.presses++;
        }

        public void update() {
            presses = Math.max(presses - 1, 0);
        }
    }

    public enum GameKey {
        UP, DOWN, LEFT, RIGHT,
        CONFIRM, CANCEL
    }

    private Map<GameKey, KeyState> keys;
    private Map<Integer, GameKey> keyMap;

    public GameInput() {
        this.keys = new HashMap<>();
        for (GameKey k : GameKey.values()) {
            this.keys.put(k, new KeyState());
        }
        useDefaultKeyMap();
    }

    private void useDefaultKeyMap() {
        this.keyMap = new HashMap<>();
        this.keyMap.put(Input.Keys.UP, GameKey.UP);
        this.keyMap.put(Input.Keys.DOWN, GameKey.DOWN);
        this.keyMap.put(Input.Keys.LEFT, GameKey.LEFT);
        this.keyMap.put(Input.Keys.RIGHT, GameKey.RIGHT);
        this.keyMap.put(Input.Keys.ENTER, GameKey.CONFIRM);
        this.keyMap.put(Input.Keys.SPACE, GameKey.CONFIRM);
        this.keyMap.put(Input.Keys.Z, GameKey.CONFIRM);
        this.keyMap.put(Input.Keys.ESCAPE, GameKey.CANCEL);
        this.keyMap.put(Input.Keys.X, GameKey.CANCEL);
    }

    public boolean isKeyDown(GameKey k) {
        return keys.get(k).down;
    }

    public boolean isKeyPressed(GameKey k) {
        return keys.get(k).presses > 0;
    }

    public void update() {

        for (GameKey k : GameKey.values()) {
            keys.get(k).update();
        }
    }

    @Override
    public boolean keyDown(int i) {

        if(keyMap.containsKey(i)) {
            keys.get(keyMap.get(i)).down();
        }

        return true;
    }

    @Override
    public boolean keyUp(int i) {
        if(keyMap.containsKey(i)) {
            keys.get(keyMap.get(i)).release();
        }

        return true;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i2, int i3, int i4) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i2, int i3, int i4) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i2, int i3) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i2) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }
}
