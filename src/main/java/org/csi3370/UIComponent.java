package org.csi3370;

import processing.core.PApplet;
import processing.core.PImage;

public class UIComponent {

    public int xPos;
    public int yPos;
    private PImage data;

    public UIComponent(int xPos, int yPos, PImage data) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.data = data;
    }

    public PImage getData() {
        return this.data;
    }

    public boolean handleMouseEvent(PApplet parent) {
        int mouseX = parent.mouseX;
        int mouseY = parent.mouseY;
        boolean mousePressed = parent.mousePressed;
        if (data.pixels.length > 0) {
            if (((mouseX > xPos) && (mouseX < xPos+data.width)) &&
                    ((mouseY > yPos) && (mouseY < yPos+data.height))) {
                return true;
            }
        }
        return false;
    }
}
