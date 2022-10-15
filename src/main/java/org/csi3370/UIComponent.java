package org.csi3370;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;

import static processing.core.PConstants.ARGB;

// Base class for UI elements
public class UIComponent {

    // TODO create individual components extended from this

    public int xPos;
    public int yPos;
    PImage data;


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

    public void setSize(int width, int height) {
        data = Main.getImageSurface(width, height, ARGB);
    }

    public int getWidth() {
        return this.data.width;
    }

    public int getHeight() {
        return this.data.height;
    }

    public boolean handleMouseEvent(MouseEvent e) {
        if (isInBounds(e.mouseX, e.mouseY)) {
            return true;
        }
        return false;
    }

    public boolean isInBounds(int x, int y) {
        if (((x > xPos) && (x < xPos+this.getWidth())) &&
                ((y > yPos) && (y < yPos+this.getHeight()))) {
            return true;
        }
        return false;
    }

    public PImage render() {
        return Main.getImageSurface(0, 0, ARGB);
    }
}
