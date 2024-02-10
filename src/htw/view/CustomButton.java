package htw.view;
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import javax.swing.JButton;
public class CustomButton extends JButton{
    private int x;
    private int y;
    private int width;
    private int height;
    public CustomButton(ActionListener actionListener, int x, int y, int width, int height){
        super();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        addActionListener(actionListener);
        setBounds(x, y, width, height);
        //setBorderPainted(false);
        //setOpaque(false);
        //setContentAreaFilled(false);
    }
    public Rectangle getBounds(){
        return new Rectangle(x, y, width,height);
    }

    @Override
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0f));
        g2d.dispose();
    }
}