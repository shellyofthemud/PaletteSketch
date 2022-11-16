package org.csi3370.palettesketch.tests;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.csi3370.palettesketch.ui.PaletteListDisplay;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaletteListDisplayTest {

    PaletteListDisplay pList = new PaletteListDisplay(new HBox());

    @Test
    void update() {
        pList.update();
    }

    @Test
    void createButton() {
        StackPane b = pList.createButton(1);
        assertNotNull(b);
    }

    @Test
    void getAddButton() {
        StackPane b = pList.getAddButton();
        assertNotNull(b);
    }

    @Test
    void createButtonList() {
        List<Node> bList = pList.createButtonList();
        for (Node n : bList) {
            assertTrue(pList.elements.contains(n));
        }
    }
}