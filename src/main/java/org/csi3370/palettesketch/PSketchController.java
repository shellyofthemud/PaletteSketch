package org.csi3370.palettesketch;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PSketchController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}