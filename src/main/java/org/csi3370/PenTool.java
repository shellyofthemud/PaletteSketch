package org.csi3370;

import processing.core.PGraphics;

import java.awt.*;

public class PenTool {

    private static PenTool _instance;

    private int pointSize = 5;
    private Color penColor;
    private PGraphics canvasRef;

    PenTool(PGraphics canvas) {
        _instance = this;
        canvasRef = canvas;
    }

    public void handleMouseEvent(MouseEvent e) {
        if (e.type == MouseEvent.EventType.CLICK) {
            if (Main.selectedColor != penColor) {
                setPenColor(Main.selectedColor);
            }
        }
        if (e.type == MouseEvent.EventType.DRAG) {
            canvasRef.beginDraw();
            canvasRef.ellipse(e.mouseX, e.mouseY, pointSize, pointSize);
            canvasRef.endDraw();
        }
    }

    public void setPenSize(int size) {
        pointSize = size;
    }

    public void setPenColor(Color c) {
        penColor = c;
        canvasRef.fill(c.getRed(), c.getGreen(), c.getBlue());
        canvasRef.stroke(c.getRed(), c.getGreen(), c.getBlue());

    }
}
