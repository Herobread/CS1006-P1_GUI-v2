package game.view;

import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Color;

public class Renderer {
	// Singleton instance
	private static Renderer instance;

	// Other attributes
	private JFrame frame;
	// jframe is strange and has strange sizing
	// maybe it includes border sizes
	// as a result 512*512 turns into 528*548
	private int width = 528;
	private int height = 548;
	private final String missingTexture = "./resources/missing-texture.png";

	// Private constructor to prevent external instantiation
	private Renderer() {
		frame = new JFrame();
		configureWindow();
	}

	private void configureWindow() {
		frame.setLayout(null);
		setDimensions(width, height);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setTitle("Hunt The Wumpus");
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

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
		button.setBounds(x, y, 150, 30);
		button.addActionListener(actionListener);
		frame.add(button);
	}

	public void draw() {
		frame.repaint();
	}

	public void drawTexture(String textureName, int x, int y) {
		String texturePath = "./resources/" + textureName + ".png";
		try {
			final BufferedImage image = ImageIO.read(new File(texturePath));
			drawTexture(textureName, x, y, image.getWidth(), image.getHeight(),1f);
		} catch (IOException e) {
			try {
				final BufferedImage image = ImageIO.read(new File(missingTexture));
				drawTexture("missing-texture", x, y, image.getWidth(), image.getHeight(),1f);
			} catch (IOException e2) {
				System.out.println("Critical error in drawing texture");
			}
		}
	}
	public void drawTexture(String textureName, int x, int y, float opacity) {
		String texturePath = "./resources/" + textureName + ".png";
		try {
			final BufferedImage image = ImageIO.read(new File(texturePath));
			drawTexture(textureName, x, y, image.getWidth(), image.getHeight(),opacity);
		} catch (IOException e) {
			try {
				final BufferedImage image = ImageIO.read(new File(missingTexture));
				drawTexture("missing-texture", x, y, image.getWidth(), image.getHeight(),1f);
			} catch (IOException e2) {
				System.out.println("Critical error in drawing texture");
			}
		}

	}

	public void drawTexture(String textureName, int x, int y, int width, int height) {
		drawTexture(textureName, x, y, width, height, 1f);
	}

	public void drawTexture(String textureName, int x, int y, int width, int height, float opacity) {
		String texturePath = "./resources/" + textureName + ".png";
		try {
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
			Image scaledImage = ImageIO.read(new File(texturePath)).getScaledInstance(width, height, Image.SCALE_DEFAULT);
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
		label.setFont(getFont(size));
		label.setForeground(new Color(255, 255, 255));
		label.setBounds(x, y, 300, size);
		frame.add(label);
	}

	public Font getFont(float size){
		try{
			Font font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("./resources/map-u.png"));
			font.deriveFont(size);
			return font;
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
		catch(FontFormatException e){
			System.out.println("Font error: " + e.getLocalizedMessage());
		}
		return new Font("Consolas", Font.TRUETYPE_FONT, (int)size);
	}

	public void clear() {
		frame.getContentPane().removeAll();
	}

	// Ensure the Singleton pattern by adding a private readObject method
	private Object readResolve() {
		return instance;
	}
}