import java.awt.Color;
import javax.swing.*;
import utils.Coordinates;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;
class Main {
	public static void main(String[] args) {
		final int width = 600;
		final int height = 600;
		CaveSystem caves = new CaveSystem(width,height);
		Cave cave = new Cave(false);

		caves.setCave(cave, new Coordinates(0, 0));
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
			imagePath="../cave-" + allDirections + ".png";
		}
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