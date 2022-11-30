package org.csi3370.palettesketch;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import org.csi3370.palettesketch.tools.CanvasTool;
import org.csi3370.palettesketch.ui.PaletteListDisplay;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

import java.awt.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import static org.csi3370.palettesketch.ColorMap.Channel.*;

public class PSketchController {

    @FXML
    private HBox paletteListDisplayContainer;

    @FXML
    private ImageView paletteCanvasView;

    @FXML
    private Slider sliderRed;

    @FXML
    private TextField textValRed;

    @FXML
    private Slider sliderGreen;

    @FXML
    private TextField textValGreen;

    @FXML
    private Slider sliderBlue;

    @FXML
    private TextField textValBlue;

    @FXML
    private Rectangle rectToolPreview;

    private PaletteCanvasController pCanvas;

    private PaletteListDisplay pList;


    private static PSketchController _instance;

    // initialize the greyscale canvas BufferedImage
    public void initPCanvas() {
        pCanvas = new PaletteCanvasController(paletteCanvasView);
        CanvasTool.setGraphicsReference(pCanvas.getGraphics());
        pList = new PaletteListDisplay(paletteListDisplayContainer);


        // adding hooks for slider property changes
        sliderRed.valueProperty().addListener((observable, oldVal, newVal) -> {
            Color newColor = ColorMap.setColorChannel(ColorMap.getSelectedMappedColor(), RED, newVal.intValue());
            ColorMap.setSelectedColor(newColor);
            pList.update();
            pCanvas.render();
            textValRed.setText(String.valueOf(newVal.intValue()));
        });
        sliderGreen.valueProperty().addListener((observable, oldVal, newVal) -> {
            Color newColor = ColorMap.setColorChannel(ColorMap.getSelectedMappedColor(), GREEN, newVal.intValue());
            ColorMap.setSelectedColor(newColor);
            pList.update();
            pCanvas.render();
            textValGreen.setText(String.valueOf(newVal.intValue()));
        });
        sliderBlue.valueProperty().addListener((observable, oldVal, newVal) -> {
            Color newColor = ColorMap.setColorChannel(ColorMap.getSelectedMappedColor(), BLUE, newVal.intValue());
            ColorMap.setSelectedColor(newColor);
            pList.update();
            pCanvas.render();
            textValBlue.setText(String.valueOf(newVal.intValue()));
        });
        setSliders(ColorMap.getSelectedMappedColor());
        _instance = this;
    }

    void setSliders(Color c) {
        setSliders(c.getRed(), c.getGreen(), c.getBlue());
    }

    void setSliders(int rVal, int gVal, int bVal) {
        sliderRed.setValue(rVal);
        sliderGreen.setValue(gVal);
        sliderBlue.setValue(bVal);
    }

    public static PSketchController getInstance() {
        return _instance;
    }

    public ImageView getPaletteCanvasView() {
        return paletteCanvasView;
    }

    // handlers for passing mouse events to canvas tools
    @FXML
    private void onMouseClickedCanvas(MouseEvent e) {
        CanvasTool.getActiveTool().onMouseClick(e);
        pCanvas.render();
    }

    @FXML
    private void onMouseDraggedCanvas(MouseEvent e) {
        CanvasTool.getActiveTool().onMouseDrag(e);
        pCanvas.render();
    }

    @FXML
    private void onMouseDragReleaseCanvas(MouseEvent e) {
        CanvasTool.getActiveTool().onMouseDragRelease(e);
        pCanvas.render();
    }

    @FXML
    private void setPenTool() {
        CanvasTool.setActiveTool(CanvasTool.Tools.PEN);
    }

    @FXML
    private void setEraserTool() {
        CanvasTool.setActiveTool(CanvasTool.Tools.ERASER);
    }

    @FXML
    private void setRectangleTool() {
        CanvasTool.setActiveTool(CanvasTool.Tools.RECTANGLE);
    }

    @FXML
    private void ExportGrayScaleImage() {
        try {
            BufferedImage pImage = pCanvas.getpImage();
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image file", "*.png"));
            File output = fc.showSaveDialog(pCanvas.getPrimaryScene().getWindow());
            fc.setTitle("Choose file location");
            ImageIO.write(pImage, "png", output);
        } catch (IOException e) {
            System.out.printf("failed to export image due to %s\n", e.getStackTrace());
        }
    }


    @FXML
    private void ExportPalette() {
        try {
            JSONObject j = new JSONObject();
            for (int i=1; i <= ColorMap.size(); i++) {
                j.put(i, Integer.toHexString(ColorMap.get(i).hashCode()));
            }
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON files", "*.json"));
            FileWriter output = new FileWriter(fc.showSaveDialog(pCanvas.getPrimaryScene().getWindow()));
            output.write(j.toJSONString());
            output.close();
        } catch (Exception e) {
            System.out.printf("export palette failed due to %s\n", e.getStackTrace());
        }
    }

    @FXML
    private void ExportRenderedImage() {
        try {
            Image rImage = pCanvas.getRenderedImage();
            FileChooser fc = new FileChooser();
            int width = (int) rImage.getWidth();
            int height = (int) rImage.getHeight();

            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image file", "*.png"));
            BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            for (int y=0; y<height; y++) {
                for (int x=0; x<width; x++) {
                    var pixel = rImage.getPixelReader().getArgb(x, y);

                    output.setRGB(x, y, pixel);
                }
            }

            File outFile = fc.showSaveDialog(pCanvas.getPrimaryScene().getWindow());
            ImageIO.write(output, "png", outFile);
        } catch (Exception e) {
            System.out.printf("Export rendered image failed due to %s\n", e.getStackTrace());
        }
    }
}
