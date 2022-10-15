package org.csi3370.ui;

import org.csi3370.Main;
import org.csi3370.MouseEvent;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;

import static processing.core.PConstants.ARGB;

// Base class for UI elements
public abstract class UIComponent {

    // TODO create individual components extended from this

    public int xPos;
    public int yPos;

    private int width;
    private int height;


    public UIComponent() {
        this.xPos = 0;
        this.yPos = 0;
    }

    public UIComponent(int xPos, int yPos, int width, int height) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void setX(int xPos) {
        this.xPos = xPos;
    }

    public void setY(int yPos) {
        this.yPos = yPos;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }


    public boolean isInBounds(int x, int y) {
        if (((x > xPos) && (x < xPos+this.getWidth())) &&
                ((y > yPos) && (y < yPos+this.getHeight()))) {
            return true;
        }
        return false;
    }

    public boolean isInBounds(MouseEvent e) {
        return isInBounds(e.getMouseX(), e.getMouseY());
    }

    PGraphics getSurface() {
        return Main.getGraphicsSurface(this.width, this.height);
    }

    public abstract PImage render();
    public abstract boolean handleMouseEvent(MouseEvent e);
}
