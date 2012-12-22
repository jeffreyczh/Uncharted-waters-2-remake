package utils;

import game.Point;
import game.World;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.SlickException;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class Utils {

    private static Utils ourInstance = new Utils();

    public static Utils getInstance() {
        return ourInstance;
    }

    private Font font;

    public static Font getFont() {
        return getInstance().font;
    }

    private Utils() {
        try {
            font = new AngelCodeFont("asset/font/eng.fnt", "asset/font/eng_0.png");
        } catch (SlickException e) {
            System.err.println("Font loading error");
            System.exit(1);
        }
    }

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

    public static Point readLocation(Element e) {
        return new Point(Integer.parseInt(e.attributeValue("x")), Integer.parseInt(e.attributeValue("y")));
    }

    public static void drawStringCenter(int x, int y, int width, String string) {
        drawStringCenter(x, y, width, string, Color.black);
    }

    public static void drawStringCenter(int x, int y, int width, String string, Color color) {
        Font font = getFont();
        font.drawString(x + (width - font.getWidth(string)) / 2, y, string, color);
    }

    public static void drawStringRightAligned(int x, int y, String string) {
        Font font = getFont();
        font.drawString(x - font.getWidth(string), y, string, Color.black);
    }
}
