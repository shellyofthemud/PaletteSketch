package org.csi3370.palettesketch.tools;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

import java.awt.*;

public abstract class CanvasTool {

    private static CanvasTool activeTool;

    static Graphics2D graphicsReference;

    static {
        activeTool = new PenTool();
    }

    public static CanvasTool getActiveTool() {
        return activeTool;
    }

    public static void setGraphicsReference(Graphics2D g) {
        graphicsReference = g;
    }

    public static Graphics2D getGraphics() {
        return graphicsReference;
    }

    protected CanvasTool() {
        activeTool = this;
    }

    public abstract void onMouseDrag(MouseEvent e);

    public abstract void onMouseClick(MouseEvent e);

    public abstract void onMouseDragRelease(MouseEvent e);

}
