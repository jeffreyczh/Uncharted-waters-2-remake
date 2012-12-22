package game.port;

import game.*;
import game.port.buildings.Place;
import org.newdawn.slick.SpriteSheet;

import java.util.List;
import java.util.Map;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class Port implements GameMap, PortInfo {

    public static final int WIDTH = 96;
    public static final int HEIGHT = 96;

    private String name;
    private String area;
    private int economy;
    private int industry;
    private int economyInvest;
    private int industryInvest;
    private double priceIndex;
    private Country country;
    private boolean capital;

    private short[][] map;
    private SpriteSheet sheet;
    private Map<Short, boolean[]> collisionMap;

    private List<CharacterUnit> npcs;
    private Map<Point, Place> buildings;

    Port(short[][] map, SpriteSheet sheet, Map<Short, boolean[]> collisionMap) {
        this.map = map;
        this.sheet = sheet;
        this.collisionMap = collisionMap;
    }

    public void render(double mapX, double mapY, int x, int y, int screenWidth, int screenHeight) {

        int numRow = screenHeight / sheet.getSubImage(0, 0).getHeight() + 1;
        int numCol = screenWidth / sheet.getSubImage(0, 0).getWidth() + 1;

        int upperLeftX = (int) Math.floor(mapX);
        int upperLeftY = (int) Math.floor(mapY);

        int offsetX = (int) ((mapX - upperLeftX) * 16);
        int offsetY = (int) ((mapY - upperLeftY) * 16);

        for (int row = 0; row < numRow; row++) {
            for (int col = 0; col < numCol; col++) {

                if (upperLeftX + col < WIDTH && upperLeftY + row < HEIGHT) {

                    int id = map[row + upperLeftY][col + upperLeftX];
                    int sprite_row = id / 33;
                    int sprite_col = id % 33;

                    sheet.getSubImage(sprite_col, sprite_row).draw((x - offsetX + col * 16),
                            (y - offsetY + row * 16));

                }
            }
        }
    }

    public void setTile(int col, int row, int id) {
        map[row][col] = (short) id;
    }

    public double wrapCol(double x) {
        return x;
    }

    public boolean isEnterable(int x, int y, Direction dir) {

        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT)
            return false;

        boolean enterable = false;

        if (collisionMap.containsKey(map[y][x])) {

            enterable = collisionMap.get(map[y][x])[dir.getID()];

            if (dir == Direction.UP && y < HEIGHT - 1 && collisionMap.containsKey(map[y + 1][x])) {
                enterable &= collisionMap.get(map[y + 1][x])[Direction.DOWN.getID()];
            } else if (dir == Direction.DOWN && y > 0 && collisionMap.containsKey(map[y - 1][x])) {
                enterable &= collisionMap.get(map[y - 1][x])[Direction.UP.getID()];
            } else if (dir == Direction.LEFT && x < WIDTH - 1 && collisionMap.containsKey(map[y][x + 1])) {
                enterable &= collisionMap.get(map[y][x + 1])[Direction.RIGHT.getID()];
            } else if (dir == Direction.RIGHT && x > 0 && collisionMap.containsKey(map[y][x - 1])) {
                enterable &= collisionMap.get(map[y][x - 1])[Direction.LEFT.getID()];
            }
        }

        return enterable;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public int getWidth() {
        return WIDTH;
    }

    public short[][] getMap() {
        return map;
    }

    void setNpcs(List<CharacterUnit> npcs) {
        this.npcs = npcs;
    }

    void setBuildings(Map<Point, Place> buildings) {
        this.buildings = buildings;
    }

    void setName(String name) {
        this.name = name;
    }

    void setArea(String area) {
        this.area = area;
    }

    void setEconomy(int amount) {
        this.economy = amount;
    }

    void setIndustry(int amount) {
        this.industry = amount;
    }

    void setEconomyInvest(int amount) {
        this.economyInvest = amount;
    }

    void setIndustryInvest(int amount) {
        this.industryInvest = amount;
    }

    void setContry(Country country, boolean capital) {
        this.country = country;
        this.capital = capital;
    }

    public String getName() {
        return name;
    }

    public String getArea() {
        return area;
    }

    public void investEconomy(int amount) {
    }

    public int getEconomy() {
        return economy;
    }

    public int getEconomyInvest() {
        return economyInvest;
    }

    public void investIndustry(int amount) {
    }

    public int getIndustry() {
        return industry;
    }

    public int getIndustryInvest() {
        return industryInvest;
    }

    public double getPriceIndex() {
        return priceIndex;
    }
    public int getPriceIndexPercentage() {
        return (int)(priceIndex * 100);
    }

    public double setPriceIndex(double index) {
        return priceIndex = index;
    }
}
