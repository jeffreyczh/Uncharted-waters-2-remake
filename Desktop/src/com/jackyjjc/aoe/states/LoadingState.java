package com.jackyjjc.aoe.states;

import com.badlogic.gdx.Gdx;
import com.jackyjjc.aoe.DesktopMasterScreen;
import com.jackyjjc.aoe.GameGraphics;
import com.jackyjjc.aoe.game.GameInput;
import com.jackyjjc.aoe.view.ResourceManager;

import java.text.DecimalFormat;

import static com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class LoadingState extends AbstractState {

    private DecimalFormat progressFormat;

    private static final String LOADING_TEXT = "Loading";
    private String loadingStr;
    private String percentageStr;
    private int numDots;

    public LoadingState() {
        this.progressFormat = new DecimalFormat("#.##");
    }

    @Override
    public void init() {

        Gdx.app.log(getClass().getCanonicalName(), "Enter State");

        this.numDots = 1;
        this.loadingStr = LOADING_TEXT;
        this.percentageStr = "0%";
    }

    @Override
    public void render(GameGraphics g, float time) {
        
        g.spriteBatch.begin();

        /*Draw 'Loading...'*/
        TextBounds textBounds = g.getTextBound(loadingStr);
        float textY = g.mid(Gdx.graphics.getHeight(), textBounds.height);
        g.drawText(loadingStr,
                   g.mid(Gdx.graphics.getWidth(), textBounds.width),
                   textY);

        /*underneath 'Loading...' 10px draw the percentage string*/
        TextBounds pb = g.getTextBound(percentageStr);
        g.drawText(percentageStr,
                   g.mid(Gdx.graphics.getWidth(), pb.width),
                   textY + 20);

        g.spriteBatch.end();
    }

    @Override
    public void update(GameInput input) {

        /*Load all the sprite and external resources first*/
        boolean finished = ResourceManager.get().update();

        if(finished) {
            Gdx.app.log(getClass().getCanonicalName(), "Loading resources Finish");
            aoe.init();
            enterState(StateManager.StateType.MAIN_MENU);
        }

        percentageStr = progressFormat.format(ResourceManager.get().getProgress() * 100) + "%";
        this.loadingStr = LOADING_TEXT;
        for (int i = 0; i < numDots; i++) {
            loadingStr += ".";
        }

        this.numDots = (numDots == 3) ? 1 : (numDots + 1);
    }
}
