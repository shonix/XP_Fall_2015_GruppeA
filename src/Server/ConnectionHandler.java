package Server;

import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionHandler {
	ServerSocket socketListener = new ServerSocket(7778);
	int socnum = 0;
	public ConnectionHandler() throws Exception {
		while (true) {
			Socket userSocket = socketListener.accept();
			if (userSocket != null) {
				socnum++;
				System.out.println(userSocket.getRemoteSocketAddress().toString());
				ClientConnection conn = new ClientConnection(userSocket);
				conn.start();
			}
		}
	}
	/*
	 * public void verifyUser(User user){ Stuff to verify via database }
	 */
}