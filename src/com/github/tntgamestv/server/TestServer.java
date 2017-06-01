package com.github.tntgamestv.server;

import com.github.tntgamestv.server.serializable.PacketHandler;
import com.github.tntgamestv.server.threads.ClientServerManager;
import com.github.tntgamestv.server.threads.ServerThread;

public class TestServer {

	public static void main(String[] args) {
		PacketHandler packetHandler = new PacketHandler();
		ServerThread serverThread = new ServerThread(ClientServerManager.SERVER_PORT, packetHandler);
		serverThread.init();
		serverThread.start();
	}
}
