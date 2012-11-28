package game;

import game.buildings.*;
import org.dom4j.Document;
import org.dom4j.Node;
import utils.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class PlaceFactory {

    public enum Building_Type {
        MARKET, DOCK, DOCKYARD, INN, BAR,
        BANK, PALACE, ASTROLOGIST_HUT, CHURCH,
        SHOP, OFFICE
    }

    public PlaceFactory() {

    }

    public Map<Point, Integer> getPorts(String fileLoc) {
        Document ports = Utils.readXML(fileLoc);

        Map<Point, Integer> portMap = new HashMap<>();

        assert (ports != null);
        List<Node> portList = ports.selectNodes("/ports/port");

        for (Node node : portList) {
            Point location = Utils.readLocation(node);
            int portID = Integer.parseInt(node.selectSingleNode("//map_id").getText());

            portMap.put(location, portID);
            portMap.put(new Point(location.x + 1, location.y), portID);
            portMap.put(new Point(location.x + 1, location.y + 1), portID);
            portMap.put(new Point(location.x, location.y + 1), portID);
        }

        return portMap;
    }

    public Map<Point, Place> getBuildings(List<Node> nodes) {

        Map<Point, Place> buildings = new HashMap<>();

        for (Node node : nodes) {
            Point loc = Utils.readLocation(node);
            buildings.put(loc, createBuilding(node));
            buildings.put(new Point(loc.x + 1, loc.y), createBuilding(node));
        }

        return buildings;
    }

    private Place createBuilding(Node node) {

        Place building = null;

        switch (Building_Type.valueOf(node.valueOf("//type"))) {
            case MARKET:
                building = new Market();
                break;
            case DOCK:
                building = new Dock();
                break;
            case DOCKYARD:
                building = new Dockyard();
                break;
            case INN:
                building = new Inn();
                break;
            case BAR:
                building = new Bar();
                break;
            case BANK:
                building = new Bank();
                break;
            case PALACE:
                building = new Palace();
                break;
            case ASTROLOGIST_HUT:
                building = new AstrologistHut();
                break;
            case CHURCH:
                building = new Church();
                break;
            case SHOP:
                building = new Shop();
                break;
            case OFFICE:
                building = new Office();
                break;
        }

        return building;
    }

}
