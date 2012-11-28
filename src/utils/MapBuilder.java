package utils;

import game.GameMap;

import java.io.File;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public interface MapBuilder {

    GameMap buildMap(File file);

    void saveMap(GameMap gameMap, File file);

    File getDefaultMapFile();
}
