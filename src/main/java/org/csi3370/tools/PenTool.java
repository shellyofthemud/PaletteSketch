package org.csi3370.tools;

import org.csi3370.Application;

import java.awt.event.MouseEvent;

public class PenTool extends CanvasTool {

    private int lastX;
    private int lastY;

    PenTool() {
        super.init();
    }

    public void mousePressed(MouseEvent e) {
        lastX = e.getX();
        lastY = e.getY();
        super.setStrokeSize(super.getStrokeSize());
        super.setToolColor(super.getToolColor());
    }

    public void mouseDragged(MouseEvent e) {
        super.getCanvasRef().drawLine(lastX, lastY, e.getX(), e.getY());
        lastX = e.getX();
        lastY = e.getY();
        Application.getInstanceCanvas().repaint();
    }
}
