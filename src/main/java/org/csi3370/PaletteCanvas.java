package org.csi3370;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class PaletteCanvas extends JPanel {

    private int width;
    private int height;
    private BufferedImage canvas;

    public PenTool activeTool;

    public PaletteCanvas(int width, int height) {
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        setCanvasSize(width, height);
        setActiveTool(new PenTool(canvas.getGraphics()));
    }

    private void setCanvasSize(int width, int height) {
        BufferedImage newCanvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        if (canvas != null) {
            newCanvas.createGraphics().drawImage(canvas, 0, 0, this);
        }
        this.setSize(width, height);
        this.setPreferredSize(new Dimension(width, height));
        this.width = width;
        this.height = height;

    }

    public Graphics getCanvasGraphics() {
        return canvas.getGraphics();
    }

    public void paint(Graphics g) {
        super.paintComponent(g);
        g.drawImage(render(), 0, 0, width, height, Color.WHITE, this);
        int windowWidth = Application.getAppInstance().getContentPane().getWidth();
        int yPos = PaletteListDisplay.getInstanceSize().height+10;
        this.setBounds((windowWidth/2)-(width/2), yPos, width, height);
    }

    public void setActiveTool(PenTool t) {
        this.activeTool = t;
        addMouseListener(t);
        addMouseMotionListener(t);
    }


    // public void paintComponent()

    // the BufferedImage object is treated as a map of color index pixels, [ 0, 0, 1, 2, 2 ];
    // This function generates a colormapped BufferendImage
    public BufferedImage render() {
        if ((width != 0) && (height != 0)) {
            BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int colorIndex = (new Color(canvas.getRGB(x, y))).getBlue();
                    if (colorIndex != 0) {
                        Color outputColor = ColorMap.get(colorIndex);
                        output.setRGB(x, y, outputColor.hashCode());
                    }
                }
            }
            return output;
        } else {
            return (BufferedImage) null;
        }
    }

}
