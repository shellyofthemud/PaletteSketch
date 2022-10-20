package org.csi3370.ui;

import org.csi3370.Application;
import org.csi3370.ColorMap;
import org.w3c.dom.css.Rect;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;

public class ColorChanger extends JPanel {

    private static int margin = 10;
    private static ColorChanger _instance;
    private static enum Channel {
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

    private ArrayList<ColorSlider> sliders = new ArrayList<>();

    private ColorChanger() {
        super();
        setLayout(null);
        _instance = this;
        for (Channel c : Channel.values()) {
            ColorSlider cs = new ColorSlider(c);
            sliders.add(cs);
            add(cs);
            add(cs.sliderLabel);
            add(cs.valDisplay);
        }
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        setBorder(BorderFactory.createLineBorder(Color.black));
        setBounds(getBounds());
        for (ColorSlider cs : sliders) {
            cs.paint(g);
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

    public static void setValues(Color c) {
        int r = c.getRed();
        int g = c.getGreen();
        int b = c.getBlue();
        setValues(r, g, b);
    }

    public static void update() {
        setValues(ColorMap.getSelectedColor());
        _instance.repaint();
    }

    public static ColorChanger getInstance() {
        if (_instance == null) {
            return new ColorChanger();
        } else {
            return _instance;
        }
    }

    private static class ColorSlider extends JSlider implements ChangeListener {

        private Channel channel;

        private int width;
        private static int height;

        private JLabel sliderLabel;
        private JLabel valDisplay;

        public ColorSlider(Channel c) {
            super(SwingConstants.HORIZONTAL, 0, 255, 127);
            channel = c;
            addChangeListener(this);
            sliderLabel = new JLabel(String.valueOf(c.asChar), CENTER) {
                public void paint(Graphics g) {
                    g.setColor(getBackground());
                    g.fillRect(0, 0, this.getWidth(), this.getHeight());
                    super.paint(g);
                }
            };
            sliderLabel.setBackground(c.asColor);
            sliderLabel.setForeground(Color.white);
            sliderLabel.setLabelFor(this);

            valDisplay = new JLabel();
            valDisplay.setLabelFor(this);


            repaint();
        }

        @Override
        public void setValue(int value) {
            super.setValue(value);
            valDisplay.setText(String.valueOf(value));
        }


        @Override
        public void paint(Graphics g) {
           super.paint(g);
           setBorder(BorderFactory.createLineBorder(Color.black));
           // label
           int width = (int) ((getParent().getWidth()/5));
           int height = getParent().getHeight()/Channel.values().length;
           int x = 0;
           int y = (getParent().getHeight()/Channel.values().length)*(channel.value-1);
           sliderLabel.setBounds(x, y, width, height);

           // ColorSlider
           x = (int) (getParent().getWidth()/5.0);
           width = (int) ((getParent().getWidth()/5)*3);
           setBounds(x, y, width, height);

            // value display
            x = (int) ((getParent().getWidth()/5.0)*4);
            width = (int) ((getParent().getWidth()/5));
            valDisplay.setBounds(x, y, width, height);
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
            ColorMap.set(ColorMap.getSelectedColorIndex(), new Color(r, g, b));
        }
    }
}
