package org.csi3370.ui;

import org.csi3370.Application;
import org.csi3370.ColorMap;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;

public class ColorChanger extends JPanel {

    private static int margin = 10;
    private static ColorChanger _instance;
    private enum Channel {
        RED(1, 'R', Color.RED),
        GREEN(2, 'G', Color.GREEN),
        BLUE(3, 'B', Color.BLUE);

        public final int value;
        public final char asChar;
        public final Color asColor;

        Channel(int value, char charRep, Color colorRep) {
            this.value = value;
            this.asChar = charRep;
            this.asColor = colorRep;
        }
    }

    private final int width = 600;
    private final int height = 210;

    private int x;
    private int y;

    private ArrayList<ColorSlider> sliders = new ArrayList<>();

    public ColorChanger() {
        super();
        _instance = this;
        setSize(width, height);
        setBounds(getBounds());
        for (Channel c : Channel.values()) {
            ColorSlider cs = new ColorSlider(c);
            add(cs);
            add(cs.sliderLabel);
            add(cs.valDisplay);
        }
        repaint();
        Color sc = ColorMap.getSelectedColor();
        setValues(sc.getRed(), sc.getGreen(), sc.getBlue());
        for (ColorSlider cs : sliders) {
            cs.updateBounds();
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        setBorder(BorderFactory.createLineBorder(Color.black));
        setBounds(getBounds());
        for (ColorSlider cs : sliders) {
            cs.updateBounds();
        }
        for (Component c : getComponents()) {
            c.repaint();
        }
    }

    public Rectangle getBounds() {
        int x = Application.getAppInstance().getContentPane().getWidth()-getWidth()-margin;
        int y = Application.getAppInstance().getContentPane().getHeight()-getHeight()-margin;
        return new Rectangle(x, y, width, height);
    }

    public static void setValues(int r, int g, int b) {
        for (ColorSlider s : _instance.sliders) {
            switch (s.getChannel()) {
                case RED -> {
                    s.setValue(r);
                }
                case GREEN -> {
                    s.setValue(g);
                }
                case BLUE -> {
                    s.setValue(b);
                }
            }
        }
    }

    public static void update() {
        for (ColorSlider cs : _instance.sliders) {
            cs.updateBounds();
        }
        _instance.repaint();
        Color sc = ColorMap.get
    }

    private class ColorSlider extends JSlider implements ChangeListener {

        private Channel channel;

        private int width;
        private int height;

        private JLabel sliderLabel;
        private JLabel valDisplay;

        public ColorSlider(Channel c) {
            super(SwingConstants.HORIZONTAL, 0, 255, 127);
            channel = c;
            addChangeListener(this);
            sliderLabel = new JLabel(String.valueOf(c.asChar), CENTER) {
                @Override
                public void paint(Graphics g) {
                    g.setColor(getBackground());
                    g.fillRect(0, 0, this.getWidth(), this.getHeight());
                    super.paint(g);
                }
            };
            sliderLabel.setBackground(c.asColor);
            sliderLabel.setForeground(Color.white);

            valDisplay = new JLabel();
            updateBounds();
        }

        public void updateSize() {
            this.height = _instance.height/Channel.values().length;
            this.width = (int) ((_instance.width)*.6);
            setSize(width, height);
            valDisplay.setSize((int) ((_instance.width)*.2), height);
            sliderLabel.setSize((int) ((_instance.width)*.2), height);
        }

        public void updateBounds() {
            updateSize();
            // doing these in the order they'll appear in
            int y = height*(channel.value-1);
            sliderLabel.setBounds(0, y, sliderLabel.getWidth(), sliderLabel.getHeight());
            this.setBounds(sliderLabel.getWidth(), y, this.width, this.height);
            valDisplay.setBounds(sliderLabel.getWidth()+this.getWidth(), y, valDisplay.getWidth(), valDisplay.getHeight());
        }

        @Override
        public void setValue(int value) {
            super.setValue(value);
            valDisplay.setText(String.valueOf(value));
        }

        public void paint(Graphics g) {
            super.paint(g);
            setBorder(BorderFactory.createLineBorder(Color.black));
        }

        public Channel getChannel() {
            return this.channel;
        }

        public JLabel getSliderLabel() {
            return sliderLabel;
        }

        public JLabel getValDisplay() {
            return valDisplay;
        }


        @Override
        public void stateChanged(ChangeEvent e) {
            int cv = this.getValue();
            Color c = ColorMap.getSelectedColor();
            int r = c.getRed();
            int g = c.getGreen();
            int b = c.getBlue();
            switch (channel) {
                case RED -> {
                    r = getValue();
                }
                case GREEN -> {
                    g = getValue();
                }
                case BLUE -> {
                    b = getValue();
                }
            }
            valDisplay.setText(String.valueOf(getValue()));
            ColorMap.Set(ColorMap.getSelectedColorIndex(), new Color(r, g, b));
            Application.getInstanceCanvas().repaint();
        }
    }
}
