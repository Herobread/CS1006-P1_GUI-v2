package htw.view.pages.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import htw.controllers.dialogue.DialogueManager;
import htw.controllers.game.GameStateManager;
import htw.controllers.game.GameStatus;
import htw.controllers.game.logic.VictoryChecker;
import htw.controllers.game.logic.initialization.GameInitializer;
import htw.controllers.game.logic.movement.playerMovement.PlayerMoveLogic;
import htw.controllers.game.logic.playerActions.PlayerShootAction;
import htw.controllers.game.logic.playerActions.PlayerSensesAction;
import htw.controllers.view.ViewManager;
import htw.model.caves.CaveSystem;
import htw.model.caves.Decoration;
import htw.model.entities.Player;
import htw.utils.Coordinates;
import htw.utils.Direction;
import htw.view.pages.ViewBase;
import htw.view.renderer.Renderer;

public class GameView extends ViewBase {
	private htw.controllers.game.GameStateManager gameStateManager = GameStateManager.getInstance();
	private ViewManager viewManager = ViewManager.getInstance();
	private Renderer renderer = Renderer.getInstance();
	private DialogueManager dialogueManager = DialogueManager.getInstance();

	public GameView() {
		super("Gameplay");
	}

	private ActionListener mapButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			viewManager.switchToMap();
		}
	};

	private ActionListener shootUpButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			PlayerShootAction.shoot(Direction.UP);

			update();
		}
	};

	private ActionListener walkUpButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			PlayerMoveLogic.handleMove(Direction.UP);

			update();
		}
	};

	private ActionListener shootDownButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			PlayerShootAction.shoot(Direction.DOWN);

			update();
		}
	};

	private ActionListener walkDownButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			PlayerMoveLogic.handleMove(Direction.DOWN);

			update();
		}
	};

	private ActionListener shootLeftButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			PlayerShootAction.shoot(Direction.LEFT);

			update();
		}
	};

	private ActionListener walkLeftButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			PlayerMoveLogic.handleMove(Direction.LEFT);

			update();
		}
	};

	private ActionListener shootRightButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			PlayerShootAction.shoot(Direction.RIGHT);

			update();
		}
	};

	private ActionListener walkRightButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			PlayerMoveLogic.handleMove(Direction.RIGHT);

			update();
		}
	};

	@Override
	public void renderView() {
		renderer.clear();

		CaveSystem caves = gameStateManager.getCaves();
		Player player = gameStateManager.getPlayer();
		Coordinates playerCoordinates = player.getCoordinates();
		String currentCaveTexture = "cave-" + caves.getConnectionsString(playerCoordinates);
		String senses = PlayerSensesAction.checkNeighbours(caves, playerCoordinates);

		renderer.drawTexture(currentCaveTexture, 0, 0, 512, 512);

		// player shadow
		renderer.drawTexture("shadow", 176, 273, 128, 64, 0.5f);

		ArrayList<Decoration> decorations = caves.getCave(playerCoordinates).getDecorations();

		for (Decoration decoration : decorations) {
			renderer.drawTexture(decoration.getTextureName(), decoration.getX(), decoration.getY(),
					decoration.getWidth(), decoration.getHeight());
		}

		// buttons up:
		renderer.drawButton("shoot", 176, 8, 72, 72, shootUpButtonActionListener);

		renderer.drawButton("walk-up", 256, 8, 72, 72, walkUpButtonActionListener);

		// buttons down:
		renderer.drawButton("shoot", 176, 432, 72, 72, shootDownButtonActionListener);

		renderer.drawButton("walk-down", 256, 432, 72, 72, walkDownButtonActionListener);

		// buttons left:
		renderer.drawButton("shoot", 8, 176, 72, 72, shootLeftButtonActionListener);

		renderer.drawButton("walk-left", 8, 256, 72, 72, walkLeftButtonActionListener);

		// buttons right:
		renderer.drawButton("shoot", 432, 176, 72, 72, shootRightButtonActionListener);

		renderer.drawButton("walk-right", 432, 256, 72, 72, walkRightButtonActionListener);

		// info:
		if (senses.length() != 0) {
			String hazardsDialogueTexture = "dialogue-" + senses;
			renderer.drawTexture(hazardsDialogueTexture, 208, 144, 144, 64);
		}

		// map button
		renderer.drawButton("map", 8, 8, 72, 72, mapButtonActionListener);

		renderer.drawTexture("player", 192, 192, 128, 128);

		renderer.draw();
	}

	@Override
	public void update() {
		if (!dialogueManager.isEmpty()) {
			viewManager.switchToDialogue();
			return;
		}

		VictoryChecker.checkVictory();

		GameStatus status = gameStateManager.getGameStatus();

		if (status != GameStatus.PLAYING) {
			viewManager.switchToScores();
			GameInitializer.initialize();
			return;
		}

		renderView();
	}

	@Override
	public void run() {
		renderView();
		update();
	}
}
