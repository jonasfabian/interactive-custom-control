package custom_control.stop_watch.model;

import custom_control.stop_watch.utils.TimeFormatter;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.util.Duration;

public class StopWatchModel {

    private Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> setTimeMilliSecondsEnd(System.currentTimeMillis())));
    private final LongProperty timeMilliSecondsStart = new SimpleLongProperty();
    private final LongProperty timeMilliSecondsEnd = new SimpleLongProperty();
    private final NumberBinding timeValue = timeMilliSecondsEnd.subtract(timeMilliSecondsStart);

    private StopWatchState stopWatchState = StopWatchState.IDLE;

    private final StringBinding timeDisplay = Bindings.createStringBinding(() -> {
        long elapsedTime = getTimeMilliSecondsEnd() - getTimeMilliSecondsStart();
        return TimeFormatter.formatTime(elapsedTime);
    }, timeMilliSecondsStart, timeMilliSecondsEnd);

    public StopWatchModel() {
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public long getTimeMilliSecondsStart() {
        return timeMilliSecondsStart.get();
    }

    public LongProperty timeMilliSecondsStartProperty() {
        return timeMilliSecondsStart;
    }

    public void setTimeMilliSecondsStart(long timeMilliSecondsStart) {
        this.timeMilliSecondsStart.set(timeMilliSecondsStart);
    }

    public long getTimeMilliSecondsEnd() {
        return timeMilliSecondsEnd.get();
    }

    public LongProperty timeMilliSecondsEndProperty() {
        return timeMilliSecondsEnd;
    }

    public void setTimeMilliSecondsEnd(long timeMilliSecondsEnd) {
        this.timeMilliSecondsEnd.set(timeMilliSecondsEnd);
    }

    public Number getTimeValue() {
        return timeValue.getValue();
    }

    public NumberBinding timeValueProperty() {
        return timeValue;
    }

    public String getTimeDisplay() {
        return timeDisplay.get();
    }

    public StringBinding timeDisplayProperty() {
        return timeDisplay;
    }


    public boolean isRunning() {
        return stopWatchState == StopWatchState.RUNNING;
    }

    public boolean isStopped() {
        return stopWatchState == StopWatchState.STOPPED;
    }

    public void setState(StopWatchState stopWatchState) {
        this.stopWatchState = stopWatchState;
    }

    public enum StopWatchState {
        RUNNING,
        STOPPED,
        IDLE;
    }
}
