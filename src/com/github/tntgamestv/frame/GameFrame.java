/**
 * 
 */
package com.github.tntgamestv.frame;

import java.util.ArrayList;
import java.util.List;

import com.github.tntgamestv.Constants;
import com.github.tntgamestv.console.Out;
import com.github.tntgamestv.game.GameEngine;
import com.github.tntgamestv.game.GameSettingsManager;
import com.github.tntgamestv.game.RenderEngine;
import com.github.tntgamestv.game.ball.Ball;
import com.github.tntgamestv.game.ball.Ball.Type;
import com.github.tntgamestv.game.render.PathTile;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Translate;

/**
 * @author TnTGamesTV Project: School8 Date: 23-05-2017
 */
public class GameFrame extends Frame {

	private GameEngine			gameEngine;
	private RenderEngine		renderEngine;

	/* Handles settings for this game (round[s]) */
	private GameSettingsManager	gameSettings;

	private List<Node>			toRemove	= new ArrayList<>();
	private List<Node>			toAdd		= new ArrayList<>();

	public Sphere				ball;
	public boolean				left = false;

	/**
	 * @param mainFrame
	 * @param lastFrame
	 */
	public GameFrame(MainFrame mainFrame, Frame lastFrame) {
		super(mainFrame, lastFrame);
		this.isCameraRotationBlocked = true;

		this.mainFrame.cameraObject.rx.setAngle(45);
		this.mainFrame.cameraObject.ry.setAngle(45);

		gameEngine = new GameEngine();
		renderEngine = new RenderEngine(this);

		gameSettings = new GameSettingsManager();
	}

	public void handleKeyboard(KeyEvent e) {
		super.handleKeyboard(e);

		if (e.getCode() == KeyCode.SPACE) {
			if(left){
				left = false;
			}else{
				left = true;
			}
		}

		// TMP
		if (e.getCode() == KeyCode.A) {
			if (gameSettings.getSetting("game.running")) {
				gameSettings.setSetting("game.running", false);
			} else {
				gameSettings.setSetting("game.running", true);
			}
		} else if (e.getCode() == KeyCode.S) {
			for (Node node : renderEngine.getRenderingObjects().getChildren()) {
				Out.renderEngine("Node: (X) => " + (node.getLayoutX() + node.getTranslateX()));
			}
		} else if (e.getCode() == KeyCode.D) {
			PathTile tile = renderEngine.getPathTiles().get(0);
			int x = tile.getNextX();
			int z = tile.getNextZ();

			Sphere ball = new Sphere(5);
			ball.setMaterial(Ball.getMaterial(Type.RED));
			ball.getTransforms().add(new Translate(x, Constants.PATH_OBJECT_SIZE / 2 + 5, z));

			this.ball = ball;

			this.children.getChildren().add(ball);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.github.tntgamestv.frame.Frame#create()
	 */
	@Override
	public void create() {

		// Start rendering
		this.renderEngine.loop();

		// Settings
		this.gameSettings.init();

		// Generate test tile
		// PathTile pathTile = new PathTile(0, 0);
		// this.renderEngine.registerRenderingObject(pathTile.getRenderObject());
		//
		// PathTile pathTile2 = new PathTile(0, 25);
		// this.renderEngine.registerRenderingObject(pathTile2.getRenderObject());
	}

	@Override
	public void loop() {
		for (Node n : toRemove) {
			this.renderEngine.getRenderingObjects().getChildren().remove(n);
		}

		toRemove.clear();

		for (Node n : toAdd) {
			this.renderEngine.getRenderingObjects().getChildren().add(n);
		}

		toAdd.clear();
	}

	public void addChildren(Node children) {
		this.children.getChildren().add(children);
	}

	public void addToRemove(Node node) {
		this.toRemove.add(node);
	}

	public void addToAdd(Node node) {
		this.toAdd.add(node);
	}

	/**
	 * @return the gameSettings
	 */
	public GameSettingsManager getGameSettings() {
		return gameSettings;
	}

	/**
	 * @return the gameEngine
	 */
	public GameEngine getGameEngine() {
		return gameEngine;
	}
}
