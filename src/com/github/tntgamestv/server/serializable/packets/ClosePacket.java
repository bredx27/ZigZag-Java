package com.github.tntgamestv.server.serializable.packets;

public class ClosePacket extends Packet {
	private static final long serialVersionUID = -4949709940347084268L;
	private Integer errorCode;

	public ClosePacket(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public Integer getErrorCode() {
		return this.errorCode;
	}
}
