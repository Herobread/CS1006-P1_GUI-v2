package game.view;

//imports
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Color;

//extend JFrame
public class Renderer extends JFrame {
	// Singleton instance
	private static Renderer instance;
	// Other attributes
	private List<TexturePanel> TexturePanels = new ArrayList<TexturePanel>();
	private List<TextPanel> TextPanels = new ArrayList<TextPanel>();
	// jframe is strange and has strange sizing
	// maybe it includes border sizes
	// as a result 512*512 turns into 528*548
	private int width = 528;
	private int height = 548;
	private final String missingTexture = "./resources/missing-texture.png";

	// Private constructor to prevent external instantiation
	private Renderer() {
		configureWindow();
	}

	// configure attributes of the JFrame
	private void configureWindow() {
		setLayout(null);
		setDimensions(width, height);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Hunt The Wumpus");
		setResizable(false);
		setBackground(Color.BLACK);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	// Public method to get the instance
	public static Renderer getInstance() {
		if (instance == null) {
			instance = new Renderer();
		}
		return instance;
	}

	// return the height of the frame
	public int getFrameHeight() {
		return height;
	}

	// return the width of the frame
	public int getFrameWidth() {
		return width;
	}

	// Method to set width and height
	public void setDimensions(int width, int height) {
		this.width = width + getInsets().left + getInsets().right;
		this.height = height + getInsets().top;
		setSize(width, height);
	}

	// temporary method to handle clicks
	public void drawButtonUnstable(String buttonText, int x, int y, ActionListener actionListener) {
		y += getInsets().top;
		if (validCoordinates(x, y)) {
			JButton button = new JButton(buttonText);
			button.setBounds(x, y, 150, 30);
			button.addActionListener(actionListener);
			add(button);
		}
	}

	public void draw() {
		repaint();
		revalidate();
		for (TexturePanel t : TexturePanels) {
			t.repaint();
		}
	}

	public void drawTexture(String textureName, int x, int y) {
		String texturePath = "./resources/" + textureName + ".png";
		try {

			final BufferedImage image = ImageIO.read(new File(texturePath));
			drawTexture(textureName, x, y, image.getWidth(), image.getHeight(), 1f);
		} catch (IOException e) {
			try {
				final BufferedImage image = ImageIO.read(new File(missingTexture));
				drawTexture("missing-texture", x, y, image.getWidth(), image.getHeight(), 1f);
			} catch (IOException e2) {
				System.out.println("Critical error in drawing texture");
			}
		}
	}

	public void drawTexture(String textureName, int x, int y, float opacity) {
		String texturePath = "./resources/" + textureName + ".png";
		try {
			final BufferedImage image = ImageIO.read(new File(texturePath));
			drawTexture(textureName, x, y, image.getWidth(), image.getHeight(), opacity);
		} catch (IOException e) {
			try {
				final BufferedImage image = ImageIO.read(new File(missingTexture));
				drawTexture(
						"missing-texture", x, y, image.getWidth(), image.getHeight(), 1f);
			} catch (IOException e2) {
				System.out.println("Critical error in drawing texture");
			}
		}
	}

	public void drawTexture(String textureName, int x, int y, int width, int height) {
		drawTexture(textureName, x, y, width, height, 1f);
	}

	public void drawTexture(String textureName, int x, int y, int width, int height, float opacity) {
		y += getInsets().top;
		if (validCoordinates(x, y)) {
			String texturePath = "./resources/" + textureName + ".png";
			try {
				Image scaledImage = ImageIO.read(new File(texturePath)).getScaledInstance(width, height,
						Image.SCALE_DEFAULT);
				TexturePanel t = new TexturePanel(scaledImage, x, y, width, height, opacity);
				TexturePanels.add(t);
				t.repaint();
				add(t);
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
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		setBackground(Color.BLACK);
		for (TexturePanel t : TexturePanels) {
			t.paintComponent(g);
		}
		for (TextPanel t : TextPanels) {
			t.paintComponent(g);
		}
	}

	public void drawText(String text, int x, int y, int size, Color colour) {
		y += getInsets().top * 2;
		if (validCoordinates(x, y)) {
			TextPanel t = new TextPanel(text, x, y, x, y, size, colour);
			add(t);
			TextPanels.add(t);
		}
	}

	public void clear() {
		getContentPane().removeAll();
		TexturePanels.clear();
		TextPanels.clear();
	}

	// Ensure the Singleton pattern by adding a private readObject method
	private Object readResolve() {
		return instance;
	}

	private boolean validCoordinates(int x, int y) {
		if (x < 0 || y < 0) {
			System.err.println("Drawing starts at negative coordinates!");
			return false;
		}
		if (x > width || y > height) {
			System.err.println("Drawing starts outside frame!");
			return false;
		}
		return true;
	}
}