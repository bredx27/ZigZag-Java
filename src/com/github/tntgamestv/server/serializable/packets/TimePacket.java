package com.github.tntgamestv.server.serializable.packets;

public class TimePacket extends Packet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1446304425887757872L;
	private long timeCode;

	private TimePacket() {

	}

	public TimePacket(long timeCode) {
		this.timeCode = timeCode;
	}

	public long getTimeCode() {
		return timeCode;
	}

}
