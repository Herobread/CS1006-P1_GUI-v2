package game.controllers.state;

import game.model.CaveSystem;

// singleton
public class GameStateManager {
	private static GameStateManager instance;
	private String inputAction = "";
	private CaveSystem caves;

	// Private constructor to prevent instantiation from outside
	private GameStateManager() {
	}

	public static GameStateManager getInstance() {
		if (instance == null) {
			instance = new GameStateManager();
		}
		return instance;
	}

	public String getInputAction() {
		return inputAction;
	}

	public void setInputAction(String inputAction) {
		this.inputAction = inputAction;
	}

	public CaveSystem getCaves() {
		return caves;
	}

	public void setCaves(CaveSystem caves) {
		this.caves = caves;
	}
}
