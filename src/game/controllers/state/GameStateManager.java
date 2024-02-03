package game.controllers.state;

// singleton
public class GameStateManager {
	private static GameStateManager instance;
	private String inputAction = "";
	private int someNumber = 1;

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

	public int getSomeNumber() {
		return someNumber;
	}

	public void setSomeNumber(int someNumber) {
		this.someNumber = someNumber;
	}

	public static void setInstance(GameStateManager instance) {
		GameStateManager.instance = instance;
	}

}
