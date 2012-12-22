package view.gamePanels;

import game.World;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SpriteSheet;
import view.buttons.AnchorButton;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class AnchorSelection extends View implements IView, ViewDestroyListener {

    private static final int NUM_ROWS = 4;
    private static final int NUM_COLS = 4;
    private static final int SIZE = 16;

    public AnchorSelection(int x, int y, int parentPanelX, int parentPanelY,
                           double mapX, double mapY, SpriteSheet buttonSprite, World world) {
        super(x, y);

        int worldX, worldY;
        int startX = x - parentPanelX;
        int startY = y - parentPanelY;
        AnchorButton button;

        for(int row = 0; row < NUM_ROWS; row++) {
            for(int col = 0; col < NUM_COLS; col++) {
                if(!((col == 1 || col == 2) && (row == 1 || row == 2))) {
                    worldX = (int) (mapX + (startX + col * SIZE) / SIZE);
                    worldY = (int) (mapY + (startY + row * SIZE) / SIZE);
                    button = new AnchorButton(x + col * SIZE, y + row * SIZE, buttonSprite, worldX , worldY, world);
                    addChild(button);
                    button.addViewDestroyListener(this);
                }
            }
        }
    }

    public void renderThisView(Graphics graphics) {
        graphics.setColor(new Color(255, 255, 255, 0.3f));
    }

    public void updateThisView(Input input, int mouseX, int mouseY, boolean mouseClicked) {

    }

    public void notifyViewDestroy() {
        destroyView();
    }
}
