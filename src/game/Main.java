package game;

import game.controllers.game.logic.initialization.InitializeGame;
import game.controllers.view.ViewManager;

class Main {
	public static void main(String[] args) {
		InitializeGame.initialize();

		ViewManager viewManager = ViewManager.getInstance();
		viewManager.switchToMainMenu();
	}
}
