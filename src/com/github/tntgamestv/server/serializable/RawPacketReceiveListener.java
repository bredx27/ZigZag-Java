/**
 * 
 */
package com.github.tntgamestv.server.serializable;

import com.github.tntgamestv.server.serializable.packets.Packet;

/**
 * @author TnTGamesTV
 * Project: School8
 * Date: 30-05-2017
 */
public interface RawPacketReceiveListener {

	public abstract void onPacketReceive(Packet packet);
}
