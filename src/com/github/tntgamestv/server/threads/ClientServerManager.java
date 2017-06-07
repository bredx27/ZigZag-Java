/**
 * 
 */
package com.github.tntgamestv.server.threads;

import com.github.tntgamestv.console.Out;
import com.github.tntgamestv.server.PacketHolder;
import com.github.tntgamestv.server.serializable.PacketHandler;
import com.github.tntgamestv.server.serializable.packets.Packet;

/**
 * @author TnTGamesTV
 * Project: School8
 * Date: 30-05-2017
 */
public class ClientServerManager{

	private static ClientThread clientThread;
	private static ServerThread serverThread;
	
	private static PacketHandler packetHandler;
	
	public static final int SERVER_PORT = 60002;
	
	public static void init(){
		packetHandler = new PacketHandler();
		serverThread = new ServerThread(SERVER_PORT, packetHandler);
		serverThread.start();
	}
	
	public static void connect(String hostName){
		clientThread = new ClientThread(hostName, SERVER_PORT);
		clientThread.start();
	}
	
	public static boolean isConnected(){
		if(clientThread != null){
			return clientThread.isConnected();
		}else{
			return false;
		}
	}
	
	public static void sendPacket(Packet packet){
		if(isConnected()){
			//Connected
			clientThread.sendPacket(packet);
		}
	}
	
	public static void sendPacket(PacketHolder packetHolder){
		if(isConnected()){
			//Connected
			clientThread.sendPacket(packetHolder);
		}
	}
	
	public static void shutdown(){
		clientThread.close();
	}
}
