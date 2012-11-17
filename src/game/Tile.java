package game;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class Tile {

    public static boolean isSea(byte id) {
        //only tiles in this range are sea
        return id <= 50;
    }
}
