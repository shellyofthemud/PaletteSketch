package org.csi3370.palettesketch;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
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


    private static Image BufferedImageToFXImage(BufferedImage b) {
        WritableImage out = new WritableImage(b.getWidth(), b.getHeight());
        PixelWriter pw = out.getPixelWriter();
        for (int y=0; y<b.getHeight(); y++) {
            for (int x=0; x<b.getWidth(); x++) {
                Color p = ColorMap.AWTColorToFXColor(new java.awt.Color(b.getRGB(x, y)));
                pw.setArgb(x, y, p.hashCode());
            }
        }
        return out;
    }

    public Graphics2D getGraphics() {
        return pImage.createGraphics();
    }

    public void render(ImageView target) {
        target.setImage(BufferedImageToFXImage(pImage));
        // pImage.snapshot((res) -> {
        //     PixelWriter pw = res.getImage().getPixelWriter();
        //     PixelReader pr = res.getImage().getPixelReader();
        //     for (int y=0; y<res.getImage().getHeight(); y++) {
        //         for (int x=0; x<res.getImage().getWidth(); x++) {
        //             Color c = pr.getColor(x, y);
        //             if ( c.hashCode() != Color.WHITE.hashCode() ) {
        //                 pw.setArgb(x, y, ColorMap.get(c).hashCode());
        //             }
        //         }
        //     }
        //     return null;
        // }, null, buffer);
        // target.setImage(buffer);
    }
}
