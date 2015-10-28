package Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ServerConnection extends Thread {
	Socket userSocket;
	Boolean connected = true;
	//Client user
	public ServerConnection(Socket userSocket, ConnectionHandler connectionHandler){//modtag ogs� Client user
		this.userSocket = userSocket;
	}
	
	public void run(){
		try {
			confirmClient(userSocket);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void confirmClient(Socket userSocket) throws IOException{
		String[] requestParam;
		System.out.println("A connection has been made, waiting for input");
		BufferedReader inFromClient = new BufferedReader(new InputStreamReader(userSocket.getInputStream()));
		DataOutputStream outToClient;	
			while(connected){
				System.out.println("waiting");
				String request = inFromClient.readLine();
				requestParam = request.split(" ");
				
				
				if(requestParam[0].equals("")){
					System.out.println("Waiting for input");
				}
				else if(requestParam[0].equals("CHAT")){
					System.out.println(requestParam[1]);
					for(Socket userSockets : ConnectionHandler.allUsers){
						outToClient = new DataOutputStream(userSockets.getOutputStream());
						outToClient.writeBytes(requestParam[1]);
						System.out.println(requestParam[1]);
					}
				}
			}
	}
		
}
