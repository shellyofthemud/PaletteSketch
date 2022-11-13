package org.csi3370.palettesketch;

import javafx.scene.paint.Color;

import java.util.HashMap;

public class ColorMap {

    // I'm sorry I think this has to be treated as a 1-indexed list

    // instance data
    // private static ArrayList<Color> data = new ArrayList<>(Arrays.asList(Color.PINK, Color.RED));

    private static HashMap<java.awt.Color, java.awt.Color> data = new HashMap<>();
    private static java.awt.Color selectedColor;

    // class init block
    static {
        add(java.awt.Color.PINK);
        add(java.awt.Color.CYAN);
    }

    private static void add(java.awt.Color c) {
        int gsIndex = data.size()+1;
        data.put(new java.awt.Color(gsIndex, gsIndex, gsIndex), c);
    }

    public static java.awt.Color get(java.awt.Color c) {
        return data.get(c);
    }

    public static Color AWTColorToFXColor(java.awt.Color c) {
        return Color.rgb(c.getRed(), c.getGreen(), c.getBlue());
    }

    // returns translated Color of selected color
    public static java.awt.Color getSelectedColor() {
        return selectedColor;
    }


    public static void selectColor(java.awt.Color c) {
        if (data.containsKey(c)) {
            selectedColor = c;
        } else {
            System.out.printf("Failed to set active color %s\n", c.toString());
        }
    }

    public static int size() {
        return data.size();
    }

    public static void remove(int index) {
        data.remove(index-1);
    }
}