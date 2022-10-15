package org.csi3370.ui;

import org.csi3370.ColorMap;
import org.csi3370.Main;
import org.csi3370.MouseEvent;
import processing.core.PGraphics;
import processing.core.PImage;

import java.awt.*;

public class PaletteColorDisplay extends UIComponent {

    private static final int elementSize = 60;

    private final int index;

    public PaletteColorDisplay(int index) {
        super();
        super.setY(Main.getAppInstance().height/20);
        this.index = index;
    }

    @Override
    public int getWidth() {
        return (int) (1.1*elementSize);
    }

    @Override
    public int getHeight() {
        return (int) (1.1*elementSize);
    }

    @Override
    public boolean handleMouseEvent(MouseEvent e) {
        if (super.isInBounds(e)) {
            // handle button press
            if (e.getType() == MouseEvent.EventType.CLICK) {
                int x = e.getMouseX();
                int selectIndex = ((x-this.xPos)/elementSize)+1;
                ColorMap.setSelectedColor(selectIndex);
            }
            return true;
        }

        return false;
    }

    public PImage render() {
        PGraphics image = Main.getGraphicsSurface( getWidth(), getHeight());
        image.beginDraw();
        image.endDraw();
        for (int i=1; i<=ColorMap.size(); i++) {
            Color c = ColorMap.get(i);
            if (ColorMap.getSelectedColorIndex() == i) {
                image.stroke(127);
            } else {
                image.stroke(0);
            }
            image.fill(c.getRed(), c.getGreen(), c.getBlue());
            image.beginDraw();
            image.ellipse((float) ((elementSize*(i-1))+elementSize/2), (float) (elementSize/2), (float) (elementSize*0.9), (float) (elementSize*0.9));
            image.endDraw();
        }
        super.setX((Main.getAppInstance().width/2)-(image.width/2));
        return image;
    }
}
