package org.csi3370;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class PaletteCanvas {

    private PGraphics pCanvas;

    // wrapper class for PGraphics
    public PaletteCanvas(PApplet parent) {
       pCanvas = parent.createGraphics(parent.width, parent.height);
       pCanvas.beginDraw();
       pCanvas.endDraw();
       pCanvas.loadPixels();

       pCanvas.background(255, 255, 255);
       pCanvas.fill(ColorMap.getSelectedColorIndex());
       pCanvas.stroke(ColorMap.getSelectedColorIndex());
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
                Color c = new Color(p);
                // using blue for index bc i'm lazy and it's the smallest bits
                int colorIndex = c.getBlue();
                if (colorIndex != 0) {
                    output.set(i % pCanvas.width, i / pCanvas.width, ColorMap.get(colorIndex).hashCode());
                }
            }

        }
        return output;
    }
}
