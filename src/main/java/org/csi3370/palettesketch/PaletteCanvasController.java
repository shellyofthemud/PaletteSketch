package org.csi3370.palettesketch;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import java.awt.image.BufferedImage;

public class PaletteCanvasController {

    // private final Canvas pImage;

    private final BufferedImage pImage;

    private final ImageView renderTarget;

    public PaletteCanvasController(ImageView renderTarget) {
        this.renderTarget = renderTarget;
        this.pImage = new BufferedImage((int) renderTarget.getFitWidth(), (int) renderTarget.getFitHeight(), BufferedImage.TYPE_INT_ARGB);
    }


    private static Image BufferedImageToFXImage(BufferedImage b) {
        WritableImage out = new WritableImage(b.getWidth(), b.getHeight());
        PixelWriter pw = out.getPixelWriter();
        for (int y=0; y<b.getHeight(); y++) {
            for (int x=0; x<b.getWidth(); x++) {
                // Color cVal = Integer.toHexString(b.getRGB(x, y));

            }
        }
        return out;
    }

    public void render(ImageView target) {
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
