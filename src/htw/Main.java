package htw;

import htw.controllers.game.logic.initialization.InitializeGame;
import htw.controllers.view.ViewManager;

class Main {
	public static void main(String[] args) {
		InitializeGame.initialize();

		ViewManager viewManager = ViewManager.getInstance();
		viewManager.switchToMainMenu();
	}
}
