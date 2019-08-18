package assignment.home.com.ehailingapp;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class UtilityHelper {
    private static final long MINUTE = 1;
    private static final long HOUR = 60 * MINUTE;
    public static final int TIME_START = 1; //The starting range number
    public static final int TIME_END = 180; //The end of range
    public static final int REFRESH_TIME = 5; //1000 milliseconds = 1 sec
    public static final int INITIAL_DELAY = 0; // Initial delay on update taxis list

    // Format how the time is displayed depending whether the given time is in minutes or in hours.
    public static String timeFormatter(int time){
        if (time > HOUR) {
            long hours = TimeUnit.MINUTES.toHours((long) time);
            long remainMinutes = time - TimeUnit.HOURS.toMinutes(hours);

            return String.format(Locale.getDefault(),"%dh %dm", hours, remainMinutes);
        }

        return String.format(Locale.getDefault(),"%dm", time);
    }

    // Get a random time by given a range in minutes.
    public static int getRandomETA(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
}
