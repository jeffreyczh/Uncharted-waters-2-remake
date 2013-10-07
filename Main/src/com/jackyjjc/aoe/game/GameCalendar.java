package com.jackyjjc.aoe.game;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class GameCalendar {

    private static final int TICKS_PER_HOUR = 15;
    private int tick;

    private Calendar calendar;

    public GameCalendar() {
        this.calendar = new GregorianCalendar(1480, 1, 1);
        this.tick = 0;
    }

    public void update() {

        tick++;

        if(tick >= TICKS_PER_HOUR) {
            calendar.add(Calendar.HOUR, 1);
            tick -= TICKS_PER_HOUR;
        }
    }

    public String getTimeString() {
        return calendar.get(Calendar.YEAR) + " " + calendar.get(Calendar.MONTH) + " " + calendar.get(Calendar.DAY_OF_MONTH)
               + ":" + calendar.get(Calendar.HOUR);
    }
}
