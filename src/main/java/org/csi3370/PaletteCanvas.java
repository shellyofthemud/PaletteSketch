package org.csi3370;

import org.csi3370.tools.CanvasTool;
import org.csi3370.ui.PaletteListDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PaletteCanvas extends JPanel {

    private int width;
    private int height;
    private BufferedImage canvas;
    private CanvasTool activeTool;

    public PaletteCanvas(int width, int height) {
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        setCanvasSize(width, height);
    }

    public PaletteCanvas() {
        this(1280, 720);
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

    @Override
    public void paint(Graphics g) {
        // super.paint(g);
        setBounds(getBounds());
        g.drawImage(render(), 0, 0, width, height, Color.WHITE, this);
    }

    public void paint() {
        paint(getGraphics());
    }

    @Override
    public Rectangle getBounds() {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        int x = (Application.getAppInstance().getContentPane().getWidth()/2)-(width/2);
        int y = PaletteListDisplay.getInstance().getBounds().height+20;
        return new Rectangle(x, y, width, height);
    }

    public void setActiveTool(CanvasTool t) {
        removeMouseListener(this.activeTool);
        removeMouseMotionListener(this.activeTool);
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
