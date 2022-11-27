package org.csi3370.palettesketch.tools;

import javafx.scene.input.MouseEvent;

import java.awt.Color;

public class EraserTool extends CanvasTool {
    @Override
    public void onMouseDrag(MouseEvent e) {
        getGraphics().fillOval((int) e.getX(), (int) e.getY(), strokeWidth*10, strokeWidth*10);
    }

    @Override
    public void onMouseClick(MouseEvent e) {
        getGraphics().setColor(new Color(0, 0, 0, 0));
    }

    @Override
    public void onMouseDragRelease(MouseEvent e) {

    }
}
