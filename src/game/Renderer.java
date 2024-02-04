package game;

import java.awt.Image;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Font;
import java.awt.Color;

public class Renderer extends JFrame{
	// 4. create a method that gets CaveSystem and then based on that info
	// draws caves (use new getConnectionsString(Coordinates))
	

	private int width = 500;
	private int height = 500;
	private final String missingTexture = "./resources/missing-texture.png";
	

	// initialise jframe in constructor
	public Renderer(int width, int height) {
		super("Hunt The Wumpus");
		this.width = width;
		this.height = height;
		configureWindow();
	}

	public int getFrameHeight() {
		return height;
	}

	public int getFrameWidth() {
		return width;
	}

	// configure important settings of the frame
	private void configureWindow() {
		setLayout(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Hunt The Wumpus");
		setSize(width, height);
		setResizable(false);
		setVisible(true);
	}

	// refresh the frame with new elements
	public void draw() {
		repaint();
	}

	// draw an image using the image's existing size
	public void drawTexture(String textureName, int x, int y) {
		String texturePath = "./resources/" + textureName + ".png";
		try {
			final BufferedImage image = ImageIO.read(new File(texturePath));
			drawTexture(textureName, x, y, image.getWidth(), image.getHeight(),1f);
		} catch (IOException e) {
			try {
				final BufferedImage image = ImageIO.read(new File(missingTexture));
				drawTexture("missing-texture", x, y, image.getWidth(), image.getHeight());
			} catch (IOException e2) {
				System.out.println("Critical error in drawing texture");
			}
		}
	}

	public void drawTexture(String textureName, int x, int y, float opacity){
		String texturePath = "./resources/" + textureName + ".png";
		try {
			final BufferedImage image = ImageIO.read(new File(texturePath));
			drawTexture(textureName, x, y, image.getWidth(), image.getHeight(),opacity);
		} catch (IOException e) {
			try {
				final BufferedImage image = ImageIO.read(new File(missingTexture));
				drawTexture("missing-texture", x, y, image.getWidth(), image.getHeight());
			} catch (IOException e2) {
				System.out.println("Critical error in drawing texture");
			}
		}
	}
	
	
	// draw an image using the specified width and height
	public void drawTexture(String textureName, int x, int y, int width, int height) {
		drawTexture(textureName, x, y, width, height, 1f);
	}
	// draw an image using the specified width and height
	public void drawTexture(String textureName, int x, int y, int width, int height, float opacity) {
		String texturePath = "./resources/" + textureName + ".png";
		try {
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
			image = ImageIO.read(new File(texturePath));
			//AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity);
			Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
			JPanel p = new JPanel();
			Graphics g = getGraphics();
			paint(p,g, scaledImage,x,y,opacity);
			add(p);
		} catch (IOException e) {
			try {
				final BufferedImage image = ImageIO.read(new File(missingTexture));
				drawTexture("missing-texture", x, y, image.getWidth(), image.getHeight());
			} catch (IOException e2) {
				System.out.println("Critical error in drawing texture");
			}
		}
	}
	public void drawText(String text, int x, int y, int size) {
		JLabel label = new JLabel(text);
		Font font = new Font("DejaVu Sans Mono", Font.PLAIN, size);
		label.setFont(font);
		label.setForeground(new Color(255, 255, 255));
		label.setBounds(x, y, 300, size);
		add(label);
	}
	public void paint(JPanel p, Graphics g, Image scaledImage, int x, int y,float opacity){
		p.paint(g);
		drawPicture(g, scaledImage, x,y,opacity);
	}

	public void drawPicture(Graphics g, Image pic, int x, int y,float opacity){
		Graphics2D g2d = (Graphics2D) g;
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity);
		g2d.setComposite(ac);
		g2d.drawImage(pic, x, y,null);
		
	}

	public void clear() {
		super.getContentPane().removeAll();
	}
}