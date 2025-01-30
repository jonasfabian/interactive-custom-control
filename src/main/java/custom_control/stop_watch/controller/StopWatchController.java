package custom_control.stop_watch.controller;

import custom_control.stop_watch.model.StopWatchModel;
import javafx.animation.Animation;

public class StopWatchController {

    private final StopWatchModel stopWatchModel;

    public StopWatchController(StopWatchModel stopWatchModel) {
        this.stopWatchModel = stopWatchModel;
    }

    public void startStopWatch() {
        if (!stopWatchModel.isRunning()) {
            Long currentMillis = System.currentTimeMillis();
            if (stopWatchModel.isStopped()) {
                Long pausedMillis = currentMillis - stopWatchModel.getTimeMilliSecondsEnd();
                stopWatchModel.setTimeMilliSecondsStart(stopWatchModel.getTimeMilliSecondsStart() + pausedMillis);
            } else {
                stopWatchModel.setTimeMilliSecondsStart(currentMillis);
            }
            stopWatchModel.setTimeMilliSecondsEnd(currentMillis);
            stopWatchModel.getTimeline().setCycleCount(Animation.INDEFINITE);
            stopWatchModel.getTimeline().play();
            stopWatchModel.setState(StopWatchModel.StopWatchState.RUNNING);
        }
    }

    public void stopStopWatch() {
        if (stopWatchModel.isRunning()) {
            stopWatchModel.setTimeMilliSecondsEnd(System.currentTimeMillis());
            stopWatchModel.getTimeline().stop();
            stopWatchModel.setState(StopWatchModel.StopWatchState.STOPPED);
        }
    }

    public void resetStopWatch() {
        stopWatchModel.setTimeMilliSecondsStart(System.currentTimeMillis());
        stopWatchModel.setTimeMilliSecondsEnd(stopWatchModel.getTimeMilliSecondsStart());
    }
}
