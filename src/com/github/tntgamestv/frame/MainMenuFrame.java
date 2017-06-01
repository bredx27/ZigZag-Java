/**
 * 
 */
package com.github.tntgamestv.frame;

import org.fxyz.shapes.primitives.Text3DMesh;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.effect.Glow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 * @author TnTGamesTV Project: School8 Date: 16-05-2017
 */
public class MainMenuFrame extends Frame {

	private int		selectedItem	= 0;
	private Group	menuGroup		= new Group();

	/**
	 * @param primaryScene
	 * @param primaryStage
	 * @param world
	 */
	public MainMenuFrame(MainFrame mainFrame, RenderObject world) {
		super(mainFrame, world);
	}

	public MainMenuFrame(MainFrame mainFrame, Frame frame) {
		super(mainFrame, frame);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.github.tntgamestv.gui.Frame#create()
	 */
	@Override
	public void create() {
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
		children.getChildren().addAll(menuGroup);
	}

	@Override
	public void show() {
		// MenuMusic.playRandomMusic();
	}

	protected void handleMouse(MouseEvent e) {
		super.handleMouse(e);
	}

	protected void handleKeyboard(KeyEvent e) {
		super.handleKeyboard(e);

		if (e.getCode() == KeyCode.DOWN) {

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
		} else if (e.getCode() == KeyCode.UP) {
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
		} else if (e.getCode() == KeyCode.ENTER) {
			if (selectedItem >= 1 && selectedItem <= 3) {
				// open menu
				menuTransition();

				if (selectedItem == 1) {
					// Play
					GameFrame game = new GameFrame(this.mainFrame, this);
					this.setNewFrame(game);
				} else if (selectedItem == 2) {
					// Shop
					ShopFrame shop = new ShopFrame(this.mainFrame, this);
					this.setNewFrame(shop);
				} else if (selectedItem == 3) {
					// Options

				}
			}
		}
	}

	private void menuTransition() {
		Box btnPlay = (Box) getMenuItemBox(1);
		Box btnOptions = (Box) getMenuItemBox(3);
		Box btnShop = (Box) getMenuItemBox(2);

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
			box = (Box) this.menuGroup.getChildren().get(0);
			break;
		case 2:
			// 2 => Shop
			box = (Box) this.menuGroup.getChildren().get(2);
			break;
		case 3:
			// 3 => Options
			box = (Box) this.menuGroup.getChildren().get(1);
			break;
		}

		return box;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.github.tntgamestv.frame.Frame#hide()
	 */
	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}
}
