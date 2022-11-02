package org.csi3370.ui;

import org.csi3370.Application;
import org.csi3370.ColorMap;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import static javax.swing.SwingConstants.*;

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

    private HashMap<Channel, JSlider> sliders = new HashMap<>();

    private ColorChanger() {
        super();
        _instance = this;
        setLayout(new BoxLayout(this, VERTICAL));
        setSize(new Dimension(600, 200));
        for (final Channel c : Channel.values()) {
            Rectangle bounds = new Rectangle();

            final JLabel valDisplay = new JLabel("", CENTER);

            final JSlider slider = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 127) {

                public final Channel channel = c;

                @Override
                public void setValue(int value) {
                    super.setValue(value);
                    valDisplay.setText(String.valueOf(value));
                }
            };
            sliders.put(c, slider);


            JLabel sliderLabel = new JLabel(String.valueOf(c.asChar), CENTER) {
                public void paint(Graphics g) {
                    g.setColor(getBackground());
                    g.fillRect(0, 0, this.getWidth(), this.getHeight());
                    super.paint(g);
                }
            };
            JPanel container = new JPanel();
            container.add(sliderLabel);
            container.add(slider);
            container.add(valDisplay);
            add(container);

            valDisplay.setLabelFor(slider);

            sliderLabel.setBackground(c.asColor);
            sliderLabel.setForeground(Color.white);
            sliderLabel.setLabelFor(slider);

            slider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    valDisplay.setText(String.valueOf(slider.getValue()));

                    ColorMap.set(ColorMap.getSelectedColorIndex(), new Color(
                            sliders.get(Channel.RED).getValue(),
                            sliders.get(Channel.GREEN).getValue(),
                            sliders.get(Channel.BLUE).getValue()));
                }
            });

            setBorder(BorderFactory.createLineBorder(Color.black));
            // label
            bounds.width = width / 5;
            bounds.height = height / Channel.values().length;
            bounds.x = 0;
            bounds.y = (bounds.height) * (c.value - 1);
            sliderLabel.setBounds(bounds);

            // ColorSlider
            bounds.x = bounds.width;
            bounds.width = (int) ((3.0 / 5.0) * width);
            slider.setSize(bounds.width, bounds.height);

            // value display
            bounds.x = (int) ((4.0/5.0)*width);
            bounds.width = width / 5;
            valDisplay.setSize(bounds.width, bounds.height);
        }

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        paintChildren(g);
        setBounds(getBounds());
    }

    public Rectangle getBounds() {
        int x = Application.getAppInstance().getWidth() - (width+10);
        int y = Application.getAppInstance().getHeight() - (height+10);
        return new Rectangle(x, y, width, height);
    }

    public static void setValues(int r, int g, int b) {
        _instance.sliders.get(Channel.RED).setValue(r);
        _instance.sliders.get(Channel.GREEN).setValue(g);
        _instance.sliders.get(Channel.BLUE).setValue(b);
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
            _instance = new ColorChanger();
        }
        return _instance;
    }
}