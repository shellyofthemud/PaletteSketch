package org.csi3370.palettesketch;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import org.csi3370.palettesketch.tools.CanvasTool;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PaletteCanvasController {

    // "canvas" emulation
    private final BufferedImage pImage;

    // javafx node to render to
    private final ImageView renderTarget;

    // creates a blank BufferedImage with the same dimensions as the supplied javafx.scene.image.ImageView
    public PaletteCanvasController(ImageView renderTarget) {
        this.renderTarget = renderTarget;
        this.pImage = new BufferedImage((int) renderTarget.getFitWidth(), (int) renderTarget.getFitHeight(), BufferedImage.TYPE_INT_ARGB);
        CanvasTool.setGraphicsReference(pImage.createGraphics());
    }

    // maps all greyscale values to colors indexed by ColorMap, then returns javafx.scene.image.Image
    void render() {
        WritableImage out = new WritableImage(pImage.getWidth(), pImage.getHeight());
        PixelWriter pw = out.getPixelWriter();
        for (int y=0; y<pImage.getHeight(); y++) {
            for (int x=0; x<pImage.getWidth(); x++) {
                int pVal = pImage.getRGB(x, y);
                Color oc = ColorMap.get(new java.awt.Color(pImage.getRGB(x, y)));
                pw.setArgb(x, y, oc.getRGB());
            }
        }
        renderTarget.setImage(new ImageView(out).getImage());
    }

    // returns graphics context for palette canvas BufferedImage
    public Graphics2D getGraphics() {
        return pImage.createGraphics();
    }
}
