package view.gamePanels;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public abstract class View implements IView {

    protected int x;
    protected int y;

    private IView parent;
    private IView focusedView;

    private List<IView> children;
    private List<ReleaseFocusListener> releaseFocusListeners;
    private List<ViewDestroyListener> viewDestroyListeners;

    public View(int x, int y) {
        this.x = x;
        this.y = y;
        focusedView = null;
        children = new ArrayList<>();
        releaseFocusListeners = new ArrayList<>();
        viewDestroyListeners = new ArrayList<>();
    }

    public void render(Graphics graphics) {
        renderThisView(graphics);
        renderChildren(graphics);
    }

    public void update(Input input, int mouseX, int mouseY, boolean mouseClicked) {

        if (focusedView != null) {
            focusedView.update(input, mouseX, mouseY, mouseClicked);
        } else {
            updateThisView(input, mouseX, mouseY, mouseClicked);
            IView[] updateList = children.toArray(new IView[children.size()]);
            for (IView view : updateList) {
                view.update(input, mouseX, mouseY, mouseClicked);
            }
        }
    }

    public void addChild(IView view) {
        if (view != null) {
            children.add(view);
            view.setParent(this);
        }
    }

    public void removeChild(IView view) {
        if (view != null && children.contains(view)) {
            view.removeChildren();
            view.releaseFocus();
            view.setParent(null);
            children.remove(view);
        }
    }

    public void removeChildren() {
        IView[] removeList = children.toArray(new IView[children.size()]);
        for (IView view : removeList) {
            removeChild(view);
        }
    }

    public List<IView> getChildren() {
        return children;
    }

    public void addReleaseFocusListener(ReleaseFocusListener l) {
        if(l != null) {
            releaseFocusListeners.add(l);
        }
    }

    public void giveFocus(IView view) {
        if (view != null) {
            focusedView = view;
        }
    }

    public void requestFocus() {
        if (parent != null) {
            parent.requestFocus();
            parent.giveFocus(this);
        }
    }

    public void receiveFocus(IView view) {
        if (focusedView != null && focusedView == view) {
            focusedView = null;
        }
    }

    public void releaseFocus() {
        if (parent != null) {
            parent.receiveFocus(this);
            notifyReleaseFocus();
        }
    }

    public void addViewDestroyListener(ViewDestroyListener l) {
        if(l != null) {
            viewDestroyListeners.add(l);
        }
    }

    public void destroyView() {
        if (parent != null) {
            parent.removeChild(this);
            notifyViewDestroy();
        }
    }

    public void setParent(IView view) {
        this.parent = view;
    }

    protected void renderChildren(Graphics graphics) {

        for (IView child : children) {
            if (focusedView != child) {
                child.render(graphics);
            }
        }

        if (focusedView != null) {
            focusedView.render(graphics);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract void renderThisView(Graphics graphics);

    public abstract void updateThisView(Input input, int mouseX, int mouseY, boolean mouseClicked);

    private void notifyReleaseFocus() {
        for(ReleaseFocusListener l : releaseFocusListeners) {
            l.notifyReleaseFocus();
        }
    }

    private void notifyViewDestroy() {
        for(ViewDestroyListener l : viewDestroyListeners) {
            l.notifyViewDestroy();
        }
    }

}
