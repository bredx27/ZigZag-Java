package com.github.tntgamestv.server.serializable;

import com.github.tntgamestv.server.serializable.packets.ClosePacket;
import com.github.tntgamestv.server.serializable.packets.ConnectPacket;
import com.github.tntgamestv.server.serializable.packets.GameStatePacket;
import com.github.tntgamestv.server.serializable.packets.KnockPacket;
import com.github.tntgamestv.server.serializable.packets.StatsUpdatePacket;
import com.github.tntgamestv.server.serializable.packets.TimePacket;

public class PacketHandler implements PacketReceiveListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.github.tntgamestv.server.serializable.PacketReceiveListener#
	 * onTimePacketRecieved(com.github.tntgamestv.server.serializable.
	 * TimePacket)
	 */
	@Override
	public void onTimePacketRecieved(TimePacket timePacket) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.github.tntgamestv.server.serializable.PacketReceiveListener#
	 * onKnockPacketRecieved(com.github.tntgamestv.server.serializable.
	 * KnockPacket)
	 */
	@Override
	public void onKnockPacketRecieved(KnockPacket knockPacket) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.github.tntgamestv.server.serializable.PacketReceiveListener#
	 * onConnectPacketRecieved(com.github.tntgamestv.server.serializable.
	 * ConnectPacket)
	 */
	@Override
	public void onConnectPacketRecieved(ConnectPacket connectPacket) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.github.tntgamestv.server.serializable.PacketReceiveListener#
	 * onGameStatePacketRecieved(com.github.tntgamestv.server.serializable.
	 * GameStatePacket)
	 */
	@Override
	public void onGameStatePacketRecieved(GameStatePacket gameStatePacket) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.github.tntgamestv.server.serializable.PacketReceiveListener#
	 * onStatsUpdatePacketRecieved(com.github.tntgamestv.server.serializable.
	 * StatsUpdatePacket)
	 */
	@Override
	public void onStatsUpdatePacketRecieved(StatsUpdatePacket statsUpdatePacket) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.github.tntgamestv.server.serializable.PacketReceiveListener#
	 * onClosePacketRecieved(com.github.tntgamestv.server.serializable.packets.
	 * ClosePacket)
	 */
	@Override
	public void onClosePacketRecieved(ClosePacket closePacket) {
		// TODO Auto-generated method stub

	}
}
