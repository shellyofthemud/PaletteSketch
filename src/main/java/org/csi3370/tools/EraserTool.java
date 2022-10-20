package org.csi3370.tools;

import java.awt.event.MouseEvent;

public class EraserTool extends CanvasTool {

    @Override
    public void mousePressed(MouseEvent e) {
        super.getCanvasRef().fillOval(e.getX(), e.getY(), super.getStrokeSize(), super.getStrokeSize());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.getCanvasRef().fillOval(e.getX(), e.getY(), super.getStrokeSize(), super.getStrokeSize());
    }
}
