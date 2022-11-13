package org.csi3370.palettesketch;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import org.csi3370.palettesketch.tools.CanvasTool;

public class PSketchController {

    @FXML
    private StackPane paletteCanvasContainer;

    @FXML
    private Canvas paletteCanvas;

    @FXML
    private ImageView paletteCanvasView;

    private PaletteCanvasController pCanvas;

    public void initPCanvas() {
        // pCanvas = new PaletteCanvasController(paletteCanvas);
        // CanvasTool.setGraphicsReference(pCanvas.getGraphics());
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
        pCanvas.render(paletteCanvasView);
    }

    @FXML
    private void onMouseDragReleaseCanvas(MouseEvent e) {
        CanvasTool.getActiveTool().onMouseDragRelease(e);
    }

}