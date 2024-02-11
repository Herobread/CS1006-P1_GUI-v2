package htw.view;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;

import javax.swing.JButton;

//extend the JButton class
public class CustomButton extends JButton{
    private int x;
    private int y;
    private int width;
    private int height;
    private Image img;
    //constructor
    public CustomButton(ActionListener actionListener,Image img, int x, int y, int width, int height, float opacity){
        super();
        addActionListener(actionListener);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = img;
        setBounds(x, y, width, height);
        setMaximumSize(new Dimension(width, height));
        setIcon(new ImageIcon(img));
    }
    

    //draw the image on the button
    @Override
    public void paintComponent(Graphics g){
        //get a copy of the graphics object to draw o
        //paint it to the JPanel
        
        //draw a picture on it
        //drawPicture(newGraphics, scaledImage, x, y, opacity);
        setBounds(x, y, width, height);
        setMaximumSize(new Dimension(width, height));
        g.drawImage(img, x, y, width,height,null);
        //super.paintComponent(g);
    }
    
}