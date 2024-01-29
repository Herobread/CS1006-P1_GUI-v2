import javax.swing.*;
import utils.Coordinates;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;
import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.awt.Image;

class Main {
	public static void main(String[] args) {
		final int width = 600;
		final int height = 600;
		final int imageSize = 75;
		CaveSystem caves = new CaveSystem(width,height);
		Cave cave = new Cave(false);

		caves.setCave(cave, new Coordinates(200, 200));
		Entity entity = new Entity("Test");
		cave.addEntity(entity);
		JFrame f = new JFrame();
		f.setLayout(null);
		f.setTitle("Hunt The Wumpus");
		f.setSize(width,height);
		f.setResizable(false);
		f.setVisible(true);
		for(Coordinates coords : caves.getConnectionKeySet()){
			List<Coordinates> connected = caves.getCaveConnections(coords);
			Set<Character> directionSet = new HashSet<Character>();
			String imagePath;
			String allDirections = "";
			for (Coordinates targetCoordinates : connected){
				directionSet.add(getDirection(coords.getX(),coords.getY(),targetCoordinates.getX(),targetCoordinates.getY()));
			}
			List<Character> directionList = new ArrayList<Character>(directionSet);
			Collections.sort(directionList);
			for (Character direction : directionList){
				allDirections+=direction;
			}
			imagePath="../resources/cave-" + allDirections + ".png";
			System.out.println(imagePath);
			try{
				BufferedImage img = ImageIO.read(new File(imagePath));
				Image scaledImg = img.getScaledInstance(imageSize, imageSize, Image.SCALE_DEFAULT);
				JLabel imageObject = new JLabel(new ImageIcon(scaledImg));
				imageObject.setBounds(coords.getX(), coords.getY(), imageSize, imageSize);
				f.add(imageObject);
			}
			catch (IOException e){
				System.out.println("Image not found!");
			}
		}
		f.repaint();
	}

	private static char getDirection(int x1, int y1, int x2, int y2){
		int xdiff = x1-x2;
		int ydiff = y1-y2;
		if (Math.abs(xdiff) >= Math.abs(ydiff)){
			if (xdiff>0){
				return 'r';
			}
			else{
				return 'l';
			}
		}
		else{
			if (ydiff>0){
				return 'u';
			}
			else{
				return 'd';
			}
		}
	}

}