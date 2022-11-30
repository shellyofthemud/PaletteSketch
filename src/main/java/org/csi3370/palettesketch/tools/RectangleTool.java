package org.csi3370.palettesketch.tools;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.csi3370.palettesketch.ColorMap;

public class RectangleTool extends CanvasTool {

    private int x1;
    private int y1;
    private int x2;
    private int y2;

    Rectangle previewBox;

    RectangleTool() {
        super();
        previewBox = (Rectangle) super.getCanvasNode().getScene().lookup("#rectToolPreview");
    }

    @Override
    public void onMouseDrag(MouseEvent e) {
        // TODO show a live-updating outline
        x2 = (int) e.getX();
        y2 = (int) e.getY();
        previewBox.setWidth(x2-x1);
        previewBox.setHeight(y2-y1);
    }

    @Override
    public void onMouseClick(MouseEvent e) {
        x1 = (int) e.getX();
        y1 = (int) e.getY();

        previewBox.setFill(ColorMap.AWTColorToFXColor(ColorMap.getSelectedMappedColor()));
        previewBox.setX(x1);
        previewBox.setY(y1);
        previewBox.setWidth(0);
        previewBox.setHeight(0);
        previewBox.setVisible(true);
    }

    @Override
    public void onMouseDragRelease(MouseEvent e) {
        x2 = (int) e.getX();
        y2 = (int) e.getY();
        int width = x2-x1;
        int height = y2-y1;

        getGraphics().setColor(ColorMap.getSelectedColor());
        getGraphics().fillRect(x1, y1, width, height);
        previewBox.setVisible(false);

    }
}
