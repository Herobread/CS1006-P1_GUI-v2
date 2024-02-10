package htw.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import javax.swing.JPanel;

//extend the JPanel Class
public class TextPanel extends JPanel {
    //attributes
    private String text;
    private int x;
    private int y;
    private int size;
    private Color colour;
    private static Font customFont; // same font for all

    //constructor
    public TextPanel(String text, int x, int y, int width, int height, int size, Color colour) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.size = size;
        this.colour = colour;
        //if the customFont hasn't been set yet, set it
        if (customFont == null) {
            customFont = loadFont("./resources/font.ttf");
        }
    }

    //when this component is drawn, get a graphics object and use it to paint the text
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawText(g, text, x, y, size);
    }

    //draw text to the screen with the provided graphics object
    private void drawText(Graphics g, String text, int x, int y, int size) {
        g.setFont(customFont.deriveFont(Font.PLAIN, size));
        g.setColor(colour);
        g.drawString(text, x, y);
    }

    //load a font into the graphics environment
    private Font loadFont(String path) {
        try {
            //store the graphics environment
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //get the font
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File(path));
            //add it to the graphics environment
            ge.registerFont(font);
            return font;
        //if there is an exception thrown while loading the font, catch it
        } catch (IOException | FontFormatException e) {
            //notify the user of the error
            System.err.println("Error loading font: " + e.getMessage());
            // fallback
            return loadSystemFont();
        }
    }

    //load a system font as a fallback
    private Font loadSystemFont() {
        try {
            // Load a default system font fallback
            return new Font("SansSerif", Font.PLAIN, size);
        } catch (Exception e) {
            //if there is an error getting the fallback font, return null and notify the user
            System.err.println("Error loading system font: " + e.getMessage());
            return null;
        }
    }

}
