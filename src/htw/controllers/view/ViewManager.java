package htw.controllers.view;

import htw.view.pages.ViewBase;
import htw.view.pages.dialogue.DialogueView;
import htw.view.pages.game.GameView;
import htw.view.pages.mainMenu.MainMenuView;
import htw.view.pages.map.MapView;
import htw.view.pages.scores.ScoresView;

/**
 * Manages the different views of the application and controls the transition
 * between them.
 * This class follows the singleton pattern.
 */
public class ViewManager {
	private static ViewManager instance;
	private ViewBase currentWindow;

	private GameView gameView;
	private MainMenuView mainMenuView;
	private DialogueView dialogueView;
	private MapView mapView;
	private ScoresView scoresView;

	private ViewManager() {
	}

	/**
	 * Retrieves the singleton instance of the ViewManager.
	 *
	 * @return The ViewManager instance.
	 */
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
		dialogueView = new DialogueView();
		mapView = new MapView();
		scoresView = new ScoresView();
	}

	/**
	 * Displays the current window.
	 */
	public void displayWindow() {
		currentWindow.run();
	}

	private void setCurrentWindow(ViewBase window) {
		currentWindow = window;
	}

	/**
	 * Switches to the main menu view.
	 */
	public void switchToMainMenu() {
		setCurrentWindow(mainMenuView);
		displayWindow();
	}

	/**
	 * Switches to the gameplay view.
	 */
	public void switchToGameplay() {
		setCurrentWindow(gameView);
		displayWindow();
	}

	/**
	 * Switches to the dialogue view.
	 */
	public void switchToDialogue() {
		setCurrentWindow(dialogueView);
		displayWindow();
	}

	/**
	 * Switches to the map view.
	 */
	public void switchToMap() {
		setCurrentWindow(mapView);
		displayWindow();
	}

	/**
	 * Switches to the scores view.
	 */
	public void switchToScores() {
		setCurrentWindow(scoresView);
		displayWindow();
	}

	/**
	 * Displays information about the current window.
	 */
	public void displayCurrentWindowInfo() {
		System.out.println("[View manager] current window:");
		if (currentWindow != null) {
			System.out.println(currentWindow.getName());
		}
	}
}
