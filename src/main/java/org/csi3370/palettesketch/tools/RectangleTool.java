package org.csi3370.palettesketch.tools;

import javafx.scene.input.MouseEvent;
import org.csi3370.palettesketch.ColorMap;

public class RectangleTool extends CanvasTool {

    private int x1;
    private int y1;
    private int x2;
    private int y2;

    @Override
    public void onMouseDrag(MouseEvent e) {
        // TODO show a live-updating outline
    }

    @Override
    public void onMouseClick(MouseEvent e) {
        x1 = (int) e.getX();
        y1 = (int) e.getY();
    }

    @Override
    public void onMouseDragRelease(MouseEvent e) {
        x2 = (int) e.getX();
        y2 = (int) e.getY();
        int width = Math.abs(x2-x1);
        int height = Math.abs(y2-y1);

        getGraphics().setColor(ColorMap.getSelectedColor());
        getGraphics().fillRect(x1, y1, width, height);
    }
}
