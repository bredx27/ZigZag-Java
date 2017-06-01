/**
 * 
 */
package com.github.tntgamestv.game.ball;

import javafx.scene.paint.*;

/**
 * @author TnTGamesTV Project: School8 Date: 20-05-2017
 */
public class Ball {

	public enum Type {
		RED(0, 1000), ORANGE(1, 1000), GREEN(2, 1000), BLUE(3, 1000), WHITE(4, 1000), BLACK(5, 1000), BLINK(6,
				2000), RAINBOW(7, 5000);
		
		int	value;
		int price;

		Type(int value, int price) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
		
		public int getPrice(){
			return price;
		}
	}

	public static Material getMaterial(Ball.Type type) {
		switch (type) {
		case RED:
			PhongMaterial red = new PhongMaterial();
			red.setDiffuseColor(Color.DARKRED);
			red.setSpecularColor(Color.RED);
			red.setSpecularPower(40);
			return red;
		case ORANGE:
			PhongMaterial orange = new PhongMaterial();
			orange.setDiffuseColor(Color.DARKORANGE);
			orange.setSpecularColor(Color.ORANGE);
			orange.setSpecularPower(40);
			return orange;
		case GREEN:
			PhongMaterial green = new PhongMaterial();
			green.setDiffuseColor(Color.DARKGREEN);
			green.setSpecularColor(Color.GREEN);
			green.setSpecularPower(40);
			return green;
		case BLUE:
			PhongMaterial blue = new PhongMaterial();
			blue.setDiffuseColor(Color.DARKBLUE);
			blue.setSpecularColor(Color.BLUE);
			blue.setSpecularPower(40);
			return blue;
		case WHITE:
			PhongMaterial white = new PhongMaterial();
			white.setDiffuseColor(Color.WHITESMOKE);
			white.setSpecularColor(Color.WHITE);
			white.setSpecularPower(40);
			return white;
		case BLACK:
			PhongMaterial black = new PhongMaterial();
			black.setDiffuseColor(Color.BLACK);
			black.setSpecularColor(Color.DARKGRAY);
			black.setSpecularPower(40);
			return black;
		default:
			PhongMaterial defaultMaterial = new PhongMaterial();
			defaultMaterial.setDiffuseColor(Color.GREY);
			defaultMaterial.setSpecularColor(Color.LIGHTGREY);
			defaultMaterial.setSpecularPower(40);
			return defaultMaterial;
		}
	}
}
