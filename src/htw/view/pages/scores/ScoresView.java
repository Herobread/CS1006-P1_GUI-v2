package htw.view.pages.scores;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import htw.controllers.game.GameStateManager;
import htw.controllers.game.GameStatus;
import htw.controllers.view.ViewManager;
import htw.model.entities.Player;
import htw.view.pages.ViewBase;
import htw.view.renderer.Renderer;

public class ScoresView extends ViewBase {
	private GameStateManager gameStateManager = GameStateManager.getInstance();
	private ViewManager viewManager = ViewManager.getInstance();
	private Renderer renderer = Renderer.getInstance();

	public ScoresView() {
		super("Scores");
	}

	// button action listeneres
	private ActionListener nextButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			viewManager.switchToMainMenu();
		}
	};

	// rendering
	@Override
	public void renderView() {
		renderer.clear();

		// background image
		renderer.drawTexture("cave-", 0, 0, 512, 512);

		GameStatus gameStatus = gameStateManager.getGameStatus();
		Player player = gameStateManager.getPlayer();

		// comment on user's result
		if (gameStatus == GameStatus.FAIL) {
			renderer.drawText("Better luck next time!", 81, 140, 24, Color.WHITE);
		}

		if (gameStatus == GameStatus.WIN) {
			renderer.drawText("Congratulations! You won!", 81, 140, 24, Color.WHITE);
		}

		// diplay game stats
		// kills
		final int totalWumpuses = gameStateManager.getTotalAmountOfWumpuses();
		final int killedWumpuses = player.getWumpusesShot();
		renderer.drawText("Wumpus: " + killedWumpuses + "/" + totalWumpuses, 81, 212, 24, Color.WHITE);

		final int totalBats = gameStateManager.getTotalAmountOfBats();
		final int killedBats = player.getBatsShot();
		renderer.drawText("Bat: " + killedBats + "/" + totalBats, 81, 241, 24, Color.WHITE);

		// other
		renderer.drawText("Gold acquired: " + player.getGold(), 81, 290, 24, Color.WHITE);

		renderer.drawTexture("logo", 148, 30, 216, 90);

		renderer.drawButton("next", 432, 432, 72, 72, nextButtonActionListener);

		renderer.draw();
	}

	@Override
	public void update() {
	}

	@Override
	public void run() {
		update();
		renderView();
	}
}
