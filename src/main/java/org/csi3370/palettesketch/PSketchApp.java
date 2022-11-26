package org.csi3370.palettesketch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PSketchApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(PSketchApp.class.getResource("layout.fxml"));
        Scene scene = new Scene((Parent) fxmlLoader.load());
        stage.setScene(scene);

        // The palette canvas has to be initialized _after_ the stage is visible
        stage.setOnShowing(e-> ((PSketchController) fxmlLoader.getController()).initPCanvas());
        stage.show();
    }
}
