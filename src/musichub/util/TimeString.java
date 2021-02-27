package musichub.util;

public class TimeString {

    /**
     * Converts a time given in seconds to the corresponding String formatted as : hh:mm:ss
     * @return the formatted string
     */
    public static String timeToString(int seconds){
        int hours = seconds / 3600;
        seconds -= hours * 3600;

        int minutes = seconds / 60;
        seconds -= minutes * 60;

        String ret = "";

        if(hours < 10){
            ret += "0";
        }
        ret += hours + ":";

        if(minutes < 10){
            ret += "0";
        }
        ret += minutes + ":";

        if(seconds < 10){
            ret += "0";
        }
        ret += seconds;

        return ret;
    }

    /**
     * Converts hours, minutes and seconds into fully seconds
     * @param hours number of hours
     * @param minutes number of minutes
     * @param seconds number of seconds
     * @return total time in seconds
     */
    public static int toSeconds(int hours, int minutes, int seconds){
        return hours*3600 + minutes*60 + seconds;
    }
}
