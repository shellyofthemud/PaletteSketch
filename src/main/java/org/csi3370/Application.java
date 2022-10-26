package org.csi3370;


import org.csi3370.tools.CanvasTool;
import org.csi3370.ui.ColorChanger;
import org.csi3370.ui.PaletteListDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// TODO clean up the junk
public class Application extends JFrame {

    // class singleton
    private static Application _instance;

    private PaletteCanvas pCanvas = new PaletteCanvas();

    public CanvasTool activeTool;

    private Application() {
        super("Palette Sketcher");
        _instance = this;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);
        add(pCanvas);
        add(PaletteListDisplay.getInstance());
        add(ColorChanger.getInstance());

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                Application.getAppInstance().repaint();
                ColorChanger.update();
            }
        });

        activeTool = CanvasTool.getTool();

        setVisible(true);
        setExtendedState(getExtendedState() | Frame.MAXIMIZED_BOTH);
        for (Component c : getComponents()) {
            c.setVisible(true);
        }
    }

    public void paint(Graphics g) {
        setBackground(Color.WHITE);
        super.paint(g);
        for (Component c : this.getContentPane().getComponents()) {
            c.repaint();
        }

    }

    public static void main(String[] args) {
        String[] appletArgs = new String[] { "org.csi3370.Application" };
        // PApplet.main(appletArgs);
        Application app = new Application();
        app.setVisible(true);
    }

    public static void setActiveToolColor(Color c) {
        _instance.activeTool.setToolColor(c);
    }
    // convenience methods for passing/converting data
    public static Application getAppInstance() {
        return _instance;
    }

    public static PaletteCanvas getInstanceCanvas() {
        return _instance.pCanvas;
    }

    private class MouseHandler extends MouseAdapter {


        @Override
        public void mouseClicked(MouseEvent e) {
            activeTool.mouseClicked(e);
            Application.getAppInstance().repaint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            // activeTool.mouseDragged(e);
            Application.getAppInstance().repaint();
        }
    }


}