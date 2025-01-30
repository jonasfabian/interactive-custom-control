package custom_control.stop_watch.view;

import custom_control.stop_watch.controller.StopWatchController;
import custom_control.stop_watch.model.StopWatchModel;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PaneView extends BorderPane {

    private StopWatchView stopWatch;
    private Label timeValue;
    private Button startButton;
    private Button stopButton;
    private Button resetButton;

    public PaneView(StopWatchModel model) {
        initializeControls(model);
        layoutControls();
    }

    // Initialize all controls like buttons, labels and views
    private void initializeControls(StopWatchModel model) {
        setPadding(new Insets(10));

        StopWatchController controller = new StopWatchController(model);
        stopWatch = new StopWatchView(model, controller);

        timeValue = new Label("00:00:00");
        startButton = new Button("Start");
        stopButton = new Button("Stop");
        resetButton = new Button("Reset");
    }

    // Arrange the layout of controls in the user interface
    private void layoutControls() {
        VBox controlPane = new VBox(new Label("SimpleControl Properties"), timeValue, startButton, stopButton, resetButton);
        controlPane.setPadding(new Insets(0, 50, 0, 50));
        controlPane.setSpacing(10);

        setRight(controlPane);
        setLeft(stopWatch);
    }

    @Override
    protected void layoutChildren() {
        // Inspired by https://stackoverflow.com/questions/49732048/autoscalepane-in-javafx-with-layoutchildren
        super.layoutChildren();
        double width = getWidth();
        double height = getHeight();

        if (stopWatch != null) {
            // 50% of the with from the parent
            stopWatch.setPrefWidth(width * 0.5);
            // Full height from the parent
            stopWatch.setPrefHeight(height);
        }
    }

    public StopWatchView getStopWatch() {
        return stopWatch;
    }

    public Label getTimeValue() {
        return timeValue;
    }

    public Button getStartButton() {
        return startButton;
    }

    public Button getStopButton() {
        return stopButton;
    }

    public Button getResetButton() {
        return resetButton;
    }
}
