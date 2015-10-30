package Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import DBAccess.DBController;

public class ServerConnection extends Thread {
	private Socket userSocket;
	private Boolean connected = true;
	private ClientHandler client;
	private User user = null;
	private char splitter = (char) 007;
	

	// Client user
	public ServerConnection(ClientHandler client,ConnectionHandler connectionHandler) {// modtag også// Client user
		this.userSocket = client.getSocket();
		this.client = client;
	}

	public void run() {
		try {
			confirmClient(userSocket);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void confirmClient(Socket userSocket) throws IOException {
		String[] requestParam;
		System.out.println("A connection has been made, waiting for input");
		BufferedReader inFromClient = new BufferedReader(new InputStreamReader(
				userSocket.getInputStream()));
		DataOutputStream outToClient =  new DataOutputStream(client.getSocket()
				.getOutputStream());;
		
		while (connected) { 
			System.out.println("waiting");
			String request = inFromClient.readLine();
			String split = String.valueOf(splitter);
			requestParam = request.split(split);

			if (requestParam[0].equals("")) {
				System.out.println("Waiting for input");
			}

			else if (requestParam[0].equals("LOGIN")) {
				login(requestParam[1],requestParam[2]);
			}
			if (user != null) {

				if (requestParam[0].equals("CHAT")) {
					System.out.println("recieved " + requestParam[1]);
					for (ClientHandler clients : ConnectionHandler.allUsers) {
						chat(requestParam[1],clients);
						}
				} else if (requestParam[0].equals("EXIT")) {
					close();
					
				}
			} else {
				close();
			}
		}
	}
	public void login(String username, String password){
		System.out.println(username);
		System.out.println(password);
		System.out.println(client.getUsername());
		client.setUsername(username);
		user = ConnectionHandler.dbController.login(username, password);
	}
	public void close() throws IOException{
		DataOutputStream outToClient =  new DataOutputStream(client.getSocket().getOutputStream());;
		outToClient.writeBytes("CONNCLOSE");
		client.getSocket().close();
		user=null;
		connected=false;
		outToClient.close();
		client.close();
	}
	public void chat(String requestParam,ClientHandler clients) throws IOException{
		DataOutputStream outToClient = new DataOutputStream(clients.getSocket()
				.getOutputStream());
		outToClient.writeBytes("CHAT" + splitter
				+ requestParam + splitter + client.getUsername() + '\n');
		System.out.println("Sending " + requestParam);
}
}