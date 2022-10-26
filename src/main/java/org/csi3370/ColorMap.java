package org.csi3370;

import org.csi3370.ui.ColorChanger;
import org.csi3370.ui.PaletteListDisplay;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ColorMap {

    // I'm sorry but I think this has to be treated as a 1-indexed list

    // instance data
    private static ArrayList<Color> data = new ArrayList<>(Arrays.asList(Color.CYAN, Color.RED));
    private static int selectedColor = 1;

    public static void setSelectedColor(int newSelectedColor) {
        // throw an error if called out of bounds
        selectedColor = data.indexOf(data.get(newSelectedColor-1))+1;
        Application.setActiveToolColor(getSelectedIndexColor());
        ColorChanger.update();
        PaletteListDisplay.update();
    }

    public static boolean contains(Color c) {
        return data.contains(c);
    }

    public static int indexOf(Color c) {
        return data.indexOf(c);
    }

    public static Color get(int index) {
        return data.get(index-1);
    }

    // returns translated Color of selected color
    public static Color getSelectedColor() {
        return data.get(selectedColor-1);
    }

    // returns integer index of selected color
    public static int getSelectedColorIndex() {
        return selectedColor;
    }

    // returns integer index of selected color in the format of a grayscale color
    public static Color getSelectedIndexColor() { return new Color(selectedColor, selectedColor, selectedColor);}

    public static void set(int i, Color c) {
        data.set(i-1, c);
        PaletteListDisplay.getInstance().getComponent(selectedColor-1).repaint();
        Application.getInstanceCanvas().repaint();
    }

    public static void add(Color c) {
        data.add(c);
        PaletteListDisplay.refresh();
        ColorChanger.update();
    }

    public static int size() {
        return data.size();
    }

    public static void remove(int index) {
        data.remove(index-1);
    }
}
