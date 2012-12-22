package game;

import game.port.buildings.*;
import org.dom4j.Document;
import org.dom4j.Element;
import utils.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class PlaceFactory {

    public enum Building_Type {
        MARKET, HARBOUR, SHIPYARD, LODGE, CAFE,
        BANK, PALACE, FORTUNE_TELLER, CHURCH,
        SHOP, GUILD, MANSION
    }

    public PlaceFactory() {

    }

    public Map<Point, Integer> getPorts(String fileLoc) {
        Document ports = Utils.readXML(fileLoc);

        Map<Point, Integer> portMap = new HashMap<>();

        assert (ports != null);
        List<Element> portList = ports.selectNodes("/ports/port");

        for (Element node : portList) {
            Point location = Utils.readLocation(node);
            int portID = Integer.parseInt(node.attributeValue("id"));

            portMap.put(location, portID);
            portMap.put(new Point(location.x + 1, location.y), portID);
            portMap.put(new Point(location.x + 1, location.y + 1), portID);
            portMap.put(new Point(location.x, location.y + 1), portID);
        }

        return portMap;
    }

    public Map<Point, Place> getBuildings(List<Element> nodes) {

        Map<Point, Place> buildings = new HashMap<>();

        for (Element node : nodes) {
            Point loc = Utils.readLocation(node);
            Place building = createBuilding(node);
            buildings.put(loc, building);
            buildings.put(new Point(loc.x + 1, loc.y), building);
        }

        return buildings;
    }

    private Place createBuilding(Element node) {

        Place building = null;

        switch (Building_Type.valueOf(node.attributeValue("type"))) {
            case MARKET:
                building = new Market();
                break;
            case HARBOUR:
                building = new Harbour();
                break;
            case SHIPYARD:
                building = new Shipyard();
                break;
            case LODGE:
                building = new Lodge();
                break;
            case CAFE:
                building = new Cafe();
                break;
            case BANK:
                building = new Bank();
                break;
            case PALACE:
                building = new Palace();
                break;
            case FORTUNE_TELLER:
                building = new FortuneTeller();
                break;
            case CHURCH:
                building = new Church();
                break;
            case SHOP:
                building = new Shop();
                break;
            case GUILD:
                building = new Guild();
                break;
            case MANSION:
                building = null;
                break;
        }

        return building;
    }

}
