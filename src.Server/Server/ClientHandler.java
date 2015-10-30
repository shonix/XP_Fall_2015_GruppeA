package Server;

import java.net.Socket;

public class ClientHandler {
	private Socket userSocket;
	private String username = "";
	
	public ClientHandler(Socket userSocket){
		this.userSocket = userSocket;
	}
	
	public void close(){
		ConnectionHandler.allUsers.remove(this);
	}
	public Socket getSocket(){
		return userSocket;
	}
	public void setUsername(String username){
		this.username = username;
	}
	public String getUsername(){
		return username;
	}
}