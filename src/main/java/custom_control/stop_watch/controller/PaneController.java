package custom_control.stop_watch.controller;

import custom_control.stop_watch.model.StopWatchModel;
import custom_control.stop_watch.model.PaneModel;
import custom_control.stop_watch.view.PaneView;

public class PaneController {

    private final PaneModel demoPaneModel;
    private final PaneView paneView;
    private final StopWatchModel stopWatchModel;
    private final StopWatchController stopWatchController;

    public PaneController(PaneModel pm, PaneView view, StopWatchModel stopWatchModel) {
        this.demoPaneModel = pm;
        this.paneView = view;
        this.stopWatchModel = stopWatchModel;

        stopWatchController = new StopWatchController(stopWatchModel);

        setupBindings();
    }

    private void setupBindings() {
        paneView.getTimeValue().textProperty().bind(stopWatchModel.timeDisplayProperty());
        paneView.getStartButton().setOnMouseClicked(event -> stopWatchController.startStopWatch());
        paneView.getStopButton().setOnMouseClicked(event -> stopWatchController.stopStopWatch());
        paneView.getResetButton().setOnMouseClicked(event -> stopWatchController.resetStopWatch());
    }
}
