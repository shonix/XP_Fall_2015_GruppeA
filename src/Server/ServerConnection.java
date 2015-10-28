package Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import DBAccess.DBController;

public class ServerConnection extends Thread {
	Socket userSocket;
	ConnectionHandler connectionHandler;
	Boolean connected = true;
	int index;
	
	//Client user
	public ServerConnection(Socket userSocket, ConnectionHandler connectionHandler, int index){//modtag også Client user
		this.userSocket = userSocket;
		this.connectionHandler = connectionHandler;
		this.index = index;
	}
	
	public void run(){
		try {
			confirmClient(userSocket);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void confirmClient(Socket userSocket) throws IOException{
		String username;
		String tmpPass;
		User client = null;
		String[] requestParam;
		System.out.println("A connection has been made, waiting for input");
		BufferedReader inFromClient = new BufferedReader(new InputStreamReader(userSocket.getInputStream()));
		DataOutputStream outToClient;	
			while(connected){
				System.out.println("waiting");
				String request = inFromClient.readLine();
				char splitter = (char)007;
				String split = String.valueOf(splitter);
				requestParam = request.split(split);
				
				
				if(requestParam[0].equals("")){
					System.out.println("Waiting for input");
				}
				
				else if(requestParam[0].equals("LOGIN")){
					username = requestParam[1];
					tmpPass = requestParam[2];
					System.out.println(requestParam[1]);
					System.out.println(requestParam[2]);
					client = ConnectionHandler.dbController.login(username, tmpPass);
				}
				if(client!=null){

				if(requestParam[0].equals("CHAT")){
					System.out.println("recieved " +requestParam[1]);
					for(Socket userSockets : ConnectionHandler.allUsers){
						outToClient = new DataOutputStream(userSockets.getOutputStream());
						outToClient.writeBytes(requestParam[1]+'\n');
						System.out.println("Sending "+requestParam[1]);
					}
				}
				else if(requestParam[0].equals("EXIT")){
					ConnectionHandler.allUsers.remove(index);
				}
				}
				else{
					System.out.println("Invalid user");
				}
				
			}
	}
		
}
