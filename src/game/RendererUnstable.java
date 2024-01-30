package game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class RendererUnstable {
	private JFrame frame;

	private int width = 600;
	private int height = 500;

	public RendererUnstable() {
		frame = new JFrame();

		configureWindow();
	}

	private void configureWindow() {
		frame.setLayout(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setTitle("Hunt The Wumpus");
		frame.setSize(width, height);
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
			JLabel imageObject = new JLabel(new ImageIcon(image));
			imageObject.setBounds(x, y, image.getWidth(), image.getHeight());
			frame.add(imageObject);
		} catch (IOException e) {
		}
	}
}
