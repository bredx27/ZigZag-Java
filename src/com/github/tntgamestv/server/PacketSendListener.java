/**
 * 
 */
package com.github.tntgamestv.server;

import com.github.tntgamestv.server.serializable.packets.Packet;

/**
 * @author TnTGamesTV
 * Project: School8
 * Date: 31-05-2017
 */
public interface PacketSendListener {

	public abstract void onPacketSend(Packet packet, long timestamp);
}
