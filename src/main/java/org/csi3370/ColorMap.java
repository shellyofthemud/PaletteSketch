package org.csi3370;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ColorMap {

    // I'm sorry but I think this has to be treated as a 1-indexed list

    private static ArrayList<Color> data = new ArrayList<>(Arrays.asList(Color.CYAN, Color.BLACK));
    private static int selectedColor = 1;

    public static void Set(int index, Color newColor) {
        data.set(index-1, newColor);
    }

    // throw an error if called out of bounds
    public static void setSelectedColor(int newSelectedColor) {
        selectedColor = data.indexOf(data.get(newSelectedColor-1))+1;
        Application.getAppInstance().setSelectedColor(new Color(selectedColor, selectedColor, selectedColor));

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

    public static Color getSelectedColor() {
        return data.get(selectedColor-1);
    }

    public static int getSelectedColorIndex() {
        return selectedColor;
    }

    public static Color getSelectedIndexColor() { return new Color(selectedColor, selectedColor, selectedColor);}

    // convenience method for getting single int as grayscale color
    public static Color getIndexColor(int index) {
        return new Color(index, index, index);
    }

    public static int colorAsRGBInt(Color c) {
        return Integer.decode(Integer.toHexString(c.hashCode()));
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
