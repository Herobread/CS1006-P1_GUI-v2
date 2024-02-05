package game;

import game.controllers.view.ViewManager;

class Main {
	public static void main(String[] args) {
		ViewManager viewManager = ViewManager.getInstance();
		viewManager.switchToMainMenu();
		viewManager.displayWindow();
	}
}