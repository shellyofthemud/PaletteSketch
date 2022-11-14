package org.csi3370.palettesketch.ui;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.csi3370.palettesketch.ColorMap;

import javax.swing.text.TableView;
import java.util.ArrayList;
import java.util.List;

// another 1-indexed list, sorry
public class PaletteListDisplay {

    private final int size = 100;

    private ArrayList<Node> elements;

    private HBox container;
    public PaletteListDisplay(HBox anchor) {
        container = anchor;
        this.update();
    }

    public void update() {
        container.getChildren().clear();
        container.getChildren().addAll(createButtonList());
    }

    StackPane createButton(int index) {
        int size = (int) (container.getPrefHeight()/2);
        Circle c1 = new Circle(size, ColorMap.AWTColorToFXColor(ColorMap.get(index)));
        Circle c2 = new Circle(size, Color.color(1, 1, 1, 0));
        double cVal = (ColorMap.isSelected(index)) ? .5 : 0.0;
        c2.setStroke(Color.color(cVal, cVal, cVal, 1.0));
        c2.setSmooth(true);
        StackPane swatchBox = new StackPane(c1, c2);
        swatchBox.setOnMouseClicked(e -> {
            ColorMap.selectColor(index);
            update();
        });
        return swatchBox;
    }

    StackPane getAddButton() {
        Text t = new Text("+");
        t.setFont(Font.font(36));
        Circle c = new Circle(container.getPrefHeight()/2, Color.color(0, 0, 0, 0));
        c.setStroke(Color.BLACK);
        StackPane sp = new StackPane(c, t);
        sp.setOnMouseClicked((e)-> {
            ColorMap.add();
            update();
        });
        return sp;
    }

    List<Node> createButtonList() {
        ArrayList<Node> buttons = new ArrayList<>();
        for (int i=1; i<=ColorMap.size(); i++) {
            buttons.add(createButton(i));
        }
        buttons.add(getAddButton());
        this.elements = buttons;
        return buttons;
    }

}
