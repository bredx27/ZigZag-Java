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
public class PacketHolder {

	private Packet packet;
	private PacketSendListener listener;
	
	public PacketHolder(Packet packet){
		this(packet, null);
	}
	
	public PacketHolder(Packet packet, PacketSendListener listener){
		this.packet = packet;
		this.listener = listener;
	}
	
	public void callListener(){
		this.listener.onPacketSend(this.packet, System.currentTimeMillis());
	}
	
	/**
	 * @return the packet
	 */
	public Packet getPacket() {
		return packet;
	}

	/**
	 * @return the listener
	 */
	public PacketSendListener getListener() {
		return listener;
	}
}
