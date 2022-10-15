package org.csi3370;

import org.csi3370.ui.UIDisplay;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

import java.awt.*;

// TODO clean up the junk
public class Main extends PApplet {

    // class singleton
    private static Main _instance;

    private UIDisplay ui;
    private PaletteCanvas pCanvas;
    float brushSize = 5;

    public static Color selectedColor;

    public PenTool activeTool;

    public void settings() {
        size(1280, 720);
        _instance = this;
    }

    // wait until everything is instantiated to start doing stuff
    // render() generates a PImage for each frame of each component and stacks them onto main canvas
    public void draw() {
        background(255);
        if (pCanvas == null) {
            pCanvas = new PaletteCanvas(this);
            background(255, 255, 255);
            colorMode(RGB);
        } else {
            image(pCanvas.render(), 0, 0);
        }

        if (ui == null) {
            ui = new UIDisplay();
        } else {
            image(ui.render(), 0, 0);
        }
    }

    @Override
    public void mouseDragged() {
        MouseEvent event = new MouseEvent(MouseEvent.EventType.DRAG, mouseX, mouseY);
        if (!ui.handleMouseEvent(event)) {
            activeTool.handleMouseEvent(event);
        }
    }

    @Override
    public void mouseReleased() {
        MouseEvent event = new MouseEvent(MouseEvent.EventType.RELEASE, mouseX, mouseY);
        if (!ui.handleMouseEvent(event)) {
            activeTool.handleMouseEvent(event);
        }
    }

    // wait until canvas is initialized to init tool for canvas reference
    @Override
    public void mousePressed() {
        MouseEvent event = new MouseEvent(MouseEvent.EventType.CLICK, mouseX, mouseY);
        if (activeTool == null) {
            initTool();
        }
        if (!ui.handleMouseEvent(event)) {
            activeTool.handleMouseEvent(event);
        }

    }

    private void initTool() {
        activeTool = new PenTool(pCanvas.getCanvas());
    }

    public static void main(String[] args) {
        String[] appletArgs = new String[] { "org.csi3370.Main" };
        PApplet.main(appletArgs);
    }

    public void setSelectedColor(Color c) {
        selectedColor = c;
        activeTool.setPenColor(c);
    }

    // convenience methods for passing/converting data
    public static PApplet getAppInstance() {
        return _instance;
    }

    public static PImage getImageSurface(int width, int height, int imageMode) {
        return _instance.createImage(width, height, imageMode);
    }

    public static PImage getImageSurface(int width, int height) {
        return getImageSurface(width, height, ARGB);
    }

    public static PImage getImageSurface() {
        return getImageSurface(_instance.width, _instance.height, ARGB);
    }

    public static PGraphics getGraphicsSurface() {
        return getGraphicsSurface(_instance.width, _instance.height);
    }

    public static PGraphics getGraphicsSurface(int width, int height) {
        return _instance.createGraphics(width, height);
    }

    public static PaletteCanvas getCanvas() {
        return _instance.pCanvas;
    }

}