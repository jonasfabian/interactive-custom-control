package custom_control.stop_watch.view;

import custom_control.stop_watch.controller.StopWatchController;
import custom_control.stop_watch.model.StopWatchModel;
import javafx.geometry.Insets;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


/**
 * Die StopWatch ist ein Custom User Interface Element.
 * Sie kann mit START die Zeit starten,
 * mit STOP die Zeit anhalten
 * und mit RESET die ZEIT zurücksetzen.
 * Es kann sowohl im Pane auf der rechten Seite gesteuert werden, sowie auf der linken Seite über die Stoppuhrknöpfe.
 */

public class StopWatchView extends Region {
    private static final double ARTBOARD_WIDTH = 350;
    private static final double ARTBOARD_HEIGHT = 372;

    private static final double ASPECT_RATIO = ARTBOARD_WIDTH / ARTBOARD_HEIGHT;

    private static final double MINIMUM_WIDTH = 25;
    private static final double MINIMUM_HEIGHT = MINIMUM_WIDTH / ASPECT_RATIO;

    private static final double MAXIMUM_WIDTH = 800;

    private Text timeDisplay;

    private Circle outerCircle;
    private Circle innerCircleBorder;
    private Circle innerCircle;

    private Rectangle startButtonStand;
    private Rectangle resetButtonStand;
    private Rectangle stopButtonStand;

    private Rectangle startButton;
    private Rectangle stopButton;
    private Rectangle resetButton;

    private Rectangle underDisplay;
    private Rectangle display;

    private Text startText;
    private Text resetText;
    private Text stopText;

    private Text modelTypeText;
    private Text modelNameText;

    private Text customControlText;

    private StopWatchModel stopWatchModel;
    private StopWatchController stopWatchController;

    private Pane drawingPane;

    public StopWatchView(StopWatchModel model, StopWatchController controller) {
        this.stopWatchModel = model;
        this.stopWatchController = controller;
        initializeSelf();
        initializeParts();
        initializeDrawingPane();
        layoutParts();
        setupEventHandlers();
        setupValueChangeListeners();
    }

    private void setupValueChangeListeners() {
        stopWatchModel.timeDisplayProperty().addListener((observable, oldValue, newValue) -> timeDisplay.textProperty().setValue(newValue));
    }

    private void initializeSelf() {
        loadFonts(
                "/fonts/Lato/Lato-Lig.ttf",
                "/fonts/digital-7 (mono).ttf"
        );
        addStylesheetFiles("/styles/style.css");
        getStyleClass().add("simple-control");
    }

    private void initializeParts() {
        outerCircle = new Circle(175, 195, 175);
        outerCircle.getStyleClass().add("outer-circle");

        innerCircleBorder = new Circle(175, 195, 160);
        innerCircleBorder.getStyleClass().add("inner-circle-border");

        innerCircle = new Circle(175, 195, 150);
        innerCircle.getStyleClass().add("inner-circle");

        startButtonStand = new Rectangle(20, 73, 46, 10);
        startButtonStand.getStyleClass().add("start-button-stand");

        resetButtonStand = new Rectangle(152, 14, 46, 10);
        resetButtonStand.getStyleClass().add("reset-button-stand");

        stopButtonStand = new Rectangle(285, 73, 46, 10);
        stopButtonStand.getStyleClass().add("stop-button-stand");

        LinearGradient linearGradient = new LinearGradient(
                67, 140, 287, 240,
                false,
                javafx.scene.paint.CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#6B8068", 1)),
                new Stop(1, Color.web("#222E20", 0.5)),
                new Stop(2, Color.web("#6B8068", 1))
        );

        underDisplay = new Rectangle(67, 140, 220, 100);
        underDisplay.setFill(Color.web("#7D9379"));
        underDisplay.setArcWidth(10.0);
        underDisplay.setArcHeight(10.0);

        display = new Rectangle(67, 140, 220, 100);
        display.setFill(linearGradient);
        display.setArcWidth(10.0);
        display.setArcHeight(10.0);

        startButton = new Rectangle(2, 62, 66, 16);
        startButton.getStyleClass().addAll("background-start-button", "start-button");

        resetButton = new Rectangle(142, 0, 66, 16);
        resetButton.getStyleClass().addAll("background-reset-button", "reset-button");

        stopButton = new Rectangle(284, 62, 66, 16);
        stopButton.getStyleClass().addAll("background-stop-button", "stop-button");

        timeDisplay = new Text();

        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.rgb(50, 50, 50, 0.5));
        dropShadow.setRadius(3.0);

        timeDisplay.setEffect(dropShadow);
        timeDisplay.setText("00:00:00");
        timeDisplay.setFont(Font.font("Digital-7 Mono", 54));
        timeDisplay.getStyleClass().add("time-display");

        double centerX = display.getX() + (display.getWidth() / 2);
        double centerY = display.getY() + (display.getHeight() / 2);
        double textWidth = timeDisplay.getBoundsInLocal().getWidth();
        double textHeight = timeDisplay.getBoundsInLocal().getHeight();

        timeDisplay.setX(centerX - textWidth / 2);
        timeDisplay.setY(centerY + textHeight / 4);

        startText = new Text("START");
        startText.getStyleClass().add("start-text");
        startText.setRotate(-45);
        startText.setX(46);
        startText.setY(116);

        resetText = new Text("RESET");
        resetText.getStyleClass().add("reset-text");
        resetText.setX(150);
        resetText.setY(70);

        stopText = new Text("STOP");
        stopText.getStyleClass().add("stop-text");
        stopText.setRotate(45);
        stopText.setX(250);
        stopText.setY(116);

        modelTypeText = new Text("Stop Watch");
        modelTypeText.getStyleClass().add("model-type-text");
        modelTypeText.setX(130);
        modelTypeText.setY(290);

        modelNameText = new Text("JavaFX Version");
        modelNameText.getStyleClass().add("model-name-text");
        modelNameText.setX(140);
        modelNameText.setY(310);

        customControlText = new Text("JavaFX");
        customControlText.getStyleClass().add("custom-control-text");
        customControlText.setX(146);
        customControlText.setY(124);
    }

    private void initializeDrawingPane() {
        drawingPane = new Pane();
        drawingPane.getStyleClass().add("drawing-pane");
        drawingPane.setMaxSize(ARTBOARD_WIDTH, ARTBOARD_HEIGHT);
        drawingPane.setMinSize(ARTBOARD_WIDTH, ARTBOARD_HEIGHT);
        drawingPane.setPrefSize(ARTBOARD_WIDTH, ARTBOARD_HEIGHT);
    }

    private void layoutParts() {
        drawingPane.getChildren().addAll(
                outerCircle,
                innerCircleBorder,
                innerCircle,
                startButtonStand,
                resetButtonStand,
                stopButtonStand,
                underDisplay,
                display,
                startButton,
                stopButton,
                resetButton,
                timeDisplay,
                startText,
                resetText,
                stopText,
                modelTypeText,
                modelNameText,
                customControlText
        );
        getChildren().add(drawingPane);
    }

    private void setupEventHandlers() {
        startButton.setOnMouseClicked(event -> stopWatchController.startStopWatch());
        stopButton.setOnMouseClicked(event -> stopWatchController.stopStopWatch());
        resetButton.setOnMouseClicked(event -> stopWatchController.resetStopWatch());
    }


    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        resize();
    }

    private void resize() {
        Insets padding = getPadding();
        double availableWidth = getWidth() - padding.getLeft() - padding.getRight();
        double availableHeight = getHeight() - padding.getTop() - padding.getBottom();

        double width = Math.max(Math.min(Math.min(availableWidth, availableHeight * ASPECT_RATIO), MAXIMUM_WIDTH), MINIMUM_WIDTH);

        double scalingFactor = width / ARTBOARD_WIDTH;

        if (availableWidth > 0 && availableHeight > 0) {
            drawingPane.relocate((getWidth() - ARTBOARD_WIDTH) * 0.5, (getHeight() - ARTBOARD_HEIGHT) * 0.5);
            drawingPane.setScaleX(scalingFactor);
            drawingPane.setScaleY(scalingFactor);
        }
    }

    private void loadFonts(String... font) {
        for (String f : font) {
            Font.loadFont(getClass().getResourceAsStream(f), 0);
        }
    }

    private void addStylesheetFiles(String... stylesheetFile) {
        for (String file : stylesheetFile) {
            String stylesheet = getClass().getResource(file).toExternalForm();
            getStylesheets().add(stylesheet);
        }
    }
}
