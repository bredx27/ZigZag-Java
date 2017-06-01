package com.github.tntgamestv.server.serializable.packets;

import com.github.tntgamestv.game.GameSettingsManager;

public class ConnectPacket extends Packet {
	private static final long serialVersionUID = 5740607954608483417L;
	private GameSettingsManager gameSettingsManager;

	private ConnectPacket() {

	}

	public ConnectPacket(GameSettingsManager gameSettingsManager) {
		this.gameSettingsManager = gameSettingsManager;
	}

	public GameSettingsManager getGameSettingsManager() {
		return this.gameSettingsManager;
	}
}
