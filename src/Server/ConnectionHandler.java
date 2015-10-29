package Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import DBAccess.DBController;

public class ConnectionHandler {
	ServerSocket socketListener = new ServerSocket(7774);
	protected static DBController dbController = new DBController();
	protected static ArrayList<ClientHandler> allUsers = new ArrayList<>();
	int index = 0;
	public ConnectionHandler() throws Exception {
		while (true) {
			Socket userSocket = socketListener.accept();
			if (userSocket != null) {
				System.out.println("Trying to connect");
				ClientHandler client = new ClientHandler(userSocket);
				allUsers.add(client);
				ServerConnection conn = new ServerConnection(client,this);
				index++;
				System.out.println("Connection established");
				conn.start();
			}
		}
	}
	/*
	 * public void verifyUser(User user){ Stuff to verify via database }
	 */
	
	
}