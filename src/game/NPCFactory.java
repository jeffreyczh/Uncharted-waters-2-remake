package game;

import org.dom4j.Element;
import org.dom4j.Node;
import utils.ResourceManager;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class NPCFactory {

    private ResourceManager resourceManager;

    public enum NPC_Type {
        GUY
    }

    public NPCFactory(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    public List<CharacterUnit> getNPCs(List<Element> npcNodes) {

        List<CharacterUnit> npcs = new ArrayList<>();

        for (Element node : npcNodes) {
            npcs.add(createCharacter(node));
        }

        return npcs;
    }

    private CharacterUnit createCharacter(Element node) {

        CharacterUnit unit = null;

        Point loc = Utils.readLocation(node);

        switch (NPC_Type.valueOf(node.attributeValue("type"))) {
            case GUY:
                unit = new CharacterUnit(loc.x, loc.y, resourceManager.spriteMap.get(NPC_Type.GUY.toString()));
                break;
//            case MAN:
//                unit = new CharacterUnit(loc.x, loc.y, npcSprites.get(NPC_Type.MAN));
//                break;
//            case WOMAN:
//                unit = new CharacterUnit(loc.x, loc.y, npcSprites.get(NPC_Type.WOMAN));
//                break;
//            case OLDMAN:
//                unit = new CharacterUnit(loc.x, loc.y, npcSprites.get(NPC_Type.OLDMAN));
//                break;
        }


        return unit;
    }
}
