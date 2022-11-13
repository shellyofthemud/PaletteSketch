package org.csi3370.palettesketch.ui;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import org.csi3370.palettesketch.ColorMap;

import javax.swing.text.TableView;

// another 1-indexed list, sorry
public class PaletteListDisplay {

    private HBox container;
    public PaletteListDisplay(HBox anchor) {
        container = anchor;
        createButtonList();
    }

    HBox getAnchor() {
        return container;
    }

    void redraw(int index) {
        Canvas target = (Canvas) getAnchor().getChildren().get(index);
        int size = 100;
        GraphicsContext g = target.getGraphicsContext2D();
        g.setFill(ColorMap.AWTColorToFXColor(ColorMap.get(index)));
        g.setStroke((ColorMap.isSelected(index)) ? Color.WHITE : Color.LIGHTGREY);
        g.fillOval(0, 0, size*.85, size*.85);
        g.strokeOval(0, 0, size*.85, size*.85);
    }

    public void redraw() {
        ObservableList<Node> children = getAnchor().getChildren();
        for (int i=0; i<children.size(); i++) {
            redraw(i);
        }
    }

    public void update() {
        getAnchor().getChildren().clear();
        createButtonList();
        redraw();
    }

    void createButtonList() {
        for (int i=1; i<=ColorMap.size(); i++) {
            Canvas c = new Canvas(getAnchor().getHeight(), getAnchor().getHeight());
            c.setOnMouseClicked(e -> {
                ColorMap.selectColor(getAnchor().getChildren().indexOf(c) + 1);
                this.redraw();
            });
            getAnchor().getChildren().add(c);
            redraw();
        }
    }

}
