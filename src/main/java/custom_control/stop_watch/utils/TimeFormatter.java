package custom_control.stop_watch.utils;

public class TimeFormatter {
    // Inspired by https://stackoverflow.com/questions/9027317/how-to-convert-milliseconds-to-hhmmss-format
    public static String formatTime(long milliseconds) {
        long totalSeconds = milliseconds / 1000;
        long minutes = totalSeconds / 60;
        long seconds = totalSeconds % 60;
        long hundredths = (milliseconds % 1000) / 10;

        return String.format("%02d:%02d:%02d", minutes, seconds, hundredths);
    }
}
