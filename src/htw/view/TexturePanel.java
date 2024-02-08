package htw.view;

import java.awt.Image;
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class TexturePanel extends JPanel {
    private Image scaledImage;
    private int x;
    private int y;
    private float opacity;

    public TexturePanel(Image scaledImage, int x, int y, int width, int height, float opacity) {
        this.scaledImage = scaledImage;
        this.x = x;
        this.y = y;
        this.opacity = opacity;
        // super.setForeground(new Color(0,0,0,opacity));
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics newGraphics = g.create();
        super.paintComponent(newGraphics);
        drawPicture(newGraphics, scaledImage, x, y, opacity);
    }

    private void drawPicture(Graphics g, Image pic, int x, int y, float opacity) {
        if (pic != null) {
            Graphics2D g2d = (Graphics2D) g;
            AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity);
            g2d.setComposite(ac);
            g2d.drawImage(pic, x, y, null);
            // g.drawImage(pic, x, y, null);
        } else {
            System.err.println("No Picture Found!");
        }
    }
}