package org.csi3370.ui;

import org.csi3370.MouseEvent;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PShape;

import java.awt.*;

public class ButtonComponent extends UIComponent {

    private int xPos;
    private int yPos;
    private int width;
    private int height;
    private Color background;
    private String label;

    private PShape buttonShape;

    private ButtonAction onPress;

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public Color getBackground() {
        return background;
    }

    public String getLabel() {
        return label;
    }

    public ButtonAction getOnPress() {
        return onPress;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public PShape getButtonShape() {
        return buttonShape;
    }

    public void setButtonShape(PShape buttonShape) {
        this.buttonShape = buttonShape;
    }

    // generic class for creating button components
    public ButtonComponent(int xPos, int yPos, int width, int height, Color background, String label, ButtonAction onPress, PShape buttonShape) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.background = background;
        this.label = label;
        this.onPress = onPress;
        this.buttonShape = buttonShape;
    }

    public ButtonComponent(int xPos, int yPos, int width, int height, Color background, String label, ButtonAction onPress) {
        this(xPos, yPos, width, height, background, label, onPress, null /* TODO fix this */);
    }

    public ButtonComponent(int xPos, int yPos, int width, int height, String label, ButtonAction onPress) {
        this(xPos, yPos, width, height, Color.GRAY, label, onPress);
    }

    public ButtonComponent(int xPos, int yPos, int width, int height, String label) {
        this(xPos, yPos, width, height, Color.GRAY, label, null);
    }

    public ButtonComponent(int xPos, int yPos, int width, int height) {
        this(xPos, yPos, width, height, Color.GRAY, "", null);
    }

    @Override
    public PImage render() {
        PGraphics output = getSurface();
        output.fill(background.hashCode());
        output.noStroke();
        output.shape(buttonShape);
        output.background(background.hashCode());
        output.text(label, 0, 0, (float) (output.width*.9), (float) (output.height*.9));
        return output.copy();
    }

    @Override
    public boolean handleMouseEvent(MouseEvent e) {
        if (super.isInBounds(e)) {
            this.onPress.execute();
            return true;
        } else {
            return false;
        }
    }

    public void setOnPress(ButtonAction action) {
        this.onPress = action;
    }
}
