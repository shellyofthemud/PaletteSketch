package org.csi3370;

import processing.core.PApplet;

import java.util.List;

public class UIDisplay {

    private List<UIComponent> components;

    public UIDisplay() {
        // TODO render components
    }

    public boolean handleMouseEvent(PApplet parent) {
        for (UIComponent c : components) {
            if (c.handleMouseEvent(parent)) {
                return true;
            }
        }
        return false;
    }
}
