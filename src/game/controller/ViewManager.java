package game.controller;

// will be responsible for selecting what is the state of the game:

// main menu
// game
// map
// dialogue

// singleton pattern
public class ViewManager {
	private static ViewManager instance;
	private ViewBase currentWindow;

	private ViewManager() {
		// private constructor to enforce singleton pattern
	}

	public static ViewManager getInstance() {
		if (instance == null) {
			instance = new ViewManager();
		}
		return instance;
	}

	public void setCurrentWindow(ViewBase window) {
		currentWindow = window;
	}

	public void switchToMainMenu() {
		System.out.println("main menu");
		// setCurrentWindow(new MainMenuWindow());
	}

	public void switchToGameplay() {
		System.out.println("gameplay");
		// setCurrentWindow(new GameplayWindow());
	}

	public void displayCurrentWindow() {
		if (currentWindow != null) {
			currentWindow.run();
		}
	}
}
