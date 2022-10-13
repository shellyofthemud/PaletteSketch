package org.csi3370;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.List;

import static processing.core.PConstants.RGB;

public class UIDisplay {

    private List<UIComponent> components;

    public UIDisplay() {
        // TODO render components
    }

    // hook for passing
    public boolean handleMouseEvent(PApplet parent) {
        for (UIComponent c : components) {
            if (c.handleMouseEvent(parent)) {
                return true;
            }
        }
        return false;
    }

    public PImage render() {
        PApplet parent = Main.getAppInstance();
        PImage output = parent.createImage(parent.width, parent.height, RGB);
        for (UIComponent c : components) {
            output.set(c.xPos, c.yPos, c.render());
        }
        return output;
    }
}
