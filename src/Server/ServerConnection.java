package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ServerConnection extends Thread {
	Socket userSocket;
	Boolean connected;
	//Client user
	public ServerConnection(Socket userSocket){//modtag også Client user
		this.userSocket = userSocket;
		Boolean connected = true;
	}
	
	public void run(){
		try {
			confirmClient(userSocket);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void confirmClient(Socket userSocket) throws IOException{
		System.out.println("A connection has been made, waiting for input");
		BufferedReader inFromClient = new BufferedReader(new InputStreamReader(userSocket.getInputStream()));
		
		
		while(connected){
			String request = inFromClient.readLine();
			String[] requestParam = request.split(" ");
			if(!requestParam[0].equals("GET")){
				userSocket.close();
				return;
			}
			if(true){
				
			}
		}
	}
		
}
