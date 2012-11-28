package game;

import org.dom4j.Node;
import org.newdawn.slick.SpriteSheet;
import utils.ResourceManager;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class NPCFactory {

    private ResourceManager resourceManager;

    public enum NPC_Type {
        GUY, MAN, WOMAN, OLDMAN;
    }

    public NPCFactory(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    public List<CharacterUnit> getNPCs(List<Node> npcNodes) {
        List<CharacterUnit> npcs = new ArrayList<>();

        for (Node node : npcNodes) {
            npcs.add(createCharacter(node));
        }

        return npcs;
    }

    private CharacterUnit createCharacter(Node node) {

        CharacterUnit unit = null;

        Map<NPC_Type, SpriteSheet> npcSprites = resourceManager.npcSprites;
        Point loc = Utils.readLocation(node);

        switch (NPC_Type.valueOf(node.valueOf("//type"))) {
            case GUY:
                unit = new CharacterUnit(loc.x, loc.y, npcSprites.get(NPC_Type.GUY));
                break;
            case MAN:
                unit = new CharacterUnit(loc.x, loc.y, npcSprites.get(NPC_Type.MAN));
                break;
            case WOMAN:
                unit = new CharacterUnit(loc.x, loc.y, npcSprites.get(NPC_Type.WOMAN));
                break;
            case OLDMAN:
                unit = new CharacterUnit(loc.x, loc.y, npcSprites.get(NPC_Type.OLDMAN));
                break;
        }


        return unit;
    }
}
