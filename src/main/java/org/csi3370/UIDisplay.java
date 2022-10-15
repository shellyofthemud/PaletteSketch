package org.csi3370;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

import static processing.core.PConstants.RGB;

public class UIDisplay {

    private List<UIComponent> components = new ArrayList<>();

    public UIDisplay() {
        // TODO generate components
        components.add(new PaletteListComponent());

    }

    // hook for passing
    public boolean handleMouseEvent(MouseEvent e) {
        for (UIComponent c : components) {
            if (c.handleMouseEvent(e)) {
                return true;
            }
        }
        return false;
    }

    public PImage render() {
        PImage output = Main.getImageSurface();
        for (UIComponent c : components) {
            output.set(c.xPos, c.yPos, c.render());
        }
        return output;
    }
}
