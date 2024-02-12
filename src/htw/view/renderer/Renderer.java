package htw.view.renderer;

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
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.FlowLayout;

//extend JFrame
public class Renderer extends JFrame {
	// Singleton instance
	private static Renderer instance;
	// Other attributes
	private List<TexturePanel> TexturePanels = new ArrayList<TexturePanel>();
	private List<TextPanel> TextPanels = new ArrayList<TextPanel>();
	private List<CustomButton> CustomButtons = new ArrayList<CustomButton>();
	private int width = 512;
	private int height = 550; // not 512 because of border on top
	// Store the filepath of the texture to be displayed when no texture is found at
	// other locations
	private final String MISSING_TEXTURE = "./resources/missing-texture.png";

	// Private constructor to prevent external instantiation
	private Renderer() {
		configureWindow();
	}

	// configure attributes of the JFrame
	private void configureWindow() {
		// set background colour
		getContentPane().setBackground(Color.BLACK);
		setLayout(new FlowLayout());
		setDimensions(width, height);
		// ensure that when the program is closed the process halts
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Hunt The Wumpus");
		setResizable(false);
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

	// please, don't remove this, until stable click area is stable
	public void drawClickAreaUnstable(int x, int y, int width, int height,
			ActionListener actionListener) {
		JButton button = new JButton("Click Me");
		button.setBounds(x, y, width, height);
		button.addActionListener(actionListener);

		getContentPane().add(button);
	}

	// draw an invisible area with an action listener
	public void drawButton(String textureName, int x, int y, int width, int height, ActionListener actionListener) {
		x -= 8; // for some reason coordinates for buttons and textures are different
		// if the image will be drawn inside the frame
		if (validCoordinates(x, y)) {
			// create new CustomButton object and add it to the frame, ensuring it's at the
			// top so clicks are handled
			try {
				String texturePath = "./resources/" + textureName + ".png";
				Image scaledImage = ImageIO.read(new File(texturePath)).getScaledInstance(width, height,
						Image.SCALE_DEFAULT);
				CustomButton button = new CustomButton(actionListener, scaledImage, x, y, width, height);
				CustomButtons.add(button);
				getContentPane().add(button);
				button.repaint();
				setComponentZOrder(button, 0);
			} catch (IOException e) {
				System.err.println("Problem drawing button");
			}
		}
	}

	// refresh the content on the screen
	public void draw() {
		SwingUtilities.invokeLater(() -> {
			repaint();
			revalidate();
		});
	}

	// draw a texture using the width and height of the original image at the
	// specified x and y co-ordinates
	public void drawTexture(String textureName, int x, int y) {
		// call the drawTexture method with the same textureName and co-ordinates as
		// this, with opacity 1
		drawTexture(textureName, x, y, 1f);
	}

	// draw a texture given it's name, it's destination co-ordinates and it's
	// intended opacity
	public void drawTexture(String textureName, int x, int y, float opacity) {
		// store the relative location of the image in the file system
		String texturePath = "./resources/" + textureName + ".png";
		try {
			// store the image as a bufferedImage to be able to get it's width and height
			// later
			final BufferedImage image = ImageIO.read(new File(texturePath));
			// call the drawTexture method with the texture name, co-ordinates, size and
			// opacity
			drawTexture(textureName, x, y, image.getWidth(), image.getHeight(), opacity);
			// if the file could not be found
		} catch (IOException e) {
			drawMissingImage(x, y);
		}
	}

	// if the drawTexture method is called with only the texture name, size and
	// location, then call drawTexture with those parameters
	// and opacity 1
	public void drawTexture(String textureName, int x, int y, int width, int height) {
		drawTexture(textureName, x, y, width, height, 1f);
	}

	// draw a texture given it's name, intended co-ordinates, size and opactity
	public void drawTexture(String textureName, int x, int y, int width, int height, float opacity) {
		// adjust the y position by the size of the header
		y += getInsets().top;
		// if the co-ordinates given are within the frame
		if (validCoordinates(x, y)) {
			// store the path to the texture
			String texturePath = "./resources/" + textureName + ".png";
			try {
				// get an image object of the file provided
				Image scaledImage = ImageIO.read(new File(texturePath));
				// scale the image
				scaledImage = scaledImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);
				// create a texturePanel object with the right parameters and add it to the
				// frame
				TexturePanel t = new TexturePanel(scaledImage, x, y, width, height, opacity);
				TexturePanels.add(t);
				t.repaint();
				add(t);
				// if the image can't be found, draw the missing texture instead
			} catch (IOException e) {
				drawMissingImage(x, y, width, height);
			}
		}
	}

	private void drawMissingImage(int x, int y) {
		try {
			// get an image object of the file provided
			BufferedImage scaledImage = ImageIO.read(new File(MISSING_TEXTURE));
			// create a texturePanel object with the right parameters and add it to the
			// frame and list of TexturePanels
			TexturePanel t = new TexturePanel(scaledImage, x, y, scaledImage.getWidth(), scaledImage.getHeight(), 1f);
			TexturePanels.add(t);
			t.repaint();
			add(t);
		}
		// if the missing texture image couldn't be found, write a message to the
		// command line to notify the user of a critical error.
		catch (IOException e) {
			System.err.println("Critical error: missing texture not found");
		}
	}

	// draw a missing image with the height and width provided
	private void drawMissingImage(int x, int y, int width, int height) {
		try {
			// get an image object of the file provided
			Image scaledImage = ImageIO.read(new File(MISSING_TEXTURE));
			// scale the image
			scaledImage = scaledImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);
			// create a texturePanel object with the right parameters and add it to the
			// frame
			TexturePanel t = new TexturePanel(scaledImage, x, y, width, height, 1f);
			TexturePanels.add(t);
			t.repaint();
			add(t);
		}
		// if the missing texture image couldn't be found, write a message to the
		// command line to notify the user of a critical error.
		catch (IOException e) {
			System.err.println("Critical error");
		}
	}

	// Define what happens when a redraw happens, ensuring that all components are
	// present after the redraw.
	@Override
	public void paint(Graphics g) {
		// call the paint method on the superclass (JFrame)
		super.paint(g);
		// add all of the TexturePanels, TextPanels and ButtonPanels back in
		for (TexturePanel t : TexturePanels) {
			t.paintComponent(g);
		}
		for (TextPanel t : TextPanels) {
			t.paintComponent(g);
		}
		for (CustomButton b : CustomButtons) {
			b.paintComponent(g);
		}
	}

	// draw text to the screen
	public void drawText(String text, int x, int y, int size, Color colour) {
		// JFrames also handle the coordinates of text slightly differently, so adjust
		// the position of the y coordinate by 2
		y += getInsets().top * 2;
		// if the x and y coordinates provided are valid
		if (validCoordinates(x, y)) {
			// create a new textPanel and add it to the frame and list of TextPanels
			TextPanel t = new TextPanel(text, x, y, x, y, size, colour);
			add(t);
			TextPanels.add(t);
		}
	}

	// clear everything from the screen
	public void clear() {
		getContentPane().removeAll();
		for (CustomButton button : CustomButtons) {
			button.setVisible(false);
		}
		CustomButtons.clear();
		TexturePanels.clear();
		TextPanels.clear();
	}

	// Ensure the Singleton pattern by adding a private readObject method
	private Object readResolve() {
		return instance;
	}

	// determine if the co-ordinates provided are valid
	private boolean validCoordinates(int x, int y) {
		// if the x or y co-ordinate provided are negative, return false and write to
		// system error
		if (x < 0 || y < 0) {
			System.err.println("Drawing starts at negative coordinates!");
			return false;
		}
		// if the x or y co-ordinate provided are outside the frame, return false and
		// write to system error
		if (x > width || y > height) {
			System.err.println("Drawing starts outside frame!");
			return false;
		}
		// if the co-ordinates pass both of these checks, return true
		return true;
	}
}