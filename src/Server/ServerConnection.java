package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ServerConnection extends Thread {

	Socket userSocket;
	//Client user
	public ServerConnection(Socket userSocket){//modtag også Client user
		this.userSocket = userSocket;
	}
	
	public void run(){
		confirmClient(userSocket);
	}
	
	public void confirmClient(Socket userSocket){
		
		try {
		BufferedReader inFromClient = new BufferedReader(new InputStreamReader(userSocket.getInputStream()));
		while(true){
		String msg = inFromClient.readLine();	
		System.out.println(msg);
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
}
