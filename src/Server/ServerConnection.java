package Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

import DBAccess.DBController;

public class ServerConnection extends Thread {
	private Socket userSocket;
	private Boolean connected = true;
	private ClientHandler client;
	private User user = null;
	private DataOutputStream outToClient;
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
		outToClient  =  new DataOutputStream(client.getSocket().getOutputStream());;
		BufferedReader inFromClient = new BufferedReader(new InputStreamReader(userSocket.getInputStream()));
		
		while (connected) { 
			String request = inFromClient.readLine();
			String split = String.valueOf(splitter);
			requestParam = request.split(split);

			switch(requestParam[0]){
			case "":		System.out.println("Waiting for input");
							break;
						
			case "LOGIN":	login(requestParam[1],requestParam[2]);
							break;
				}
			
			if (user != null) {
				switch(requestParam[0]){
				case "CHAT":	System.out.println("recieved " + requestParam[1]);
								for (ClientHandler clients : ConnectionHandler.allUsers) {
								chat(requestParam[1],clients);}
								break;
								
				case "EXIT": 	close();
								break;
								
				case "MOVE":	int x = Integer.parseInt(requestParam[1]);
								int y = Integer.parseInt(requestParam[2]);
								move(x,y);
								break;
								
				case "CLIENT":	showAllUsers();
								break;
			}
			} else {
				close();
			}
		}
	}
	public void login(String username, String password){
		client.setUsername(username);
		for(ClientHandler clients: ConnectionHandler.allUsers){
			if(username.equals(clients.getUsername())){
				System.out.println(username);
				user=null;
				System.out.println("log lige ind ordentligt plz");
				break;
			}
			else{
				user = ConnectionHandler.dbController.login(username, password);
				System.out.println("gz fgt, u did it");
			}
		}
		
	}
	public void close() throws IOException{
		outToClient =  new DataOutputStream(client.getSocket().getOutputStream());;
		outToClient.writeBytes("CONNCLOSE");
		client.getSocket().close();
		user=null;
		connected=false;
		outToClient.close();
		client.close();
	}
	public void chat(String requestParam,ClientHandler clients) throws IOException{
		outToClient = new DataOutputStream(clients.getSocket().getOutputStream());
		outToClient.writeBytes("CHAT" + splitter + requestParam + splitter + client.getUsername() + '\n');
		System.out.println("Sending " + requestParam);
}
	
	public void move(int x, int y) throws IOException{
		outToClient =  new DataOutputStream(client.getSocket().getOutputStream());;
		if(x!= 2 || y!=3){
			System.out.println(x+" "+y);
			outToClient.writeBytes("FALSE"+splitter+x+splitter+y+'\n');
		}
	}
	public void showAllUsers(){
		ArrayList<String> activeUsers = new ArrayList<>();
		for(ClientHandler clients: ConnectionHandler.allUsers){
			activeUsers.add(clients.getUsername());
			try {
				for(String users : activeUsers){
					outToClient.writeBytes("CLIENT" + splitter + "user joined the channel"+splitter+users+'\n');
				System.out.println(users);
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}