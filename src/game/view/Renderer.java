package game.view;

import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Font;
import java.awt.Color;

public class Renderer {
	// Singleton instance
	private static Renderer instance;

	// Other attributes
	private JFrame frame;
	private int width = 500;
	private int height = 500;
	private final String missingTexture = "./resources/missing-texture.png";

	// Private constructor to prevent external instantiation
	private Renderer() {
		frame = new JFrame();
		configureWindow();
	}

	// Public method to get the instance
	public static Renderer getInstance() {
		if (instance == null) {
			instance = new Renderer();
		}
		return instance;
	}

	public int getFrameHeight() {
		return height;
	}

	public int getFrameWidth() {
		return width;
	}

	// Method to set width and height
	public void setDimensions(int width, int height) {
		this.width = width;
		this.height = height;
		frame.setSize(width, height);
	}

	// temporary method to handle clicks
	public void drawButtonUnstable(String buttonText, int x, int y, ActionListener actionListener) {
		JButton button = new JButton(buttonText);
		button.setBounds(x, y, 150, 30); // You can adjust the size as needed
		button.addActionListener(actionListener); // Attach the ActionListener
		frame.add(button);
	}

	private void configureWindow() {
		frame.setLayout(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setTitle("Hunt The Wumpus");
		frame.setResizable(false);
		frame.setVisible(true);
	}

	public void draw() {
		frame.repaint();
	}

	public void drawTexture(String textureName, int x, int y) {
		String texturePath = "./resources/" + textureName + ".png";
		try {
			final BufferedImage image = ImageIO.read(new File(texturePath));
			drawTexture(textureName, x, y, image.getWidth(), image.getHeight());
		} catch (IOException e) {
			try {
				final BufferedImage image = ImageIO.read(new File(missingTexture));
				drawTexture("missing-texture", x, y, image.getWidth(), image.getHeight());
			} catch (IOException e2) {
				System.out.println("Critical error in drawing texture");
			}
		}
	}

	public void drawTexture(String textureName, int x, int y, int width, int height) {
		String texturePath = "./resources/" + textureName + ".png";
		try {
			final BufferedImage image = ImageIO.read(new File(texturePath));
			Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
			JLabel imageObject = new JLabel(new ImageIcon(scaledImage));
			imageObject.setBounds(x, y, width, height);
			frame.add(imageObject);
		} catch (IOException e) {
			drawTexture("missing-texture", x, y, width, height);
		}
	}

	public void drawTexture(String textureName, int x, int y, int width, int height, float opacity) {
		String texturePath = "./resources/" + textureName + ".png";
		try {
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
			image = ImageIO.read(new File(texturePath));
			// AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
			// opacity);
			Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
			JLabel imageObject = new JLabel(new ImageIcon(scaledImage));
			imageObject.setBounds(x, y, width, height);
			frame.add(imageObject);
			JPanel p = new JPanel();
			Graphics g = frame.getGraphics();
			paint(p, g, scaledImage, x, y, opacity);
			frame.add(p);
		} catch (IOException e) {
			drawTexture("missing-texture", x, y, width, height);
			try {
				final BufferedImage image = ImageIO.read(new File(missingTexture));
				drawTexture("missing-texture", x, y, image.getWidth(), image.getHeight());
			} catch (IOException e2) {
				System.out.println("Critical error in drawing texture");
			}
		}
	}

	public void paint(JPanel p, Graphics g, Image scaledImage, int x, int y, float opacity) {
		p.paint(g);
		drawPicture(g, scaledImage, x, y, opacity);
	}

	public void drawPicture(Graphics g, Image pic, int x, int y, float opacity) {
		Graphics2D g2d = (Graphics2D) g;
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity);
		g2d.setComposite(ac);
		g2d.drawImage(pic, x, y, null);

	}

	public void drawText(String text, int x, int y, int size) {
		JLabel label = new JLabel(text);
		Font font = new Font("DejaVu Sans Mono", Font.PLAIN, size);
		label.setFont(font);
		label.setForeground(new Color(255, 255, 255));
		label.setBounds(x, y, 300, size);
		frame.add(label);
	}

	public void clear() {
		frame.getContentPane().removeAll();
	}

	// Ensure the Singleton pattern by adding a private readObject method
	private Object readResolve() {
		return instance;
	}
}
