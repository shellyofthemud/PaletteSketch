package org.csi3370.palettesketch.tests;

import javafx.scene.paint.Color;
import org.csi3370.palettesketch.ColorMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColorMapTest {

    @Test
    void testAdd() {
        int size = ColorMap.size();
        ColorMap.add();
        assertEquals(size+1, ColorMap.size());
    }

    @Test
    void testGet() {
        java.awt.Color gray = ColorMap.get(127);
        assertEquals(127, gray.getRed());
        assertEquals(127, gray.getGreen());
        assertEquals(127, gray.getBlue());
    }


    @Test
    void AWTColorToFXColor() {
        java.awt.Color awtColor = java.awt.Color.BLACK;
        Color fxColor = ColorMap.AWTColorToFXColor(awtColor);
        assertEquals(fxColor.getRed()*255, awtColor.getRed());
    }

    @Test
    void getSelectedColor() {
        ColorMap.selectColor(2);
        assertEquals(2, ColorMap.getSelectedColor().getBlue());
    }

    @Test
    void getSelectedMappedColor() {
        java.awt.Color target = ColorMap.get(ColorMap.getSelectedColor());
        assertSame(target, ColorMap.getSelectedMappedColor());
    }

    @Test
    void testSelectColor() {
        ColorMap.selectColor(2);
        assertEquals(2, ColorMap.getSelectedColor().getBlue());
    }


    @Test
    void isSelected() {
        ColorMap.selectColor(2);
        assertTrue(ColorMap.isSelected(2));
    }

    @Test
    void size() {
        assertTrue(ColorMap.size() > 0);
    }

    @Test
    void remove() {
        int size = ColorMap.size();
        ColorMap.remove(1);
        assertTrue(size > ColorMap.size());
    }
}