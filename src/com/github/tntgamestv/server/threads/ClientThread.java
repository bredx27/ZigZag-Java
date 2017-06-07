package com.github.tntgamestv.server.threads;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.github.tntgamestv.console.Out;
import com.github.tntgamestv.events.FutureListener;
import com.github.tntgamestv.execeptions.PacketMatchesQueuePacketException;
import com.github.tntgamestv.server.PacketHolder;
import com.github.tntgamestv.server.PacketSendListener;
import com.github.tntgamestv.server.serializable.packets.ClosePacket;
import com.github.tntgamestv.server.serializable.packets.Packet;

public class ClientThread extends Thread {

	/* Connection */
	private Socket connection;
	private String hostName;
	private int port;

	/* I/O-Streams */
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;

	private FutureListener[] futureListeners;

	private boolean keep = true;

	private List<PacketHolder> packetQueue;

	/**
	 * Creates a new ClientThread to send packets
	 * 
	 * @param client
	 * @param listener
	 */
	public ClientThread(String hostName, int port, FutureListener... futureListeners) {
		this.hostName = hostName;
		this.port = port;
		this.futureListeners = futureListeners;
		this.packetQueue = new ArrayList<>();
	}

	public void init() {
		try {
			this.connection = new Socket(hostName, port);
		} catch (IOException e) {
		}

		if (connection != null) {
			try {
				Out.clientThread("Client thread init starting. Creating I/O-Streams.");

				ObjectOutputStream objectOutputStream = new ObjectOutputStream(connection.getOutputStream());
				Out.clientThread("Created client output stream. Flushing output stream.");
				objectOutputStream.flush();
				Out.clientThread("Flushed output stream. Creating input stream.");
				Out.clientThread("Created client input stream. Linking to class fields.");
				this.objectOutputStream = objectOutputStream;
				// this.objectInputStream = objectInputStream;
				Out.clientThread("Linking to class fields finished. We are up and running!");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (connection.isConnected()) {
				Out.clientThread("Executing FutureListener(s)");
				foreachFutureListener();
			}
		}
	}

	public void setFutureListeners(FutureListener... futureListeners) {
		this.futureListeners = futureListeners;
	}

	private void foreachFutureListener() {
		if (futureListeners != null) {
			for (FutureListener listener : this.futureListeners) {
				listener.execute();
				Out.clientThread("FutureListener got executed");
			}
		}
	}

	public boolean isConnected() {
		if (connection != null) {
			return connection.isConnected();
		} else {
			return false;
		}
	}

	public void close() {
		PacketHolder closeHolder = new PacketHolder(new ClosePacket(0), new PacketSendListener() {

			@Override
			public void onPacketSend(Packet packet, long timestamp) {
				try {
					connection.close();
					keep = false;
					ClosePacket closePacket = (ClosePacket) packet;
					Out.clientThread(
							"Client thread closed on " + timestamp + " with error code " + closePacket.getErrorCode());
				} catch (IOException e) {
					Out.clientThread("Something went wrong while sending close packet and closing client thread");
				}
			}
		});
		this.sendPacket(closeHolder);
	}

	@Override
	public void run() {
		while (keep) {
			for (PacketHolder packetHolder : packetQueue) {
				Out.clientThread("Sending packet.");
				try {
					objectOutputStream.writeObject(packetHolder.getPacket());
					Out.clientThread("Packet was written to ObjectOutputStream");
					packetHolder.callListener();
					objectOutputStream.flush();
				} catch (IOException e) {
					Out.clientThread("Something went wrong while writing to output stream: " + e.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			packetQueue.clear();
		}
	}

	public void sendPacket(PacketHolder clientPacketHolder) {
		if (!packetQueue.contains(clientPacketHolder)) {
			packetQueue.add(clientPacketHolder);
		} else {
			throw new PacketMatchesQueuePacketException("This packet is already in queue");
		}
	}

	public void sendPacket(Packet packet) {
		PacketHolder newHolder = new PacketHolder(packet);
		this.sendPacket(newHolder);
	}
}
