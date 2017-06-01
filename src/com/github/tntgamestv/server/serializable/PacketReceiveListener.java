/**
 * 
 */
package com.github.tntgamestv.server.serializable;

import com.github.tntgamestv.server.serializable.packets.ClosePacket;
import com.github.tntgamestv.server.serializable.packets.ConnectPacket;
import com.github.tntgamestv.server.serializable.packets.GameStatePacket;
import com.github.tntgamestv.server.serializable.packets.KnockPacket;
import com.github.tntgamestv.server.serializable.packets.StatsUpdatePacket;
import com.github.tntgamestv.server.serializable.packets.TimePacket;

/**
 * @author TnTGamesTV Project: School8 Date: 30-05-2017
 */
public interface PacketReceiveListener {

	public abstract void onTimePacketRecieved(TimePacket timePacket);

	public abstract void onKnockPacketRecieved(KnockPacket knockPacket);

	public abstract void onConnectPacketRecieved(ConnectPacket connectPacket);

	public abstract void onClosePacketRecieved(ClosePacket closePacket);

	public abstract void onGameStatePacketRecieved(GameStatePacket gameStatePacket);

	public abstract void onStatsUpdatePacketRecieved(StatsUpdatePacket statsUpdatePacket);
}
