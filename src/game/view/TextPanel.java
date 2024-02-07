package game.view;

import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Color;
import javax.swing.JPanel;

public class TextPanel extends JPanel {
    private String text;
    private int x;
    private int y;
    private int size;
    private Color colour;

    public TextPanel(String text, int x, int y, int width, int height, int size, Color colour) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.size = size;
        this.colour = colour;
        // super.setForeground(new Color(0,0,0,opacity));
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics newGraphics = g.create();
        super.paintComponent(newGraphics);
        drawText(newGraphics, text, x, y, size);
    }

    private void drawText(Graphics g, String text, int x, int y, int size) {
        g.setFont(getFont(size));
        g.setColor(colour);
        g.drawString(text, x, y);
    }

    private Font getFont(int size) {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("./resources/font.ttf")));
            return new Font("Pixelify Sans Regular", Font.TRUETYPE_FONT, size);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (FontFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return new Font("Consolas", Font.TRUETYPE_FONT, size);
    }
}