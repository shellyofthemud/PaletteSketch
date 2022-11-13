package org.csi3370.palettesketch.tools;


import javafx.scene.input.MouseEvent;

public class PenTool extends CanvasTool {

    private int strokeWidth = 5;

    private double lastX;

    private double lastY;

    PenTool() {
        super();
    }


    @Override
    public void onMouseClick(MouseEvent e) {
        lastX = e.getX();
        lastY = e.getY();
    }

    @Override
    public void onMouseDrag(MouseEvent e) {
        // super.getGraphics().setFill(ColorMap.getSelectedIndexColor());
        // super.getGraphics().setStroke(ColorMap.getSelectedIndexColor());
        super.getGraphics().setLineWidth(strokeWidth);
        super.getGraphics().setImageSmoothing(false);
        if (lastX == 0) {
            lastX = e.getX();
        }
        if (lastY == 0) {
            lastY = e.getY();
        }
        super.getGraphics().strokeLine(lastX, lastY, e.getX(), e.getY());
        lastX = e.getX();
        lastY = e.getY();
    }


    @Override
    public void onMouseDragRelease(MouseEvent e) {

    }
}
