package custom_control.stop_watch;

import custom_control.stop_watch.controller.PaneController;
import custom_control.stop_watch.model.PaneModel;
import custom_control.stop_watch.model.StopWatchModel;
import custom_control.stop_watch.view.PaneView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class Starter extends Application {

    @Override
    public void start(Stage primaryStage) {
        StopWatchModel stopWatchModel = new StopWatchModel();
        PaneModel rootPM = new PaneModel();

        PaneView paneView = new PaneView(stopWatchModel);

        new PaneController(rootPM, paneView, stopWatchModel);

        Region rootPanel = paneView;
        Scene scene = new Scene(rootPanel);

        primaryStage.titleProperty().bind(rootPM.demoTitleProperty());
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
