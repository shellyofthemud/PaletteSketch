package org.csi3370;

import processing.core.PGraphics;
import processing.core.PImage;

import java.awt.*;

import static processing.core.PConstants.ARGB;

public class PaletteListComponent extends UIComponent {

    private int elementSize = 100;

    public PaletteListComponent() {
        super();
        super.setY(Main.getAppInstance().height/20);
    }

    @Override
    public int getWidth() {
        return (int) (1.1*elementSize*ColorMap.size());
    }

    @Override
    public int getHeight() {
        return (int) (1.1*elementSize);
    }

    @Override
    public boolean handleMouseEvent(MouseEvent e) {
        if (super.handleMouseEvent(e)) {
            // handle button press
            if (e.type == MouseEvent.EventType.CLICK) {
                int x = e.mouseX;
                int selectIndex = ((x-this.xPos)/elementSize)+1;
                ColorMap.setSelectedColor(selectIndex);
            }
            return true;
        }

        return false;
    }

    public PImage render() {
        PGraphics image = Main.getGraphicsSurface( elementSize*ColorMap.size(), elementSize);
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
