package game;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;

public class Renderer {
	// 4. create a method that gets CaveSystem and then based on that info
	// draws caves (use new getConnectionsString(Coordinates))
	//store jframe (and other attributes) here
	private JFrame frame;

	private int width = 500;
	private int height = 500;

	//initialise jframe in constructor
	public Renderer() {
		frame = new JFrame();
		configureWindow();
	}

	public int getFrameHeight(){
		return height;
	}
	public int getFrameWidth(){
		return width;
	}
	//configure important settings of the frame
	private void configureWindow() {
		frame.setLayout(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setTitle("Hunt The Wumpus");
		frame.setSize(width, height);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	//refresh the frame with new elements
	public void draw() {
		frame.repaint();
	}

	//draw an image using the image's existing size
	public void drawTexture(String textureName, int x, int y) {
		String texturePath = "./resources/" + textureName + ".png";
		try{
			final BufferedImage image = ImageIO.read(new File(texturePath));
			drawTexture(textureName,x,y, image.getWidth(), image.getHeight());
		}
		catch (IOException e){
			System.out.println("There was an error in drawing an image");
		}

	}
	//draw an image using the specified width and height
	public void drawTexture(String textureName, int x, int y, int width, int height) {
		String texturePath = "./resources/" + textureName + ".png";
		try {
			final BufferedImage image = ImageIO.read(new File(texturePath));
			//JLabel imageObject = new JLabel(new ImageIcon(image).getImage().getScaledInstance(width, height, Image.SCALE_REPLICATE));
			Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
			JLabel imageObject = new JLabel(new ImageIcon(scaledImage));
			imageObject.setBounds(x, y, width, height);
			frame.add(imageObject);
		} catch (IOException e) {
		}
	}
	public void drawText(String text, int x, int y, int size){
		JLabel label = new JLabel(text);
		Font font = new Font("DejaVu Sans Mono", Font.PLAIN, size);
		label.setFont(font);
		label.setForeground(new Color(255,255,255));
		label.setBounds(x, y, 300, size);
		frame.add(label);
	}

	public void clear(){
		frame.getContentPane().removeAll();
	}
}