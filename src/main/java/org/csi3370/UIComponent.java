package org.csi3370;

import processing.core.PApplet;
import processing.core.PImage;

// Base class for UI elements
public class UIComponent {

    // TODO create individual components extended from this

    public int xPos;
    public int yPos;
    private PImage data;


    public UIComponent() {
        this.xPos = 0;
        this.yPos = 0;
        this.data = null;
    }
    public UIComponent(int xPos, int yPos, PImage data) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.data = data;
    }

    public void setX(int xPos) {
        this.xPos = xPos;
    }

    public void setY(int yPos) {
        this.yPos = yPos;
    }

    public void setData(PImage data) {
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

    public PImage render() {
        PImage output = Main.getImageSurface();

        return output;
    }
}
