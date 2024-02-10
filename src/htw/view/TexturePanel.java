package htw.view;

import java.awt.Image;
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

//extend JPanel
public class TexturePanel extends JPanel {
    //attributes
    private Image scaledImage;
    private int x;
    private int y;
    private float opacity;

    //constructor
    public TexturePanel(Image scaledImage, int x, int y, int width, int height, float opacity) {
        this.scaledImage = scaledImage;
        this.x = x;
        this.y = y;
        this.opacity = opacity;
    }

    @Override
    public void paintComponent(Graphics g) {
        //get a copy of the graphics object to draw on
        Graphics newGraphics = g.create();
        //paint it to the JPanel
        super.paintComponent(newGraphics);
        //draw a picture on it
        drawPicture(newGraphics, scaledImage, x, y, opacity);
    }

    //draw a picture on a graphics object provided
    private void drawPicture(Graphics g, Image pic, int x, int y, float opacity) {
        //if the pic is provided
        if (pic != null) {
            //make a graphics2d object as sa copy of the graphics object
            Graphics2D g2d = (Graphics2D) g;
            //set it's opacity correctly
            AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity);
            g2d.setComposite(ac);
            //draw the image
            g2d.drawImage(pic, x, y, null);
        } else {
        //if no picture was provided, notify the user
            System.err.println("No Picture Found!");
        }
    }
}