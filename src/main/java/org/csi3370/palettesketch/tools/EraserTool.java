package org.csi3370.palettesketch.tools;

import javafx.scene.input.MouseEvent;

import java.awt.*;

public class EraserTool extends CanvasTool {

    EraserTool() { super(); }

    @Override
    public void onMouseDrag(MouseEvent e) {
        int width = strokeWidth*5;
        Graphics2D g = getGraphics();
        g.setColor(new Color(0, 0, 0));
        g.setStroke(new BasicStroke(getStrokeWidth()));
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
        int x = (int) (e.getX() - width/2);
        int y = (int) (e.getY() - width/2);
        g.fillOval(x, y, width, width);
    }

    @Override
    public void onMouseClick(MouseEvent e) {

    }

    @Override
    public void onMouseDragRelease(MouseEvent e) {
        getGraphics().setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
    }
}
