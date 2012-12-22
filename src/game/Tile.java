package game;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class Tile {

    public static boolean isSea(byte id) {
        //only tiles in this range are sea
        return id <= 50;
    }

    public static boolean isLand(byte id) {
        return (id > 50
                && !(id >= 64 && id <= 69)
                && !(id >= 71 && id <= 76)
                && !(id >= 78 && id <= 79)
                && !(id >= 82 && id <= 83));
    }
}
