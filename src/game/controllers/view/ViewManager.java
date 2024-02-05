package game.controllers.view;

import game.pages.game.GameView;
import game.pages.mainMenu.MainMenuView;

// will be responsible for selecting what is the state of the game:

// main menu
// game
// map
// dialogue

// singleton pattern
public class ViewManager {
	private static ViewManager instance;
	private ViewBase currentWindow;

	private GameView gameView;
	private MainMenuView mainMenuView;

	private ViewManager() {
	}

	public static synchronized ViewManager getInstance() {
		if (instance == null) {
			instance = new ViewManager();
			instance.initializeViews();
		}

		return instance;
	}

	private void initializeViews() {
		gameView = new GameView();
		mainMenuView = new MainMenuView();
	}

	public void displayWindow() {
		currentWindow.run();
	}

	public void setCurrentWindow(ViewBase window) {
		currentWindow = window;
	}

	public void switchToMainMenu() {
		System.out.println("[View manager] main menu");

		setCurrentWindow(mainMenuView);
	}

	public void switchToGameplay() {
		System.out.println("[View manager] switched to gameplay");
		setCurrentWindow(gameView);

	}

	public void displayCurrentWindowInfo() {
		System.out.println("[View manager] current window:");

		if (currentWindow != null) {
			System.out.println(currentWindow.getName());
		}
	}
}
