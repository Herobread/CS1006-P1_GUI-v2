package htw.controllers.game;

import htw.model.caves.CaveSystem;
import htw.model.entities.Player;
import htw.model.map.ExploredMap;

public class GameStateManager {
	private static GameStateManager instance;
	private String inputAction = "";
	private CaveSystem caves;
	private Player player;
	private ExploredMap exploredMap;
	private boolean isEndGame = false;
	private GameStatus gameStatus = GameStatus.PLAYING;
	private int amountOfBats;
	private int amountOfWumpuses;
	private int amountOfPits;
	private int amountOfTreasures;
	private int amountOfArrows;

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

	public boolean isEndGame() {
		return isEndGame;
	}

	public void setEndGame(boolean isEndGame) {
		this.isEndGame = isEndGame;
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

	public ExploredMap getExploredMap() {
		return exploredMap;
	}

	public void setExploredMap(ExploredMap exploredMap) {
		this.exploredMap = exploredMap;
	}

	public GameStatus getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	public int getTotalAmountOfBats() {
		return amountOfBats;
	}

	public void setAmountOfBats(int amountOfBats) {
		this.amountOfBats = amountOfBats;
	}

	public int getTotalAmountOfWumpuses() {
		return amountOfWumpuses;
	}

	public void setAmountOfWumpuses(int amountOfWumpuses) {
		this.amountOfWumpuses = amountOfWumpuses;
	}

	public int getTotalAmountOfPits() {
		return amountOfPits;
	}

	public void setAmountOfPits(int amountOfPits) {
		this.amountOfPits = amountOfPits;
	}

	public int getTotalAmountOfTreasures() {
		return amountOfTreasures;
	}

	public void setAmountOfTreasures(int amountOfTreasures) {
		this.amountOfTreasures = amountOfTreasures;
	}

	public int getTotalAmountOfArrows() {
		return amountOfArrows;
	}

	public void setAmountOfArrows(int amountOfArrows) {
		this.amountOfArrows = amountOfArrows;
	}
}
