package org.csi3370.ui;

import org.csi3370.Main;
import processing.core.PConstants;
import processing.core.PShape;

public class ShapeFactory {

    public static PShape getRect(int width, int height) {
        return Main.getAppInstance().createShape(PConstants.RECT, 0, 0, width, height);
    }

    public static PShape getCircle(int diameter) {
        return Main.getAppInstance().createShape(PConstants.ELLIPSE, diameter/2, diameter/2, diameter, diameter);
    }
}
