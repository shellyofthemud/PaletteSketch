package org.csi3370.ui;

import org.csi3370.Main;
import org.csi3370.MouseEvent;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

public class UIDisplay {

    private List<UIComponent> components = new ArrayList<>();

    public UIDisplay() {
        // TODO generate components
        // components.add(new PaletteColorDisplay());


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
