package htw.controllers.game;

import htw.model.CaveSystem;
import htw.model.entities.Player;

// singleton
public class GameStateManager {
	private static GameStateManager instance;
	private String inputAction = "";
	private CaveSystem caves;
	private Player player;

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

	@Deprecated
	public static void setInstance(GameStateManager instance) {
		GameStateManager.instance = instance;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
