package utils;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class ResourceManager {

    private static final String RESOURCES_XML = "resources.xml";
    private static final String ASSET_LOC = "asset/";
    private static final String IMAGE_TYPE = "image";
    private static final String SPRITESHEET_TYPE = "spritesheet";

    public final Map<String, Image> imageMap;
    public final Map<String, SpriteSheet> spriteMap;

    public ResourceManager() throws SlickException {
        this.imageMap = new HashMap<>();
        this.spriteMap = new HashMap<>();
        load(ASSET_LOC + RESOURCES_XML);
    }

    public void load(String xmlFile) throws SlickException {
        Document resourceDoc = Utils.readXML(xmlFile);
        List<Node> resources = (List<Node>) resourceDoc.selectNodes("/resources/resource");

        Element e = null;
        String type = null;
        String id = null;
        String path = null;

        for (Node node : resources) {
            e = (Element) node;
            type = e.attributeValue("type");
            path = e.getText();
            id = e.attributeValue("id");

            if (type.equals(IMAGE_TYPE)) {
                addImage(e, id, path);
            } else if (type.equals(SPRITESHEET_TYPE)) {
                addSpriteSheet(e, id, path);
            }
        }
    }

    private void addImage(Element e, String id, String path) throws SlickException {

        checkPath(id, path, IMAGE_TYPE);

        Image image = null;
        try {
            image = new Image(ASSET_LOC + path);
        } catch (SlickException slickException) {
            throw new SlickException("Could not load image " + id, slickException);
        }

        imageMap.put(id, image);
    }

    private void addSpriteSheet(Element e, String id, String path) throws SlickException {

        int spriteWidth = Integer.parseInt(e.attributeValue("width"));
        int spriteHeight = Integer.parseInt(e.attributeValue("height"));

        checkPath(id, path, SPRITESHEET_TYPE);

        SpriteSheet spritesheet = null;
        try {
            spritesheet = new SpriteSheet(ASSET_LOC + path, spriteWidth, spriteHeight);
        } catch (SlickException slickException) {
            throw new SlickException("Could not load Spritesheet " + id, slickException);
        }

        spriteMap.put(id, spritesheet);
    }

    private void checkPath(String id, String path, String type) throws SlickException {
        if (path == null || path.length() == 0) {
            throw new SlickException(type + id + " has invalid path");
        }
    }
}
