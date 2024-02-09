package htw.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

public class TextPanel extends JPanel {
    private String text;
    private int x;
    private int y;
    private int size;
    private Color colour;
    private static Font customFont; // same font for all

    public TextPanel(String text, int x, int y, int width, int height, int size, Color colour) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.size = size;
        this.colour = colour;
        if (customFont == null) {
            customFont = loadFont("./resources/font.ttf");
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawText(g, text, x, y, size);
    }

    private void drawText(Graphics g, String text, int x, int y, int size) {
        g.setFont(customFont.deriveFont(Font.PLAIN, size));
        g.setColor(colour);
        g.drawString(text, x, y);
    }

    private Font loadFont(String path) {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File(path));
            ge.registerFont(font);
            return font;
        } catch (IOException | FontFormatException e) {
            System.err.println("Error loading font: " + e.getMessage());
            // fallback
            return loadSystemFont();
        }
    }

    private Font loadSystemFont() {
        try {
            // Load a default system font fallback
            return new Font("SansSerif", Font.PLAIN, size);
        } catch (Exception e) {
            System.err.println("Error loading system font: " + e.getMessage());
            return null;
        }
    }

}
