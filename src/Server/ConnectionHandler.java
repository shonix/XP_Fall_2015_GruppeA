package Server;

import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionHandler {
	ServerSocket socketListener = new ServerSocket(7776);
	public ConnectionHandler() throws Exception {
		while (true) {
			Socket userSocket = socketListener.accept();
			if (userSocket != null) {
				System.out.println("Trying to connect");
				ServerConnection conn = new ServerConnection(userSocket);
				System.out.println("Connection established");
				conn.start();
			}
		}
	}
	/*
	 * public void verifyUser(User user){ Stuff to verify via database }
	 */
	
	
}