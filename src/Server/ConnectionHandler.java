package Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ConnectionHandler {
	ServerSocket socketListener = new ServerSocket(7776);
	public static ArrayList<Socket> allUsers = new ArrayList<>();
	public ConnectionHandler() throws Exception {
		while (true) {
			Socket userSocket = socketListener.accept();
			if (userSocket != null) {
				System.out.println("Trying to connect");
				ServerConnection conn = new ServerConnection(userSocket,this);
				allUsers.add(userSocket);
				System.out.println("Connection established");
				conn.start();
			}
		}
	}
	/*
	 * public void verifyUser(User user){ Stuff to verify via database }
	 */
	
	
}