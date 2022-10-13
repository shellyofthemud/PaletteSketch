package org.csi3370;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ColorMap {

    // I'm sorry but I think this has to be treated as a 1-indexed list

    private static ArrayList<Color> data = new ArrayList<>(Arrays.asList(Color.BLACK));
    private static int selectedColor = 1;

    public static void Set(int index, Color newColor) {
        data.set(index-1, newColor);
    }

    // throw an error if called out of bounds
    public static void setSelectedColor(int newSelectedColor) {
        selectedColor = data.indexOf(data.get(newSelectedColor-1))+1;
    }

    public static Color get(int index) {
        return data.get(index-1);
    }

    public static Color getSelectedColor() {
        return data.get(selectedColor-1);
    }

    public static int getSelectedColorIndex() {
        return selectedColor;
    }

    public static void add(Color c) {
        data.add(c);
    }

    public static int size() {
        return data.size();
    }

    public static void remove(int index) {
        data.remove(index-1);
    }
}
