package org.csi3370.ui;

import org.csi3370.Application;
import org.csi3370.ColorMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class PaletteListDisplay extends JPanel {

    private static PaletteListDisplay _instance;

    private final int height = 100;

    private PaletteListDisplay() {
        super();
        _instance = this;
        for (JPanel p : getButtons()) {
            add(p);
        }
        setBounds(getBounds());
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        setBounds(getBounds());
        paintChildren(g);
        // super.paint(g);
        setBorder(BorderFactory.createLineBorder(Color.black));
        paintBorder(g);
    }


    @Override
    public Rectangle getBounds() {
        int x = (Application.getAppInstance().getContentPane().getWidth()/2)-(getWidth()/2);
        int width = ColorButton.size*(this.getComponents().length);
        return new Rectangle(x, 10, width, ColorButton.size);
    }

    private void setSize() {
        int width = 0;
        for (Component c : getComponents()) {
            width += ColorButton.size;
        }
        setSize(new Dimension(width, ColorButton.size));
    }

    private static JPanel[] getButtons() {
        ArrayList<JPanel> buttons = new ArrayList<JPanel>();
        for (int i = 1; i<= ColorMap.size(); i++) {
            ColorButton b = new ColorButton(i);
            buttons.add(b);

        }

        JPanel addButton = new JPanel() {

            @Override
            public void paint(Graphics g) {
                setBounds(new Rectangle(_instance.getWidth()-ColorButton.size, 0, ColorButton.size, ColorButton.size));
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setColor(Color.black);
                setSize(ColorButton.getBoxSize(), ColorButton.getBoxSize());
                g.drawOval(ColorButton.getMargin(), ColorButton.getMargin(), ColorButton.getContentSize(), ColorButton.getContentSize());
                int fontSize = 30;
                g.setFont(new Font(getFont().getFamily(), Font.BOLD, fontSize));
                int x = (this.getWidth()/2)-7;
                int y = (this.getHeight()/2)+10;
                g.drawChars(new char[] {'+'}, 0, 1, x, y);
            }
        };
        MouseListener l = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ColorMap.add(Color.BLACK);
            }
        };
        addButton.addMouseListener(l);
        buttons.add(addButton);

        JPanel[] out = new JPanel[buttons.size()];
        return buttons.toArray(out);
    }

    public static void refresh() {
        if (_instance.getComponents().length-1 != ColorMap.size()) {
            for (Component c : _instance.getComponents()) {
                _instance.remove(c);
            }
            for (JPanel j : _instance.getButtons()) {
                _instance.add(j);
            }
        }
        _instance.repaint();
        _instance.paintChildren(_instance.getGraphics());
    }

    public static void update() {
        _instance.paintChildren(_instance.getGraphics());
    }

    public static PaletteListDisplay getInstance() {
        if (_instance == null) {
            return new PaletteListDisplay();
        } else {
            return _instance;
        }
    }

    public static Dimension getInstanceSize() {
        return new Dimension(_instance.getWidth(), _instance.getHeight());
    }

    private static class ColorButton extends JPanel implements MouseListener {

        private int colorIndex;

        public static final int size = 100;

        ColorButton(int colorIndex) {
            super();
            this.colorIndex = colorIndex;
            addMouseListener(this);
            repaint();
        }


        public static int getBoxSize() {
            return size;
        }

        public static int getContentSize() {
            return (int) (size*.85);
        }

        public static int getMargin() {
            return (int) (size*.075);
        }

        @Override
        public void paint(Graphics g) {
            setBounds(getBounds());
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(ColorMap.get(colorIndex));
            g.fillOval(getMargin(), getMargin(), getContentSize(), getContentSize());
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(2));
            if (colorIndex == ColorMap.getSelectedColorIndex()) {
                g2.setColor(new Color(127, 127, 127));
            } else {
                g2.setColor(Color.WHITE);
            }
            g2.drawOval(getMargin(), getMargin(), getContentSize(), getContentSize());
        }

        public Rectangle getBounds() {
            return new Rectangle(size*(colorIndex-1), 0, size, size);
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
