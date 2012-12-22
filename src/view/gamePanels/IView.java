package view.gamePanels;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

import java.util.List;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public interface IView {

    int getX();

    int getY();

    void render(Graphics graphics);

    void update(Input input, int mouseX, int mouseY, boolean mouseClicked);

    public void addReleaseFocusListener(ReleaseFocusListener l);

    void requestFocus();

    void releaseFocus();

    public void addViewDestroyListener(ViewDestroyListener l);

    void destroyView();

    void setParent(IView view);

    void addChild(IView view);

    void removeChild(IView view);

    void removeChildren();

    List<IView> getChildren();

    void giveFocus(IView view);

    void receiveFocus(IView view);
}
