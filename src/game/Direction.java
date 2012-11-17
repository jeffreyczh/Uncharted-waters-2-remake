package game;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public enum Direction {

    LEFT(0), UP(1), RIGHT(2), DOWN(3);

    private int id;

    private Direction(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

}
