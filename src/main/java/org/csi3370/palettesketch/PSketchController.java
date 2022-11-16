package org.csi3370.palettesketch;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.csi3370.palettesketch.tools.CanvasTool;
import org.csi3370.palettesketch.ui.PaletteListDisplay;

public class PSketchController {

    @FXML
    private HBox paletteListDisplayContainer;

    @FXML
    private ImageView paletteCanvasView;

    @FXML
    private Slider sliderRed;

    @FXML
    private TextField textValRed;

    @FXML
    private Slider sliderGreen;

    @FXML
    private TextField textValGreen;

    @FXML
    private Slider sliderBlue;

    @FXML
    private TextField textValBlue;

    private PaletteCanvasController pCanvas;

    private PaletteListDisplay pList;

    public void initPCanvas() {
        pCanvas = new PaletteCanvasController(paletteCanvasView);
        CanvasTool.setGraphicsReference(pCanvas.getGraphics());
        pList = new PaletteListDisplay(paletteListDisplayContainer);

       sliderBlue.valueProperty().addListener(new ChangeListener<Number>() {
           @Override
           public void changed(ObservableValue<? extends Number> observable, Number oldVal, Number newVal) {
               // observable.
           }
       });
    }

    @FXML
    private void onMouseClickedRoot(MouseEvent e) {
    }

    @FXML
    private void onMouseClickedCanvas(MouseEvent e) {
        CanvasTool.getActiveTool().onMouseClick(e);
    }

    @FXML
    private void onMouseDraggedCanvas(MouseEvent e) {
        CanvasTool.getActiveTool().onMouseDrag(e);
        pCanvas.render();
    }

    @FXML
    private void onMouseDragReleaseCanvas(MouseEvent e) {
        CanvasTool.getActiveTool().onMouseDragRelease(e);
    }


}