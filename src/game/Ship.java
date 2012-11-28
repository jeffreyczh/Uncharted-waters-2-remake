package game;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class Ship {

    private String name;

    private int maxCap;
    private int curCap;

    private int maxDurability;
    private int curDurability;

    private int maxCannonCap;
    private int curCannonCap;

    private int maxTurningSpeed;
    private int curTurningSpeed;

    private int maxRowingSpeed;
    private int curRowingSpeed;

    private int maxSailors;
    private int minSailors;
    private int curSailors;

    public Ship(String name, int[] details) {
        this.name = name;
        readDetails(details);
    }

    private void readDetails(int[] details) {

        assert (details.length == 13);

        maxCap = details[0];
        curCap = details[1];
        maxDurability = details[2];
        curDurability = details[3];
        maxCannonCap = details[4];
        curCannonCap = details[5];
        maxTurningSpeed = details[6];
        curTurningSpeed = details[7];
        maxRowingSpeed = details[8];
        curRowingSpeed = details[9];
        maxSailors = details[10];
        minSailors = details[11];
        curSailors = details[12];
    }

    public int getMaxCap() {
        return maxCap;
    }

    public void setMaxCap(int maxCap) {
        this.maxCap = maxCap;
    }

    public int getCurCap() {
        return curCap;
    }

    public void setCurCap(int curCap) {
        this.curCap = curCap;
    }

    public int getMaxDurability() {
        return maxDurability;
    }

    public void setMaxDurability(int maxDurability) {
        this.maxDurability = maxDurability;
    }

    public int getCurDurability() {
        return curDurability;
    }

    public void setCurDurability(int curDurability) {
        this.curDurability = curDurability;
    }

    public int getMaxCannonCap() {
        return maxCannonCap;
    }

    public void setMaxCannonCap(int maxCannonCap) {
        this.maxCannonCap = maxCannonCap;
    }

    public int getCurCannonCap() {
        return curCannonCap;
    }

    public void setCurCannonCap(int curCannonCap) {
        this.curCannonCap = curCannonCap;
    }

    public int getMaxTurningSpeed() {
        return maxTurningSpeed;
    }

    public void setMaxTurningSpeed(int maxTurningSpeed) {
        this.maxTurningSpeed = maxTurningSpeed;
    }

    public int getCurTurningSpeed() {
        return curTurningSpeed;
    }

    public void setCurTurningSpeed(int curTurningSpeed) {
        this.curTurningSpeed = curTurningSpeed;
    }

    public int getMaxRowingSpeed() {
        return maxRowingSpeed;
    }

    public void setMaxRowingSpeed(int maxRowingSpeed) {
        this.maxRowingSpeed = maxRowingSpeed;
    }

    public int getCurRowingSpeed() {
        return curRowingSpeed;
    }

    public void setCurRowingSpeed(int curRowingSpeed) {
        this.curRowingSpeed = curRowingSpeed;
    }

    public int getMaxSailors() {
        return maxSailors;
    }

    public void setMaxSailors(int maxSailors) {
        this.maxSailors = maxSailors;
    }

    public int getMinSailors() {
        return minSailors;
    }

    public void setMinSailors(int minSailors) {
        this.minSailors = minSailors;
    }

    public int getCurSailors() {
        return curSailors;
    }

    public void setCurSailors(int curSailors) {
        this.curSailors = curSailors;
    }

}
