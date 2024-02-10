package htw.controllers.game;

import htw.model.caves.CaveSystem;
import htw.model.entities.Player;
import htw.model.map.ExploredMap;

/**
 * Manages the state of the game, including player, caves, map, and game status.
 * This class follows the singleton pattern.
 */
public class GameStateManager {
	private static GameStateManager instance;
	private CaveSystem caves;
	private Player player;
	private ExploredMap exploredMap;
	private GameStatus gameStatus = GameStatus.PLAYING;
	private int amountOfBats;
	private int amountOfWumpuses;
	private int amountOfPits;
	private int amountOfTreasures;
	private int amountOfArrows;

	// Private constructor to prevent instantiation from outside
	private GameStateManager() {
	}

	/**
	 * Retrieves the singleton instance of the GameStateManager.
	 *
	 * @return The GameStateManager instance.
	 */
	public static synchronized GameStateManager getInstance() {
		if (instance == null) {
			instance = new GameStateManager();
		}
		return instance;
	}

	// Getters and setters for various game state properties...

	/**
	 * Retrieves the cave system.
	 *
	 * @return The cave system.
	 */
	public CaveSystem getCaves() {
		return caves;
	}

	/**
	 * Sets the cave system.
	 *
	 * @param caves The cave system to set.
	 */
	public void setCaves(CaveSystem caves) {
		this.caves = caves;
	}

	/**
	 * Retrieves the player.
	 *
	 * @return The player.
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Sets the player.
	 *
	 * @param player The player to set.
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * Retrieves the explored map.
	 *
	 * @return The explored map.
	 */
	public ExploredMap getExploredMap() {
		return exploredMap;
	}

	/**
	 * Sets the explored map.
	 *
	 * @param exploredMap The explored map to set.
	 */
	public void setExploredMap(ExploredMap exploredMap) {
		this.exploredMap = exploredMap;
	}

	/**
	 * Retrieves the game status.
	 *
	 * @return The game status.
	 */
	public GameStatus getGameStatus() {
		return gameStatus;
	}

	/**
	 * Sets the game status.
	 *
	 * @param gameStatus The game status to set.
	 */
	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	/**
	 * Retrieves the total number of bats.
	 *
	 * @return The total number of bats.
	 */
	public int getTotalAmountOfBats() {
		return amountOfBats;
	}

	/**
	 * Sets the total number of bats.
	 *
	 * @param amountOfBats The total number of bats to set.
	 */
	public void setAmountOfBats(int amountOfBats) {
		this.amountOfBats = amountOfBats;
	}

	/**
	 * Retrieves the total number of wumpuses.
	 *
	 * @return The total number of wumpuses.
	 */
	public int getTotalAmountOfWumpuses() {
		return amountOfWumpuses;
	}

	/**
	 * Sets the total number of wumpuses.
	 *
	 * @param amountOfWumpuses The total number of wumpuses to set.
	 */
	public void setAmountOfWumpuses(int amountOfWumpuses) {
		this.amountOfWumpuses = amountOfWumpuses;
	}

	/**
	 * Retrieves the total number of pits.
	 *
	 * @return The total number of pits.
	 */
	public int getTotalAmountOfPits() {
		return amountOfPits;
	}

	/**
	 * Sets the total number of pits.
	 *
	 * @param amountOfPits The total number of pits to set.
	 */
	public void setAmountOfPits(int amountOfPits) {
		this.amountOfPits = amountOfPits;
	}

	/**
	 * Retrieves the total number of treasures.
	 *
	 * @return The total number of treasures.
	 */
	public int getTotalAmountOfTreasures() {
		return amountOfTreasures;
	}

	/**
	 * Sets the total number of treasures.
	 *
	 * @param amountOfTreasures The total number of treasures to set.
	 */
	public void setAmountOfTreasures(int amountOfTreasures) {
		this.amountOfTreasures = amountOfTreasures;
	}

	/**
	 * Retrieves the total number of arrows.
	 *
	 * @return The total number of arrows.
	 */
	public int getTotalAmountOfArrows() {
		return amountOfArrows;
	}

	/**
	 * Sets the total number of arrows.
	 *
	 * @param amountOfArrows The total number of arrows to set.
	 */
	public void setAmountOfArrows(int amountOfArrows) {
		this.amountOfArrows = amountOfArrows;
	}
}
