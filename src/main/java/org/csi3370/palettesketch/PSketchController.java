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

import java.awt.*;

import static org.csi3370.palettesketch.ColorMap.Channel.*;

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

    // initialize the greyscale canvas BufferedImage
    public void initPCanvas() {
        pCanvas = new PaletteCanvasController(paletteCanvasView);
        CanvasTool.setGraphicsReference(pCanvas.getGraphics());
        pList = new PaletteListDisplay(paletteListDisplayContainer);


        // adding hooks for slider property changes
        sliderRed.valueProperty().addListener((observable, oldVal, newVal) -> {
            Color newColor = ColorMap.setColorChannel(ColorMap.getSelectedMappedColor(), RED, newVal.intValue());
            ColorMap.setSelectedColor(newColor);
        });
        sliderGreen.valueProperty().addListener((observable, oldVal, newVal) -> {
            Color newColor = ColorMap.setColorChannel(ColorMap.getSelectedMappedColor(), GREEN, newVal.intValue());
            ColorMap.setSelectedColor(newColor);
        });
        sliderBlue.valueProperty().addListener((observable, oldVal, newVal) -> {
            Color newColor = ColorMap.setColorChannel(ColorMap.getSelectedMappedColor(), BLUE, newVal.intValue());
            ColorMap.setSelectedColor(newColor);
        });
    }

    // handlers for passing mouse events to canvas tools
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