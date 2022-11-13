package org.csi3370.palettesketch;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
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

    private PaletteCanvasController pCanvas;

    private PaletteListDisplay pList;

    public void initPCanvas() {
        pCanvas = new PaletteCanvasController(paletteCanvasView);
        CanvasTool.setGraphicsReference(pCanvas.getGraphics());
        pList = new PaletteListDisplay(paletteListDisplayContainer);

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