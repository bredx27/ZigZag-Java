package com.github.tntgamestv.server.threads;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.github.tntgamestv.console.Out;
import com.github.tntgamestv.server.serializable.PacketHandler;

public class ServerThread extends Thread {

	/* Connection */
	private ServerSocket			socket;
	private Integer					port;

	/* State */
	private Boolean					isRunning;

	/* Stream(s) */
	private InputStream				inputStream;

	/* Handling */
	private PacketHandler packetHandler;

	public ServerThread(Integer port, PacketHandler packetHandler) {
		this.port = port;
		this.packetHandler = packetHandler;
		this.isRunning = true;
	}

	public void init() {
		try {
			Out.serverThread("Creating server thread and opening port.");
			socket = new ServerSocket(port);
			Out.serverThread("Server is running and port is open. port => " + port);
		} catch (IOException e) {
			e.printStackTrace();
			isRunning = false;
		}
	}

	public void close(){
		try {
			this.socket.close();
		} catch (IOException e) {
			Out.serverThread("Something went wrong while closing the server port: " + e.getMessage());
		}
	}
	
	@Override
	public void run() {
		while (isRunning) {
			try {
				//Establish connection
				Socket connectedSocket = socket.accept();
				
				//Create answer thread
				AnswerThread answer = new AnswerThread(this, connectedSocket, packetHandler);
				Out.serverThread("Someone connected: Address => " + connectedSocket.getInetAddress().getHostAddress());
				answer.start();
				
			} catch (IOException e) {
				isRunning = false;
				e.printStackTrace();
			} catch (Exception e) {
				isRunning = false;
			}
		}
	}

	public Integer getPort() {
		return port;
	}
}
