/**
 * 
 */
package com.github.tntgamestv;

import com.github.tntgamestv.server.PacketHolder;
import com.github.tntgamestv.server.PacketSendListener;
import com.github.tntgamestv.server.serializable.packets.Packet;
import com.github.tntgamestv.server.serializable.packets.TimePacket;
import com.github.tntgamestv.server.threads.ClientServerManager;

/**
 * @author TnTGamesTV
 * Project: School8
 * Date: 31-05-2017
 */
public class NetworkTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClientServerManager.init();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ClientServerManager.connect("127.0.0.1");
		PacketHolder holder = new PacketHolder(new TimePacket(System.currentTimeMillis()), new PacketSendListener() {
			
			@Override
			public void onPacketSend(Packet packet, long timestamp) {
				ClientServerManager.shutdown();
			}
		});
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ClientServerManager.sendPacket(holder);
		
	}
}
