import java.util.Random;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import javax.swing.*;
class Main {
	public static void main(String[] args) {
		final int width = 600;
		final int height = 600;
		CaveSystem caves = new CaveSystem(width,height);
		Cave cave = new Cave(false);
		Entity entity = new Entity("Test");
		cave.addEntity(entity);
		JFrame f = new JFrame();
		f.setLayout(null);
		f.setTitle("Hunt The Wumpus");
		f.setSize(width,height);
		f.setResizable(false);
		f.setVisible(true);
		for(Cave[] cx : caves.getCaves()){

		}
	}
}