/**
 * 
 */
package com.github.tntgamestv.frame;

import org.fxyz.shapes.primitives.Text3DMesh;

import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.effect.*;

/**
 * @author TnTGamesTV Project: School8 Date: 15-05-2017
 */
public class MainMenu extends Application {

	final Group				root				= new Group();
	final Group				menuGroup			= new Group();

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

	private void buildScene() {
		root.getChildren().add(world);
	}

	private void buildMenu() {

		final PhongMaterial redMaterial = new PhongMaterial();
		redMaterial.setDiffuseColor(Color.DARKRED);
		redMaterial.setSpecularColor(Color.RED);

		final PhongMaterial greenMaterial = new PhongMaterial();
		greenMaterial.setDiffuseColor(Color.DARKGREEN);
		greenMaterial.setSpecularColor(Color.GREEN);

		final PhongMaterial blueMaterial = new PhongMaterial();
		blueMaterial.setDiffuseColor(Color.DARKBLUE);
		blueMaterial.setSpecularColor(Color.BLUE);

		final Text3DMesh lblPlay = new Text3DMesh("Play");
		lblPlay.setTranslateZ(10);
		lblPlay.setFontSize(10);

		final Text3DMesh lblShop = new Text3DMesh("Shop");
		lblShop.setTranslateZ(10);
		lblShop.setFontSize(10);

		final Text3DMesh lblOptions = new Text3DMesh("Options");
		lblOptions.setTranslateZ(10);
		lblOptions.setFontSize(10);

		final Box btnPlay = new Box(205, 50, 10);
		btnPlay.setMaterial(redMaterial);

		final Box btnOptions = new Box(100, 50, 10);
		btnOptions.setMaterial(blueMaterial);

		final Box btnShop = new Box(100, 50, 10);
		btnShop.setMaterial(greenMaterial);

		btnPlay.setTranslateY(27.5);
		btnOptions.setTranslateY(-27.5);
		btnShop.setTranslateY(-27.5);
		btnOptions.setTranslateX(52.5);
		btnShop.setTranslateX(-52.5);

		lblPlay.getTransforms().add(new Rotate(180, 0, 0, 0, Rotate.X_AXIS));
		lblShop.getTransforms().add(new Rotate(180, 0, 0, 0, Rotate.X_AXIS));
		lblOptions.getTransforms().add(new Rotate(180, 0, 0, 0, Rotate.X_AXIS));

		menuGroup.getChildren().addAll(btnPlay, btnOptions, btnShop, lblPlay, lblShop, lblOptions);
		world.getChildren().addAll(menuGroup);
	}

	private void handleKeyboard(Scene scene, final Node root) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.DOWN) {

					if (selectedItem == 0) {
						// Nothing selected
						selectMenuItem(1);
					} else if (selectedItem == 1) {
						// Nothing selected
						selectMenuItem(2);
					} else if (selectedItem == 2) {
						// Nothing selected
						selectMenuItem(3);
					} else if (selectedItem == 3) {
						// Nothing selected
						selectMenuItem(1);
					}
				} else if (event.getCode() == KeyCode.UP) {
					if (selectedItem == 0) {
						// Nothing selected
						selectMenuItem(3);
					} else if (selectedItem == 1) {
						// Nothing selected
						selectMenuItem(3);
					} else if (selectedItem == 2) {
						// Nothing selected
						selectMenuItem(1);
					} else if (selectedItem == 3) {
						// Nothing selected
						selectMenuItem(2);
					}
				} else if(event.getCode() == KeyCode.ENTER){
					if(selectedItem >= 1 && selectedItem <= 3){
						//open menu
						menuTransition();
					}
				}
			}
		});
	}

	private void menuTransition(){
		Box btnPlay = (Box) menuGroup.getChildren().get(0);
		Box btnOptions = (Box) menuGroup.getChildren().get(1);
		Box btnShop = (Box) menuGroup.getChildren().get(2);

		TranslateTransition tt1 = new TranslateTransition(Duration.millis(1500), btnPlay);
		tt1.setToY(1000);
		tt1.setToZ(0);
		tt1.setInterpolator(Interpolator.EASE_BOTH);
		tt1.play();

		TranslateTransition tt2 = new TranslateTransition(Duration.millis(1500), btnOptions);
		tt2.setToX(1000);
		tt2.setToZ(0);
		tt2.setInterpolator(Interpolator.EASE_BOTH);
		tt2.play();

		TranslateTransition tt3 = new TranslateTransition(Duration.millis(1500), btnShop);
		tt3.setToX(-1000);
		tt3.setToZ(0);
		tt3.setInterpolator(Interpolator.EASE_BOTH);
		tt3.play();
	}
	
	private void selectMenuItem(int item) {
		Box oldItem = getMenuItemBox(selectedItem);
		Box newItem = getMenuItemBox(item);

		TranslateTransition tt1 = new TranslateTransition(Duration.millis(500), oldItem);
		tt1.setToZ(0);
		tt1.setInterpolator(Interpolator.EASE_BOTH);
		tt1.play();

		TranslateTransition tt2 = new TranslateTransition(Duration.millis(500), newItem);
		tt2.setToZ(20);
		tt2.setInterpolator(Interpolator.EASE_BOTH);
		tt2.play();

		Glow glow = new Glow();
		glow.setLevel(0.9);
		newItem.setEffect(glow);
		
		selectedItem = item;
	}

	private Box getMenuItemBox(int item) {
		Box box = null;
		switch (item) {
		default:
			break;
		case 1:
			// 1 => btnPlay
			box = (Box) menuGroup.getChildren().get(0);
			break;
		case 3:
			// 2 => Options
			box = (Box) menuGroup.getChildren().get(1);
			break;
		case 2:
			// 3 => Shop
			box = (Box) menuGroup.getChildren().get(2);
			break;
		}

		return box;
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
		cameraObject.ry.setAngle(320.0);
		cameraObject.rx.setAngle(40);
	}

	private void handleMouse(Scene scene, final Node root) {
		scene.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				mousePosX = me.getSceneX();
				mousePosY = me.getSceneY();
				mouseOldX = me.getSceneX();
				mouseOldY = me.getSceneY();
			}
		});
		scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
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
					cameraObject.ry
							.setAngle(cameraObject.ry.getAngle() - mouseDeltaX * modifierFactor * modifier * 2.0); // +
					cameraObject.rx
							.setAngle(cameraObject.rx.getAngle() + mouseDeltaY * modifierFactor * modifier * 2.0); // -
				} else if (me.isSecondaryButtonDown()) {
					double z = camera.getTranslateZ();
					double newZ = z + mouseDeltaX * modifierFactor * modifier;
					camera.setTranslateZ(newZ);
				} else if (me.isMiddleButtonDown()) {
					cameraObject2.t.setX(cameraObject2.t.getX() + mouseDeltaX * modifierFactor * modifier * 0.3); // -
					cameraObject2.t.setY(cameraObject2.t.getY() + mouseDeltaY * modifierFactor * modifier * 0.3); // -
				}
			}
		});
	}

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
		buildMenu();

		Scene scene = new Scene(root, 1024, 768, true);
		scene.setFill(Color.WHITE);

		handleMouse(scene, world);
		handleKeyboard(scene, world);

		stage.setTitle("ZigZag - MainMenu");
		stage.setScene(scene);
		stage.show();
		scene.setCamera(camera);
	}

	public static void main(String[] args) {
		System.setProperty("prism.dirtyopts", "false");
		launch(args);
	}
}
