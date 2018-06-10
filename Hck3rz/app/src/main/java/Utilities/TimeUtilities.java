package Utilities;

import java.util.Calendar;
import java.util.Date;

public class TimeUtilities {

    //Taken from the event logging homework Joshua Navarro turned in.
    /**
     * Create the time string from info.
     * @return
     */
    public static  String createTime() {
        Date date = Calendar.getInstance().getTime();
        int hour = date.getHours();
        int minute = date.getMinutes();
        int seconds = date.getSeconds();

        String dayOrNight = "";
        if (hour == 24) hour = 0;
        if (hour > 12) {
            hour -= 12;
            dayOrNight = "PM";
        } else {
            dayOrNight = "AM";
        }

        String hourTime = "";
        if (hour < 10) hourTime = "0" + Integer.toString(hour);
        else hourTime = Integer.toString(hour);

        String minuteTime = "";
        if (minute < 10) minuteTime = "0" + Integer.toString(minute);
        else minuteTime = Integer.toString(minute);

        String secondTime = "";
        if (seconds < 10) secondTime = "0" + Integer.toString(seconds);
        else secondTime = Integer.toString(seconds);

        String timeString = hourTime + ":" + minuteTime + ":" + secondTime + " " + dayOrNight;
        return timeString;
    }
}
