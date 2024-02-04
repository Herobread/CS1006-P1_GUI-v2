package game;

import game.controllers.view.ViewManager;
import game.view.Renderer;

class Main {
	public static void main(String[] args) {
		Renderer renderer = Renderer.getInstance();
		renderer.setDimensions(512, 512);

		ViewManager viewManager = ViewManager.getInstance();

		viewManager.switchToMainMenu();

		viewManager.displayWindow();
	}
}