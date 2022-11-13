package org.csi3370.palettesketch.tools;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public abstract class CanvasTool {

    private static CanvasTool activeTool;

    static GraphicsContext graphicsReference;

    static {
        activeTool = new PenTool();
    }

    public static CanvasTool getActiveTool() {
        return activeTool;
    }

    public static void setGraphicsReference(GraphicsContext g) {
        graphicsReference = g;
    }

    GraphicsContext getGraphics() {
        return graphicsReference;
    }

    protected CanvasTool() {
        activeTool = this;
    }

    public abstract void onMouseDrag(MouseEvent e);

    public abstract void onMouseClick(MouseEvent e);

    public abstract void onMouseDragRelease(MouseEvent e);

}
