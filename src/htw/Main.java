package htw;

import htw.controllers.game.logic.initialization.GameInitializer;
import htw.controllers.view.ViewManager;

class Main {
	public static void main(String[] args) {
		GameInitializer.initialize();

		ViewManager viewManager = ViewManager.getInstance();
		viewManager.switchToMainMenu();
	}
}
