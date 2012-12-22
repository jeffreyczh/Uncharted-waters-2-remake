package utils;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import view.gamePanels.IView;
import view.gamePanels.Menu;

import java.util.List;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class MenuBuilder {

    private static final String MENU_FILE = "asset/menus.xml";
    private ResourceManager resourceManager;

    public enum MenuType {
        FLEET, CREW, INFO, NAVIGATION
    }

    public MenuBuilder(ResourceManager resourcesManager) {
        this.resourceManager = resourcesManager;
    }

    public IView buildMenu(MenuType type) {

        Document menus = Utils.readXML(MENU_FILE);
        Node node = menus.selectSingleNode("/menus/menu[@type='" + type.toString().toLowerCase() + "']");

        return parseMenu(node);
    }

    private IView parseMenu(Node node) {

        assert (node != null);
        Element e = (Element) node;
        List<Node> items = (List<Node>) node.selectNodes("./item");

        Menu menu = new Menu(Integer.parseInt(e.attributeValue("x")),
                Integer.parseInt(e.attributeValue("y")),
                Integer.parseInt(e.attributeValue("width")),
                resourceManager.spriteMap.get("MENU"));

        for (Node option : items) {
            menu.addOption(option.getText());
        }

        return menu;
    }
}
