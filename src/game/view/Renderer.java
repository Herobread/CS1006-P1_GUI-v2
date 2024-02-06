package game.view;

import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Color;
import java.awt.GraphicsEnvironment;;

public class Renderer extends JFrame{
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
	
	private void configureWindow() {
		setLayout(null);
		setDimensions(width, height);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Hunt The Wumpus");
		setResizable(false);
		getContentPane().setBackground(Color.BLACK);
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
		setSize(width, height);
	}

	// temporary method to handle clicks
	public void drawButtonUnstable(String buttonText, int x, int y, ActionListener actionListener) {
		JButton button = new JButton(buttonText);
		button.setBounds(x, y, 150, 30);
		button.addActionListener(actionListener);
		add(button);
	}

	public void draw() {
		repaint();
		revalidate();
		for (TexturePanel t : TexturePanels){
			t.repaint();
		}
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
				drawTexture(
					"missing-texture", x, y, image.getWidth(), image.getHeight(),1f);
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
			Image scaledImage = ImageIO.read(new File(texturePath)).getScaledInstance(width, height, Image.SCALE_DEFAULT);
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

	@Override
	public void paint(Graphics g) {
		setBackground(Color.BLACK);
		for (TexturePanel t : TexturePanels){
			t.paintComponent(g);
		}
		for (TextPanel t : TextPanels){
			t.paintComponent(g);
		}
	}

	public void drawPicture(Graphics g, Image pic, int x, int y, float opacity) {
		Graphics2D g2d = (Graphics2D) g;
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity);
		g2d.setComposite(ac);
		g2d.drawImage(pic, x, y, null);
	}

	public void drawText(String text, int x, int y, int size, Color colour) {
		TextPanel t = new TextPanel(text, x, y, x, y, size, colour);
		add(t);
		TextPanels.add(t);
	}
	public Font getFont(int size) {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("./resources/font.ttf")));
            return new Font("Pixelify Sans Regular", Font.TRUETYPE_FONT, size);
        } catch (IOException | FontFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return new Font("Consolas", Font.TRUETYPE_FONT, size);
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
}