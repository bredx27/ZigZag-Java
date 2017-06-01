/**
 * 
 */
package com.github.tntgamestv.frame;

import java.util.ArrayList;
import java.util.List;

import com.github.tntgamestv.Constants;
import com.github.tntgamestv.game.ball.Ball;
import com.github.tntgamestv.shop.Shop;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

/**
 * @author TnTGamesTV Project: School8 Date: 16-05-2017
 */
public class ShopFrame extends Frame {

	private int				currentBox		= 1;

	private List<ShopBox>	shopBoxes		= new ArrayList<>();
	private Group			boxGroup		= new Group();
	private Group			ballGroup		= new Group();
	private Group			selectorGroup	= new Group();

	/**
	 * @param primaryScene
	 * @param primaryStage
	 * @param world
	 */
	public ShopFrame(MainFrame mainFrame, RenderObject world) {
		super(mainFrame, world);
	}

	public ShopFrame(MainFrame mainFrame, Frame frame) {
		super(mainFrame, frame);
	}

	public class ShopBox {

		private int			x;
		private Sphere		ball;
		private Ball.Type	ballType;

		/**
		 * @return the ballType
		 */
		public Ball.Type getBallType() {
			return ballType;
		}

		/**
		 * @param ballType
		 *            the ballType to set
		 */
		public void setBallType(Ball.Type ballType) {
			this.ballType = ballType;
		}

		/**
		 * @return the ball
		 */
		public Sphere getBall() {
			return ball;
		}

		/**
		 * @param ball
		 *            the ball to set
		 */
		public void setBall(Sphere ball) {
			this.ball = ball;
		}

		public ShopBox(int x) {
			this.x = x;
			this.ball = null;
		}

		/**
		 * @return the x
		 */
		public int getX() {
			return x;
		}
	}

	public void create() {

		// Camera options
		this.isCameraRotationBlocked = true;
		this.mainFrame.cameraObject.rx.setAngle(-20);
		this.mainFrame.cameraObject.ry.setAngle(0);

		// 3D Object creation
		final PhongMaterial boxMaterial = new PhongMaterial();
		boxMaterial.setDiffuseColor(Color.DARKGREY);
		boxMaterial.setSpecularColor(Color.GREY);

		final PhongMaterial floorMaterial = new PhongMaterial();
		boxMaterial.setDiffuseColor(Color.LIGHTGRAY);
		boxMaterial.setSpecularColor(Color.WHITE);

		Box floor = new Box(3600, 10, 450);
		floor.setMaterial(floorMaterial);
		floor.getTransforms().add(new Translate(0, Constants.SHOP_BOX_Y_OFFSET, 0));
		this.boxGroup.getChildren().add(floor);

		Box selector1 = new Box(10, 50, 10);
		selector1.setMaterial(floorMaterial);

		Box selector2 = new Box(10, 50, 10);
		selector2.setMaterial(floorMaterial);

		generateBox(1575, 0, boxMaterial, Ball.Type.RAINBOW);
		generateBox(1125, 0, boxMaterial, Ball.Type.BLINK);
		generateBox(625, 0, boxMaterial, Ball.Type.BLACK);
		generateBox(225, 0, boxMaterial, Ball.Type.WHITE);
		generateBox(-225, 0, boxMaterial, Ball.Type.BLUE);
		generateBox(-625, 0, boxMaterial, Ball.Type.GREEN);
		generateBox(-1125, 0, boxMaterial, Ball.Type.ORANGE);
		generateBox(-1575, 0, boxMaterial, Ball.Type.RED);

		// Camera optimization
		this.mainFrame.camera.setTranslateX(shopBoxes.get(0).getX());

		this.children.getChildren().addAll(boxGroup, ballGroup);

	}

	private Sphere generateBall(ShopBox shopBox, Ball.Type type) {
		Sphere ball = new Sphere(Constants.SHOP_BALL_SIZE);
		ball.getTransforms().add(new Translate(shopBox.getX(), Constants.SHOP_BALL_Y_OFFSET, 0));
		ball.setMaterial(Ball.getMaterial(type));

		shopBox.setBallType(type);

		this.ballGroup.getChildren().add(ball);

		return ball;
	}

	private void generateBox(int translateX, int translateZ, Material material, Ball.Type type) {
		Box box = new Box(50, 250, 50);
		box.setMaterial(material);

		box.getTransforms().add(new Translate(translateX, Constants.SHOP_BOX_Y_OFFSET, translateZ));

		ShopBox shopBox = new ShopBox(translateX);

		Sphere ball = generateBall(shopBox, type);

		this.shopBoxes.add(shopBox);
		this.boxGroup.getChildren().add(box);

		shopBox.setBall(ball);
	}

	public void handleMouse(MouseEvent e) {
		super.handleMouse(e);
	}

	public void handleKeyboard(KeyEvent e) {
		super.handleKeyboard(e);

		if (e.getCode() == KeyCode.LEFT) {
			next();
		} else if (e.getCode() == KeyCode.RIGHT) {
			last();
		} else if (e.getCode() == KeyCode.ENTER) {
			ShopBox currentShopBox = this.shopBoxes.get(currentBox - 1);
			if (!Shop.hasBought(currentShopBox.getBallType())) {
				if (Shop.buyBall(currentShopBox.getBallType())) {
					System.out.println("buy->bought");
				} else {
					System.out.println("buy->notBought");
				}
			} else {
				selectBallAndStartGame(currentShopBox.getBallType());
			}
		}
	}

	private void selectBallAndStartGame(Ball.Type selectedBall) {
		System.out.println("selectBallAndStartGame " + selectedBall.getValue());
	}

	private void last() {
		if (currentBox >= Constants.SHOP_START_BOX && currentBox <= Constants.SHOP_MAX_BOX) {
			if (currentBox != Constants.SHOP_START_BOX) {
				ShopBox oldShopBox = this.shopBoxes.get(currentBox - 1);
				currentBox--;

				moveToBox(currentBox, false, oldShopBox);
			} else {
				ShopBox oldShopBox = this.shopBoxes.get(currentBox - 1);
				currentBox = Constants.SHOP_MAX_BOX;

				moveToBox(Constants.SHOP_MAX_BOX, true, oldShopBox);
			}
		} else if (currentBox == 0) {
			currentBox = Constants.SHOP_MAX_BOX;
			moveToBox(currentBox, true, null);
		} else {
			currentBox = Constants.SHOP_MAX_BOX;
			moveToBox(currentBox, true, null);
		}
	}

	private void next() {
		if (currentBox >= Constants.SHOP_START_BOX && currentBox <= Constants.SHOP_MAX_BOX) {
			if (currentBox != Constants.SHOP_MAX_BOX) {
				ShopBox oldShopBox = this.shopBoxes.get(currentBox - 1);
				currentBox++;

				moveToBox(currentBox, false, oldShopBox);
			} else {
				ShopBox oldShopBox = this.shopBoxes.get(currentBox - 1);
				currentBox = Constants.SHOP_START_BOX;

				moveToBox(Constants.SHOP_START_BOX, true, oldShopBox);
			}
		} else if (currentBox == 0) {
			currentBox = Constants.SHOP_START_BOX;
			moveToBox(currentBox, true, null);
		} else {
			currentBox = Constants.SHOP_START_BOX;
			moveToBox(currentBox, true, null);
		}
	}

	private void moveToBox(int box, boolean slow, ShopBox oldShopBox) {
		ShopBox shopBox = this.shopBoxes.get(box - 1);

		playTranslateTransition(shopBox.getX(), slow);
	}

	private void playTranslateTransition(int x, boolean slow) {
		TranslateTransition ttr1 = new TranslateTransition(Duration.millis(Constants.SHOP_NORMAL_SPEED),
				this.mainFrame.camera);
		ttr1.setInterpolator(Interpolator.EASE_BOTH);

		if (slow) ttr1.setDuration(Duration.millis(Constants.SHOP_SLOW_SPEED));

		ttr1.setToX(x);
		ttr1.play();
	}
}
