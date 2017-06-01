/**
 * 
 */
package com.github.tntgamestv.frame;

import com.github.tntgamestv.console.ConsoleHandler;
import com.github.tntgamestv.shop.Shop;

import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author TnTGamesTV Project: School8 Date: 16-05-2017
 */
public class MainFrame extends Application {

	final Group				root				= new Group();
	final Group				frameChildren		= new Group();

	final RenderObject		world				= new RenderObject();
	final PerspectiveCamera	camera				= new PerspectiveCamera(true);
	final RenderObject		cameraObject		= new RenderObject();
	final RenderObject		cameraObject2		= new RenderObject();
	final RenderObject		cameraObject3		= new RenderObject();
	final double			cameraDistance		= 450;

	public int				selectedItem		= 0;

	private Timeline		timeline;
	boolean					timelinePlaying		= false;
	double					ONE_FRAME			= 1.0 / 24.0;
	double					DELTA_MULTIPLIER	= 200.0;
	double					CONTROL_MULTIPLIER	= 0.1;
	double					SHIFT_MULTIPLIER	= 0.1;
	double					ALT_MULTIPLIER		= 0.5;
	double					mousePosX;
	double					mousePosY;
	double					mouseOldX;
	double					mouseOldY;
	double					mouseDeltaX;
	double					mouseDeltaY;

	Scene					scene;

	Frame					currentFrame		= null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage stage) throws Exception {
		System.out.println("start");

		buildScene();
		buildCamera();

		Scene scene = new Scene(root, 1024, 768, true);
		scene.setFill(Color.LIGHTGREY);

		this.scene = scene;
		
		handleMouse(scene, world);
		handleKeyboard(scene, world);

		stage.setTitle("ZigZag");
		stage.setScene(scene);
		stage.show();
		scene.setCamera(camera);

		root.getChildren().addAll(frameChildren);

		// Prefs
		Shop.init();
		
		//Timer
		Task task = new MainFrame.Task();
		task.start();

		// Console
		ConsoleHandler.init(this, world);

		MainMenuFrame mainMenu = new MainMenuFrame(this, world);
		mainMenu.isCameraRotationBlocked = false;
		setNewFrame(mainMenu);
	}

	public class Task extends AnimationTimer{
		
		@Override
		public void handle(long arg0){
			if(currentFrame != null){
				currentFrame.loop();
			}
		}
	}
	
	public void setNewFrame(Frame frame) {
		if (currentFrame != null) {
			frame.hide();
			frameChildren.getChildren().clear();
		}

		currentFrame = frame;

		frame.create();
		frameChildren.getChildren().addAll(frame.children);

		frame.show();
	}

	public static void main(String[] args) {
		System.setProperty("prism.dirtyopts", "false");
		launch(args);
	}

	private void buildScene() {
		root.getChildren().add(world);
	}

	private void buildCamera() {
		root.getChildren().add(cameraObject);
		cameraObject.getChildren().add(cameraObject2);
		cameraObject2.getChildren().add(cameraObject3);
		cameraObject3.getChildren().add(camera);
		cameraObject3.setRotateZ(180.0);
		camera.setNearClip(0.1);
		camera.setFarClip(10000.0);
		camera.setTranslateZ(-cameraDistance);
		cameraObject.ry.setAngle(0);
		cameraObject.rx.setAngle(0);
	}

	private void handleMouse(Scene scene, final Node root) {
		scene.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				if (currentFrame != null) currentFrame.handleMouse(me);

				mousePosX = me.getSceneX();
				mousePosY = me.getSceneY();
				mouseOldX = me.getSceneX();
				mouseOldY = me.getSceneY();
			}
		});
		scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				if (currentFrame != null) currentFrame.handleMouse(me);

				if (currentFrame != null) {
					if (!currentFrame.isCameraRotationBlocked) {
						mouseOldX = mousePosX;
						mouseOldY = mousePosY;
						mousePosX = me.getSceneX();
						mousePosY = me.getSceneY();
						mouseDeltaX = (mousePosX - mouseOldX);
						mouseDeltaY = (mousePosY - mouseOldY);

						double modifier = 1.0;
						double modifierFactor = 0.1;

						if (me.isControlDown()) {
							modifier = 0.1;
						}
						if (me.isShiftDown()) {
							modifier = 10.0;
						}
						if (me.isPrimaryButtonDown()) {
							cameraObject.ry.setAngle(
									cameraObject.ry.getAngle() - mouseDeltaX * modifierFactor * modifier * 2.0); // +
							cameraObject.rx.setAngle(
									cameraObject.rx.getAngle() + mouseDeltaY * modifierFactor * modifier * 2.0); // -
						} else if (me.isSecondaryButtonDown()) {
							double z = camera.getTranslateZ();
							double newZ = z + mouseDeltaX * modifierFactor * modifier;
							camera.setTranslateZ(newZ);
						} else if (me.isMiddleButtonDown()) {
							cameraObject2.t
									.setX(cameraObject2.t.getX() + mouseDeltaX * modifierFactor * modifier * 0.3); // -
							cameraObject2.t
									.setY(cameraObject2.t.getY() + mouseDeltaY * modifierFactor * modifier * 0.3); // -
						}
					}
				}
			}
		});
	}

	private void handleKeyboard(Scene scene, final Node root) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				if (currentFrame != null) currentFrame.handleKeyboard(e);

				if (e.getCode() == KeyCode.A && e.isShiftDown()) {
					RotateTransition rt = new RotateTransition(Duration.millis(1500), camera);
					rt.setByAngle(180);
					rt.setInterpolator(Interpolator.EASE_BOTH);
					rt.play();
				}
			}
		});
	}

	/**
	 * @return the currentFrame
	 */
	public Frame getCurrentFrame() {
		return currentFrame;
	}
}
