package com.jackyjjc.aoe.states;

import com.jackyjjc.aoe.game.AOE;

/**
 *
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class StateManager {

    /*List of states that the game can be in*/
    public enum StateType {

        LOADING(0),
        MAIN_MENU(1),
        PLAY(2);

        public int id;

        private StateType(int i) {
            this.id = i;
        }
    }

    /*Members of this class*/
    private AbstractState[] gameStates;
    private AbstractState currentState;

    public StateManager(AOE aoe) {

        /*Initialize all the states*/
        this.gameStates = new AbstractState[StateType.values().length];
        this.gameStates[StateType.LOADING.id] = new LoadingState();
        this.gameStates[StateType.MAIN_MENU.id] = new MainMenuState();
        this.gameStates[StateType.PLAY.id] = new GamePlayState();

        for (AbstractState s : this.gameStates) {
            s.setStateManager(this);
            s.setGame(aoe);
        }

        changeState(StateType.LOADING);
    }

    public void changeState(StateType to) {
        this.gameStates[to.id].init();
        this.currentState = this.gameStates[to.id];
    }

    public AbstractState getCurrentState() {
        return currentState;
    }
}
