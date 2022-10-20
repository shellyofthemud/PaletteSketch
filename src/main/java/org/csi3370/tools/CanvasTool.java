package org.csi3370.tools;

import org.csi3370.Application;
import org.csi3370.ColorMap;

import java.awt.*;
import java.awt.event.MouseAdapter;

public abstract class CanvasTool extends MouseAdapter {

    private static CanvasTool activeTool;

    private Color toolColor;

    private int strokeSize = 5;
    private Graphics2D canvasRef;

    public static CanvasTool getTool() {
        if (activeTool == null) {
            activeTool = getPenTool();
        }
        return activeTool;
    }

    void init() {
        canvasRef = (Graphics2D) Application.getInstanceCanvas().getCanvasGraphics();
        setToolColor(ColorMap.getSelectedIndexColor());
        Application.getInstanceCanvas().addMouseListener(this);
        Application.getInstanceCanvas().addMouseMotionListener(this);
    }

    public static CanvasTool getPenTool() {
        activeTool = new PenTool();
        return activeTool;
    }

    Color getToolColor() {
        return toolColor;
    }

    Graphics2D getCanvasRef() {
        return canvasRef;
    }

    int getStrokeSize() {
        return strokeSize;
    }

    void setStrokeSize(int strokeSize) {
        this.strokeSize = strokeSize;
        canvasRef.setStroke(new BasicStroke(strokeSize));
    }

    public void setToolColor(Color c) {
        this.toolColor = c;
        canvasRef.setColor(c);
    }

    static void setActiveTool(CanvasTool t) {
        if (activeTool != null) {
            Application.getInstanceCanvas().removeMouseMotionListener(activeTool);
            Application.getInstanceCanvas().removeMouseListener(activeTool);
        }
        activeTool = t;
        Application.getInstanceCanvas().addMouseMotionListener(activeTool);
        Application.getInstanceCanvas().addMouseListener(activeTool);
    }
}
