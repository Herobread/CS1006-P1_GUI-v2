package game.controllers.view;

import game.view.Renderer;

// will be responsible for selecting what is the state of the game:

// main menu
// game
// map
// dialogue

// singleton pattern
public class ViewManager {
	private static ViewManager instance;
	private ViewBase currentWindow;

	private GameView gameView = new GameView("Game");
	private MainMenuView mainMenuView = new MainMenuView();

	private ViewManager() {

		mainMenuView.init();
	}

	public static synchronized ViewManager getInstance() {
		if (instance == null) {
			instance = new ViewManager();
		}

		return instance;
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
			currentWindow.run();
		}
	}
}
