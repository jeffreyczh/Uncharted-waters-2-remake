package game;

import org.newdawn.slick.Input;
import view.SailingPanel;

/**
 * @author Junjie CHEN(jacky.jjchen@gmail.com)
 */
public class Calendar {
    private static final int HOURS_PER_DAY = 24;
    private static final int MONTHS_PER_YEAR = 12;
    private static final int DAYS_IN_FEB = 28;

    private int year;
    private int month;
    private int day;
    private int time;

    private int count;

    public Calendar(Rule rule) {
        year = rule.START_YEAR;
        month = rule.START_MONTH;
        day = rule.START_DAY;
        time = 8;
    }

    public void tick(SailingPanel panel, Input input) {

        count++;

        if (count == 3) {

            int remainder;

            time++;
            remainder = time / HOURS_PER_DAY;
            time = time % HOURS_PER_DAY;

            day += remainder;
            remainder = day / (daysInMonth(month) + 1);
            day = day % (daysInMonth(month) + 1);
            day = (day == 0) ? 1 : day;

            remainder = monthChange(remainder);
            yearChange(remainder);

            count = 0;

            panel.update(input);
        }
    }

    @Override
    public String toString() {
        String date = "";
        date += year + ".";
        date += month + ".";
        date += day + ".";
        date += time;

        return date;
    }

    private int daysInMonth(int month) {

        int daysInMonth = 30;

        if (month == 2) {
            daysInMonth = DAYS_IN_FEB;
            if(isLeapYear()) daysInMonth += 1;
        } else if (isThirtyDays(month)) {
            daysInMonth = 30;
        } else if (!isThirtyDays(month)) {
            daysInMonth = 31;
        }

        return daysInMonth;
    }

    private boolean isThirtyDays(int month) {

        boolean thirtyDays = false;

        if ((month < 7 && month % 2 == 0) || (month > 8 && month % 2 != 0)) {
            thirtyDays = true;
        }

        return thirtyDays;
    }

    public int monthChange(int remainder) {

        month += remainder;
        month = month % (MONTHS_PER_YEAR + 1);
        month = (month == 0) ? 1 : month;

        remainder = month / (MONTHS_PER_YEAR + 1);

        return remainder;
    }

    public void yearChange(int remainder) {

        if (remainder > 0) {
            year += remainder;
        }
    }

    public boolean isLeapYear() {

        boolean isLeapYear = false;

        if (year % 400 == 0) {
            isLeapYear = true;
        } else if (year % 100 == 0) {
            isLeapYear = false;
        } else if (year % 4 == 0) {
            isLeapYear = true;
        } else {
            isLeapYear = false;
        }

        return isLeapYear;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getTime() {
        return time;
    }
}
