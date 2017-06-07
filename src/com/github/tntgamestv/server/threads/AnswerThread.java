/**
 * 
 */
package com.github.tntgamestv.server.threads;

import java.io.ObjectInputStream;
import java.net.Socket;

import com.github.tntgamestv.console.Out;
import com.github.tntgamestv.server.serializable.PacketHandler;
import com.github.tntgamestv.server.serializable.packets.ClosePacket;
import com.github.tntgamestv.server.serializable.packets.ConnectPacket;
import com.github.tntgamestv.server.serializable.packets.GameStatePacket;
import com.github.tntgamestv.server.serializable.packets.KnockPacket;
import com.github.tntgamestv.server.serializable.packets.Packet;
import com.github.tntgamestv.server.serializable.packets.StatsUpdatePacket;
import com.github.tntgamestv.server.serializable.packets.TimePacket;

/**
 * @author TnTGamesTV Project: School8 Date: 30-05-2017
 */
public class AnswerThread extends Thread {

	/* Host */
	private ServerThread hostThread;

	/* Connection */
	private Socket socket;
	private String hostName;
	private int port;

	/* Handling */
	private PacketHandler packetHandler;

	public AnswerThread(ServerThread hostThread, Socket connectedSocket, PacketHandler packetHandler) {
		this.hostThread = hostThread;
		this.socket = connectedSocket;
		this.hostName = this.socket.getInetAddress().getHostName();
		this.port = this.socket.getPort();
		this.packetHandler = packetHandler;
		Out.answerThread("AnswerThread was created. Host => " + connectedSocket.getInetAddress().getHostName());
	}

	public void onPacketReceived(Packet packet) {
		Out.answerThread("Event - onPacketReceived");
		if (packet instanceof TimePacket) {
			Out.answerThread("Incoming packet => TimePacket");
			packetHandler.onTimePacketRecieved((TimePacket) packet);
		} else if (packet instanceof KnockPacket) {
			Out.answerThread("Incoming packet => KnockPacket");
			packetHandler.onKnockPacketRecieved((KnockPacket) packet);
		} else if (packet instanceof ConnectPacket) {
			Out.answerThread("Incoming packet => ConnectPacket");
			packetHandler.onConnectPacketRecieved((ConnectPacket) packet);
		} else if (packet instanceof ClosePacket) {
			Out.answerThread("Incoming packet => ClosePacket");
			ClosePacket closePacket = (ClosePacket) packet;
			Out.answerThread("Closing server thread (hostThread)");
			Out.answerThread("Connection has been ended. Exit code is " + closePacket.getErrorCode());
			Out.answerThread("Bye, bye. :D");
			this.hostThread.close();
			packetHandler.onClosePacketRecieved((ClosePacket) packet);
		} else if (packet instanceof GameStatePacket) {
			Out.answerThread("Incoming packet => GameStatePacket");
			packetHandler.onGameStatePacketRecieved((GameStatePacket) packet);
		} else if (packet instanceof StatsUpdatePacket) {
			Out.answerThread("Incoming packet => StatsUpdatePacket");
			packetHandler.onStatsUpdatePacketRecieved((StatsUpdatePacket) packet);
		}
	}

	@Override
	public void run() {
		ObjectInputStream objectInputStream = null;
		try {
			if (socket.isConnected()) {
				objectInputStream = new ObjectInputStream(socket.getInputStream());
			}
			while (socket.isConnected()) {
				Out.answerThread("Reading object from input stream");
				Object object = objectInputStream.readObject();
				Out.answerThread("Got object: " + object.toString());

				if (object instanceof Packet) {
					Out.answerThread("Object is a packet. Redirecting to PacketHandler");
					onPacketReceived((Packet) object);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
}
