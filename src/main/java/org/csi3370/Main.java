package org.csi3370;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.opengl.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends PApplet {

    private PaletteCanvas pCanvas;
    float brushSize = 5;

    ArrayList<Color> colorMap = new ArrayList<>(Arrays.asList(Color.BLACK, Color.CYAN));

    public void settings() {
        size(1280, 720);
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
            System.out.printf("point at %d, %d\n", mouseX, mouseY);
        }
    }

    public static void main(String[] args) {
        String[] appletArgs = new String[] { "org.csi3370.Main" };
        PApplet.main(appletArgs);
    }
}