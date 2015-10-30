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
	private BufferedReader inFromClient;
	private boolean connOpen = true;
	private char splitter = (char) 007;
	private TicTacToeLogic tttl=null;
	

	// Client user
	public ServerConnection(ClientHandler client,ConnectionHandler connectionHandler) {// modtag også// Client user
		this.userSocket = client.getSocket();
		this.client = client;
	}

	public void run() {
		confirmClient(userSocket);
	}

	public void confirmClient(Socket userSocket)  {
		connOpen = true;
		String[] requestParam;
		
		try {
		outToClient  =  new DataOutputStream(client.getSocket().getOutputStream());
		inFromClient = new BufferedReader(new InputStreamReader(userSocket.getInputStream()));
		} catch (IOException e1) {e1.printStackTrace();};
		
		try {
		while (connected) { 
			String request;
			
			request = inFromClient.readLine();
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
								
				case "NEW": 	newGame();
								break;
			}
			} else {
				if(connOpen)
				close();
			}
		}} catch (IOException e) {
			connected=false;
			e.printStackTrace();
		}
	}
	public void login(String username, String password){
		boolean isLoggedIn = false;
		
		for(ClientHandler clients: ConnectionHandler.allUsers){
			if(username.equals(clients.getUsername())){isLoggedIn=true;}
			else{isLoggedIn=false;}}
		
		if(!isLoggedIn){
			client.setUsername(username);
			user = ConnectionHandler.dbController.login(username, password);
			if(user==null){
				try {
					System.out.println("is this it?");
					close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(connOpen){
			showAllUsers();
			}
			System.out.println("gz fgt, u did it");
		}else{
			System.out.println(username);
			user=null;
			System.out.println("log lige ind ordentligt plz");
		}
		
	}
	public void close() throws IOException{
		connOpen = false;
		System.out.println(client.getUsername());
		outToClient =  new DataOutputStream(client.getSocket().getOutputStream());;
		outToClient.writeBytes("CONNCLOSE");
		client.getSocket().close();
		user=null;
		connected=false;
		outToClient.close();
		inFromClient.close();
		client.close();
		
	}
	public void chat(String requestParam,ClientHandler clients) throws IOException{
		outToClient = new DataOutputStream(clients.getSocket().getOutputStream());
		outToClient.writeBytes("CHAT" + splitter + requestParam + splitter + client.getUsername() + '\n');
		System.out.println("Sending " + requestParam);
}
	
	public void move(int x, int y) throws IOException{
		outToClient =  new DataOutputStream(client.getSocket().getOutputStream());;
		if(tttl.setPiece(x, y, user.getID())){
			outToClient.writeBytes("TRUE"+splitter+ x+""+y +splitter+"hej"+'\n');
		}else{
			outToClient.writeBytes("FALSE"+splitter+ x+""+y +splitter+"hej"+'\n');
		}
	}
	public void showAllUsers(){
		for(ClientHandler clients: ConnectionHandler.allUsers){
			try {
				outToClient.writeBytes("CLIENT" + splitter + "user joined the channel"+splitter+clients.getUsername()+'\n');
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
public void newGame(){
	if(tttl==null){
		tttl = new TicTacToeLogic(3);
	}
	}
}