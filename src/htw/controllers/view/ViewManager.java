package htw.controllers.view;

import htw.view.pages.dialogue.DialogueView;
import htw.view.pages.game.GameView;
import htw.view.pages.mainMenu.MainMenuView;
import htw.view.pages.map.MapView;
import htw.view.pages.scores.ScoresView;

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
	private DialogueView dialogueView;
	private MapView mapView;
	private ScoresView scoresView;

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
		dialogueView = new DialogueView();
		mapView = new MapView();
		scoresView = new ScoresView();
	}

	public void displayWindow() {
		currentWindow.run();
	}

	public void setCurrentWindow(ViewBase window) {
		currentWindow = window;
	}

	public void switchToMainMenu() {
		setCurrentWindow(mainMenuView);
		displayWindow();
	}

	public void switchToGameplay() {
		setCurrentWindow(gameView);
		displayWindow();
	}

	public void switchToDialogue() {
		setCurrentWindow(dialogueView);
		displayWindow();
	}

	public void switchToMap() {
		setCurrentWindow(mapView);
		displayWindow();
	}

	public void switchToScores() {
		setCurrentWindow(scoresView);
		displayWindow();
	}

	public void displayCurrentWindowInfo() {
		System.out.println("[View manager] current window:");

		if (currentWindow != null) {
			System.out.println(currentWindow.getName());
		}
	}
}
