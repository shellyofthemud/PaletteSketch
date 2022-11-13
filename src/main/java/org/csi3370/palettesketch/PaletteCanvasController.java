package org.csi3370.palettesketch;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import org.csi3370.palettesketch.tools.CanvasTool;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PaletteCanvasController {

    // private final Canvas pImage;

    private final BufferedImage pImage;

    private final ImageView renderTarget;

    private int strokeWidth;

    public PaletteCanvasController(ImageView renderTarget) {
        this.renderTarget = renderTarget;
        this.pImage = new BufferedImage((int) renderTarget.getFitWidth(), (int) renderTarget.getFitHeight(), BufferedImage.TYPE_INT_ARGB);
        CanvasTool.setGraphicsReference(pImage.createGraphics());
    }

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

    public Graphics2D getGraphics() {
        return pImage.createGraphics();
    }
}
