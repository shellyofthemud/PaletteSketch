package org.csi3370.palettesketch.tools;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

import java.awt.*;

public abstract class CanvasTool {

    // abstract base class for all tools that modify the canvas

    private static CanvasTool activeTool;

    static Graphics2D graphicsReference;

    static int strokeWidth;

    public static enum Tools {
        PEN,
        RECTANGLE,
        ERASER
    }

    static {
        activeTool = new PenTool();
    }

    public static CanvasTool getActiveTool() {
        return activeTool;
    }

    public static void setActiveTool(Tools t) {
        switch (t) {
            case PEN -> activeTool = new PenTool();
            case RECTANGLE -> activeTool = new RectangleTool();
            case ERASER -> activeTool = new EraserTool();
        }
    }

    public static void setGraphicsReference(Graphics2D g) {
        graphicsReference = g;
    }

    Graphics2D getGraphics() {
        return graphicsReference;
    }

    protected CanvasTool() {
        activeTool = this;
    }

    float getStrokeWidth() {
        return strokeWidth;
    }

    public abstract void onMouseDrag(MouseEvent e);

    public abstract void onMouseClick(MouseEvent e);

    public abstract void onMouseDragRelease(MouseEvent e);

}
