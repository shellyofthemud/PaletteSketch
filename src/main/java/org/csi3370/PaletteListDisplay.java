package org.csi3370;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PaletteListDisplay extends JPanel {

    private static PaletteListDisplay _instance;

    public PaletteListDisplay() {
        super();
        _instance = this;
        setBackground(Color.WHITE);
        for (JPanel p : getButtons()) {
            add(p);
        }
        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        setSize();
        setBackground(new Color(0, 0, 0, 0));
        Dimension windowSize = Application.getAppInstance().getContentPane().getSize();
        this.setBounds(new Rectangle((windowSize.width/2)-(getWidth()/2), 0, ColorButton.size*(this.getComponents().length), ColorButton.size));
        this.getGraphics().drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());

        List<Component> components = List.of(getComponents());
        for (Component c : components) {
            c.setBounds((ColorButton.size)*(components.indexOf(c)), 0, ColorButton.size, ColorButton.size);
            c.repaint();
        }
    }

    private void setSize() {
        int width = 0;
        for (Component c : getComponents()) {
            width += ColorButton.size;
        }
        setSize(new Dimension(width, ColorButton.size));
    }

    private JPanel[] getButtons() {
        ArrayList<JPanel> buttons = new ArrayList<JPanel>();
        for (int i=1; i<=ColorMap.size(); i++) {
            ColorButton b = new ColorButton(i);
            buttons.add(b);

        }

        // JPanel addButton = new JPanel() {
        //     @Override
        //     public void paint(Graphics g) {
        //         super.paint(g);
        //         g.drawBytes("+".getBytes(), 0, 1, 0, 0);
        //     }
        // };
        // MouseListener l = new MouseAdapter() {
        //     @Override
        //     public void mouseClicked(MouseEvent e) {
        //         super.mouseClicked(e);
        //         ColorMap.add(Color.BLACK);
        //     }
        // };
        // addButton.setSize(new Dimension(ColorButton.size, ColorButton.size));
        // addButton.addMouseListener(l);
        // buttons[buttons.length-1] = addButton;

        JPanel[] out = new JPanel[buttons.size()];
        return buttons.toArray(out);
    }

    public static Dimension getInstanceSize() {
        return new Dimension(_instance.getWidth(), _instance.getHeight());
    }

    private class ColorButton extends JPanel implements MouseListener {

        private int colorIndex;

        public static int size = 100;

        ColorButton(int colorIndex) {
            super();
            this.colorIndex = colorIndex;
            setSize(new Dimension(size, size));
            addMouseListener(this);
            repaint();
        }

        public void paint(Graphics g) {
            g.setColor(ColorMap.get(colorIndex));
            g.fillOval(0, 0, size, size);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            ColorMap.setSelectedColor(this.colorIndex);
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
