package org.csi3370;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends PApplet {

    private static Main _instance;

    private PaletteCanvas pCanvas;
    float brushSize = 5;

    ArrayList<Color> colorMap = new ArrayList<>(Arrays.asList(Color.BLACK, Color.CYAN));

    public void settings() {
        size(1280, 720);
        _instance = this;
    }

    public void draw() {
        if (pCanvas == null) {
            pCanvas = new PaletteCanvas(this);
            background(255, 255, 255);
            colorMode(RGB);
        } else {
            image(pCanvas.render(), 0, 0);
        }
    }

    @Override
    public void mouseDragged() {
        if (mousePressed) {
            // canvas.drawPoint(brushSize, mouseX, mouseY);
            PGraphics canvas = pCanvas.getCanvas();
            canvas.beginDraw();
            canvas.ellipse(mouseX, mouseY, brushSize, brushSize);
            canvas.endDraw();
        }
    }

    public static void main(String[] args) {
        String[] appletArgs = new String[] { "org.csi3370.Main" };
        PApplet.main(appletArgs);
    }

    public static PApplet getAppInstance() {
        return _instance;
    }

    public static PImage getImageSurface(int width, int height, int imageMode) {
        return _instance.createImage(width, height, imageMode);
    }

    public static PImage getImageSurface() {
        return getImageSurface(_instance.width, _instance.height, RGB);
    }

    public static PaletteCanvas getCanvas() {
        return _instance.pCanvas;
    }

}