package game;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public interface GameMap {

    public void render(double mapX, double mapY, int x, int y, int screenWidth, int screenHeight);

    public void setTile(int col, int row, int id);

    public double wrapCol(double x);

    public boolean isEnterable(int x, int y, Direction dir);

    public int getHeight();

    public int getWidth();
}
