package utils;

import game.NPCFactory;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class ResourceManager {

    private static final String WORLD_MAP_SPRITE_LOC = "asset/tiles.png";
    private static final String PORT_MAP_SPRITE_LOC = "asset/portTile.png";
    public static final int SPRITE_SIZE = 16;
    public static final int UNIT_SPRITE_SIZE = 32;
    public static final int EDITOR_BTN_WIDTH = 100;
    public static final int EDITOR_BTN_HEIGHT = 32;

    public Map<NPCFactory.NPC_Type, SpriteSheet> npcSprites = null;

    public SpriteSheet editorButtonSheet = null;
    public SpriteSheet worldMapSpriteSheet = null;
    public SpriteSheet portMapSpriteSheet = null;
    public SpriteSheet mainShipSpriteSheet = null;
    public SpriteSheet johnSpriteSheet = null;
    public Image interfaceImage = null;


    public ResourceManager() {

        try {
            worldMapSpriteSheet = new SpriteSheet(WORLD_MAP_SPRITE_LOC, SPRITE_SIZE, SPRITE_SIZE);
            portMapSpriteSheet = new SpriteSheet(PORT_MAP_SPRITE_LOC, SPRITE_SIZE, SPRITE_SIZE);
            mainShipSpriteSheet = new SpriteSheet("asset/mainship.png", UNIT_SPRITE_SIZE, UNIT_SPRITE_SIZE);
            johnSpriteSheet = new SpriteSheet("asset/fwalk.png", UNIT_SPRITE_SIZE, UNIT_SPRITE_SIZE);
            interfaceImage = new Image("asset/interface.png");
            editorButtonSheet = new SpriteSheet("asset/editor_buttons.png", EDITOR_BTN_WIDTH, EDITOR_BTN_HEIGHT);

            npcSprites = new HashMap<>();
            for (NPCFactory.NPC_Type type : NPCFactory.NPC_Type.values()) {
                npcSprites.put(type, new SpriteSheet("asset/guy.png", UNIT_SPRITE_SIZE, UNIT_SPRITE_SIZE));
            }

        } catch (SlickException e) {
            System.err.println("Sprites reading error");
            System.exit(1);
        }
    }
}
