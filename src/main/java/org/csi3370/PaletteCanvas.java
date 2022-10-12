package org.csi3370;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class PaletteCanvas extends PGraphics {

    // TODO make colorMap its own class singleton
    public ArrayList<Color> colorMap = new ArrayList<Color>(Arrays.asList(Color.GRAY, Color.BLACK));
    private int selectedColor;
    private PGraphics pCanvas;

    // wrapper class for PGraphics
    public PaletteCanvas(PApplet parent) {
       pCanvas = parent.createGraphics(parent.width, parent.height);
       pCanvas.beginDraw();
       pCanvas.endDraw();
       pCanvas.loadPixels();

       pCanvas.background(255, 255, 255);
       setSelectedColor(1);
    }

    public int createColor() {
        Color c = new Color(127, 127, 127);
        colorMap.add(c);
        return colorMap.indexOf(c);
    }

    public int createColor(Color c) {
        colorMap.add(c);
        return colorMap.indexOf(c);
    }

    public int createColor(int colorValue) {
        Color c = new Color(colorValue);
        colorMap.add(c);
        return colorMap.indexOf(c);
    }

    public void setSelectedColor(int newColor) {
        selectedColor = newColor;
        pCanvas.fill(newColor);
        pCanvas.stroke(newColor);
    }

    public void setColor(int index, Color newColor) {
        colorMap.set(index, newColor);
    }

    public Color getColor(int index) {
        return colorMap.get(index-1);
    }

    public Color getSelectedColor() {
        return colorMap.get(selectedColor-1);
    }

    public PGraphics getCanvas() {
        return pCanvas;
    }

    // the PGraphics object is treated as a map of color index pixels, [ 0, 0, 1, 2, 2 ];
    // This function generates a PImage
    public PImage render() {
        PImage output = (PImage) pCanvas.copy();
        for (int i=0; i<pCanvas.pixels.length; i++) {
            int p = pCanvas.pixels[i];
            if (p != 0) {
                String colorHex = Integer.toHexString(p);
                System.out.println(colorHex);
                int colorIndex = Integer.parseInt(colorHex.substring(colorHex.length() - 2));
                if (colorIndex != 0) {
                    output.set(i % pCanvas.width, i / pCanvas.width, getColor(colorIndex).hashCode());
                }
            }

        }
        return output;
    }
}
