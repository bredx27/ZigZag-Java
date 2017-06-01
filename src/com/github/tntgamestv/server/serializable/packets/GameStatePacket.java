package com.github.tntgamestv.server.serializable.packets;

import com.github.tntgamestv.server.GameState;

public class GameStatePacket extends Packet {
	private static final long serialVersionUID = -3821258968311069185L;
	private GameState gameState;

	private GameStatePacket() {

	}

	public GameStatePacket(GameState gameState) {
		this.gameState = gameState;
	}
}
