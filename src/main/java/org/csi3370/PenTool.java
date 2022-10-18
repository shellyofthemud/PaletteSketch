package org.csi3370;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PenTool extends MouseAdapter {

    private static PenTool _instance;

    private int pointSize = 5;
    private Color penColor;
    private Graphics2D canvasRef;

    private int lastX;
    private int lastY;

    private int brushSize = 5;

    PenTool(Graphics canvas) {
        _instance = this;
        canvasRef = (Graphics2D) canvas;
        setColor(ColorMap.getSelectedIndexColor());
    }

    public void mousePressed(MouseEvent e) {
        penColor = Application.selectedColor;
        lastX = e.getX();
        lastY = e.getY();
        setStroke(brushSize);
        canvasRef.setColor(penColor);
    }

    public void mouseDragged(MouseEvent e) {
        canvasRef.drawLine(lastX, lastY, e.getX(), e.getY());
        lastX = e.getX();
        lastY = e.getY();
        Application.getAppInstance().repaint();
    }

    private void setStroke(int size) {
        this.brushSize = size;
        canvasRef.setStroke(new BasicStroke(size));
    }

    public void setPenSize(int size) {
        pointSize = size;
    }

    public void setColor(Color c) {
        penColor = c;
        canvasRef.setColor(c) ;
    }

    public static PenTool getTool(Graphics canvasRef) {
        if (_instance != null) {
            return _instance;
        } else {
            return new PenTool(canvasRef);
        }
    }
}
