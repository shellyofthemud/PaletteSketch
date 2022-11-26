package org.csi3370.palettesketch.tools;


import javafx.scene.input.MouseEvent;
import org.csi3370.palettesketch.ColorMap;

import java.awt.*;

public class PenTool extends CanvasTool {

    private int strokeWidth = 5;

    private double lastX;

    private double lastY;

    PenTool() {
        super();
    }


    // anything that interacts with the canvas graphics context must be a CanvasTool
    @Override
    public void onMouseClick(MouseEvent e) {
        lastX = e.getX();
        lastY = e.getY();
    }

    // Get graphics instance from the parent
    @Override
    public void onMouseDrag(MouseEvent e) {
        Graphics2D g = getGraphics();
        g.setStroke(new BasicStroke(getStrokeWidth()));
        g.setColor(ColorMap.getSelectedColor());
        if (lastX == 0) {
            lastX = e.getX();
        }
        if (lastY == 0) {
            lastY = e.getY();
        }
        g.drawLine((int) lastX, (int) lastY, (int) e.getX(), (int) e.getY());
        lastX = e.getX();
        lastY = e.getY();
    }


    @Override
    public void onMouseDragRelease(MouseEvent e) {

    }
}
