/**
 * 
 */
package com.github.tntgamestv.shop;

import com.github.tntgamestv.game.ball.Ball;

/**
 * @author TnTGamesTV
 * Project: School8
 * Date: 23-05-2017
 */
public class ShopItem implements Buyable{

	private Ball.Type type;
	private int price;
	
	public ShopItem(Ball.Type type, int price){
		this.type = type;
		this.price = price;
	}

	/* (non-Javadoc)
	 * @see com.github.tntgamestv.shop.Buyable#getPrice()
	 */
	@Override
	public int getPrice() {
		return price;
	}
}
