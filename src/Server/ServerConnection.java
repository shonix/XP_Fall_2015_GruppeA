package Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import com.mysql.fabric.xmlrpc.Client;

import DBAccess.DBController;

public class ServerConnection extends Thread {
	private Socket userSocket;
	private ConnectionHandler connectionHandler;
	private Boolean connected = true;
	private ClientHandler client;
	private int index;

	// Client user
	public ServerConnection(ClientHandler client,
			ConnectionHandler connectionHandler, int index) {// modtag også
																// Client user
		this.connectionHandler = connectionHandler;
		this.index = index;
		this.userSocket = client.getSocket();
	}

	public void run() {
		try {
			confirmClient(userSocket);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void confirmClient(Socket userSocket) throws IOException {
		String username = null;
		String tmpPass = null;
		User user = null;
		String[] requestParam;
		System.out.println("A connection has been made, waiting for input");
		BufferedReader inFromClient = new BufferedReader(new InputStreamReader(
				userSocket.getInputStream()));
		DataOutputStream outToClient;
		while (connected) {
			System.out.println("waiting");
			String request = inFromClient.readLine();
			char splitter = (char) 007;
			String split = String.valueOf(splitter);
			requestParam = request.split(split);

			if (requestParam[0].equals("")) {
				System.out.println("Waiting for input");
			}

			else if (requestParam[0].equals("LOGIN")) {
				username = requestParam[1];
				tmpPass = requestParam[2];
				System.out.println(requestParam[1]);
				System.out.println(requestParam[2]);
				user = ConnectionHandler.dbController.login(username, tmpPass);
				client.setUsername(username);
			}
			if (user != null) {

				if (requestParam[0].equals("CHAT")) {
					System.out.println("recieved " + requestParam[1]);
					for (ClientHandler clients : ConnectionHandler.allUsers) {
						outToClient = new DataOutputStream(clients.getSocket()
								.getOutputStream());
						outToClient.writeBytes("CHAT" + splitter
								+ requestParam[1] + splitter + username + '\n');
						System.out.println("Sending " + requestParam[1]);
					}
				} else if (requestParam[0].equals("EXIT")) {
					client.close();
				}
			} else {
				System.out.println("Invalid user");
			}
		}
	}
}
