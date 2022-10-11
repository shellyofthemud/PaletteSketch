package org.csi3370;

import processing.awt.PGraphicsJava2D;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static processing.core.PConstants.RGB;

public class PaletteCanvas extends PGraphics {

    private ArrayList<Color> colorMap = new ArrayList<Color>() ;
    private int selectedColor = 0;
    private PGraphics pCanvas;

    public PaletteCanvas(PApplet parent) {
       pCanvas = parent.createGraphics(parent.width, parent.height);
       pCanvas.beginDraw();
       pCanvas.endDraw();
       pCanvas.loadPixels();

       pCanvas.background(255, 255, 255);


       createColor();
       setSelectedColor(1);
    }

    public int createColor() {
        int colorIndex = colorMap.size() + 1;
        colorMap.add(new Color(127, 127, 127));
        return colorMap.size()-1;
    }

    public int createColor(Color c) {
        colorMap.add(c);
        return colorMap.size();
    }

    public int createColor(int colorValue) {
        colorMap.add(new Color(colorValue));
        return colorMap.size()-1;
    }

    public void setSelectedColor(int newColor) {
        selectedColor = newColor;
        pCanvas.fill(newColor);
        pCanvas.stroke(newColor);
        System.out.printf("pcanvas selected color: %s", Integer.toHexString(pCanvas.fillColor));
    }

    public void setColor(int index, Color newColor) {
        colorMap.set(index, newColor);
    }

    public Color getColor(int index) {
        // int colorHex = (c.getRed()*10000) + (c.getGreen()*100) + (c.getBlue());
        return colorMap.get(index-1);
    }

    public Color getSelectedColor() {
        return colorMap.get(selectedColor-1);
    }

    public PGraphics getCanvas() {
        return pCanvas;
    }

    public PImage render() {
        PImage output = (PImage) pCanvas.copy();
        for (int i=0; i<pCanvas.pixels.length; i++) {
            int p = pCanvas.pixels[i];
            if (p != 0) {
                String colorHex = Integer.toHexString(p);
                int colorIndex = Integer.parseInt(colorHex.substring(colorHex.length() - 2));
                if (colorIndex != 0) {
                    output.set(i % pCanvas.width, i / pCanvas.width, getColor(colorIndex).hashCode());
                }
            }

        }
        return output;
    }
}
