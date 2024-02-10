package htw.view.renderer;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import javax.swing.JButton;

//extend the JButton class
public class CustomButton extends JButton {
    // attributes
    private int x;
    private int y;
    private int width;
    private int height;

    // constructor
    public CustomButton(ActionListener actionListener, int x, int y, int width, int height) {
        super();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        addActionListener(actionListener);
        setBounds(x, y, width, height);
    }

    // get the bounds of the button
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    // ensure that the button isn't painted
    @Override
    public void paint(Graphics g) {
    }
}