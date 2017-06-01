package com.github.tntgamestv.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.github.tntgamestv.console.Out;
import com.github.tntgamestv.events.FutureListener;
import com.github.tntgamestv.server.serializable.packets.Packet;
import com.github.tntgamestv.server.serializable.packets.TimePacket;

public class ClientStarter extends Thread {

	/* connection */
	private String				hostName;
	private Integer				port;
	private Socket				socket;

	/* I/O-Streams */
	private ObjectInputStream	objectInputStream;
	private ObjectOutputStream	objectOutputStream;

	/* Future execution */
	private FutureListener		futureListener;

	public ClientStarter(String hostName, Integer port) {
		this.hostName = hostName;
		this.port = port;
	}

	public void setFutureListener(FutureListener futureListener){
		this.futureListener = futureListener;
	}
	
	@Override
	public void run() {
		try {
			Out.clientThread("Creating connection socket. host => " + hostName + "; port => " + port);
			socket = new Socket(hostName, port);

			Out.clientThread("Creating I/O-Streams. Socket is connected.");
			
			Out.clientThread("Creating output stream.");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectOutputStream.flush();
			
			Out.clientThread("Output stream is created and flushed. Creating input stream");
			ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
			Out.clientThread("Input stream is created. Linking class fields.");

			// Linking class fields
			this.objectOutputStream = objectOutputStream;
			this.objectInputStream = objectInputStream;
			Out.clientThread("Class fields are linked");

			this.sendPacket(new TimePacket(System.currentTimeMillis()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendPacket(Packet packet) {
		try {
			objectOutputStream.writeObject(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
