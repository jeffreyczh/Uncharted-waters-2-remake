package utils;

import game.Point;
import game.World;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class Utils {

    public static File getFile(String location) {

        String decodedPath = null;
        try {
            String path = World.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            path = path.substring(0, path.lastIndexOf('/'));
            decodedPath = URLDecoder.decode(path, "UTF-8");
            decodedPath = decodedPath + location;
            decodedPath = location;
        } catch (UnsupportedEncodingException e) {
            System.out.println("IO ERROR");
            System.exit(1);
        }

        return new File(decodedPath);
    }

    public static Document readXML(String fileLoc) {

        SAXReader reader = new SAXReader();
        Document document = null;

        try {
            document = reader.read(getFile(fileLoc));
        } catch (DocumentException e) {
            System.err.println("XML reading error: fileLoc is not a valid xml file");
            System.exit(1);
        }

        return document;
    }

    public static Point readLocation(Node node) {
        String[] portLoc = node.selectSingleNode("//location").getText().split(",");
        return new Point(Integer.parseInt(portLoc[0]), Integer.parseInt(portLoc[1]));
    }
}
