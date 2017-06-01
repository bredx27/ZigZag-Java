/**
 * 
 */
package com.github.tntgamestv.shop;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import com.github.tntgamestv.game.ball.Ball;

/**
 * @author TnTGamesTV Project: School8 Date: 23-05-2017
 */
public class Shop {

	private static Preferences prefs = Preferences.userNodeForPackage(Shop.class);

	public static void init() throws BackingStoreException {
		if (!prefs.nodeExists("shop.money")) {
			prefs.putInt("shop.money", 10000);
		}

		if (!prefs.nodeExists("shop.bought")) {
			prefs.putByteArray("shop.bought", new byte[8]);
		}
	}

	public static int getMoney() {
		return prefs.getInt("shop.money", 0);
	}

	public static void setMoney(int money){
		prefs.putInt("shop.money", money);
	}
	
	public static byte[] getBought() {
		return prefs.getByteArray("shop.bought", new byte[8]);
	}
	
	public static void setBought(byte[] b){
		prefs.putByteArray("shop.bought", b);
	}
	
	public static ShopItem getBallFromType(Ball.Type type) {
		switch (type) {
		case RED:
			return new RedBall();
		default:
			return null;
		}
	}

	public static boolean hasBought(Ball.Type type) {
		return getBought()[type.getValue()] == 1;
	}

	public static boolean buyBall(Ball.Type type) {
		System.out.println("CurrentMoney: " + getMoney());
		
		if(getMoney() >= type.getPrice()){
			
			setMoney(getMoney() - type.getPrice());
			
			byte[] b = getBought();
			b[type.getValue()] = 1;
			
			setBought(b);
			
			return true;
		}else{
			return false;
		}
	}
}
