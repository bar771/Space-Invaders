package spaceInvaders;

import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;

public class Network {
	
	public int port = 7575;
	
	java.util.ArrayList<Player> players = Game.netPlayers;
	
	public Network() {
		
		try {
			DatagramSocket socket = new DatagramSocket(port);
			
			EndPoint peer = Recieve(socket);
			
			String[] msg = peer.msg.split(":"); // ID:REQUEST
			int id = Integer.parseInt(msg[0]);
			
			if (msg[1] == "LOGGEDUSR") { 
				for (int i=0; i<players.size(); i++) { 
					Player p = players.get(i);
					if (p == null) { 
						p = new Player(100, 100, Sprite.playerAnimO, Game.playerAnim); // Change X,Y to the exact coordinates.
					}
				}
			}
			else if (msg[1] == "LOGGEDOUTUSR") {
			}
			
			
			
		} catch(Exception e) { e.printStackTrace(); }
		
		
	}
	
	
	public void sendMessage(DatagramSocket socket, String msg, EndPoint toEp) {
		try {
			byte[] buffer = msg.getBytes();
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length, toEp.addr, toEp.port);
			socket.send(packet);
		} catch(Exception e) { e.printStackTrace(); }
	}
	
	public void sendEndPoint(DatagramSocket socket, EndPoint toEp, EndPoint contentEp) {
		try {
			String msg = contentEp.hAddr + ":" + contentEp.port;
			byte[] buffer = msg.getBytes();
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length, toEp.addr, toEp.port);
			socket.send(packet);
		} catch(Exception e) { e.printStackTrace(); }
	}
	
	public EndPoint Recieve(DatagramSocket socket) { 
		try {
			byte[] buffer = new byte[10];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			String msg = new String(buffer);
			return new EndPoint(packet.getAddress(), packet.getPort(), msg);
		} catch(Exception e) { e.printStackTrace(); }
		return null;
	}
	
}
/*
class PlayerAI extends Player {
	
	public PlayerAI(int x, int y, Sprite sprite, Sprite[] animSprite) {
		super(x, y, sprite, animSprite);
	}
	
	public boolean tick() {
		super.tick();
		return false;
	}
	
	public void draw(Bitmap bitmap) {
		super.draw(bitmap);
	}
	
	
	
}*/


class EndPoint { 
	public InetAddress addr;
	public String hAddr, msg;
	public int port;

	
	public EndPoint(InetAddress addr, int port, String msg) {
		this.addr = addr;
		this.port = port;
		this.msg = msg;
	}
	
	public EndPoint(String addr, int port, String msg) {
		this.hAddr = addr;
		this.port = port;
		this.msg = msg;
	}
}
