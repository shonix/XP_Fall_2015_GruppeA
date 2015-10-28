package Client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;


public class ClientConnection {
	
	Socket clientSocket;
	DataOutputStream outToServer;
	boolean correct = false;
	
	public ClientConnection ()
	{
		
	}
	
//	public static void main(String args[]) throws IOException 
//	{
//		System.out.println("starting TCPClient main"); 
//        String sentence; 
//        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); 
//        System.out.println("trying to connect"); 
//        Socket clientSocket = new Socket("10.111.176.136", 7778); 
//		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream()); 
//        System.out.print("Please type your text: "); 
//        
//        while(clientSocket.isConnected()){
//	        sentence = inFromUser.readLine(); 
//	        outToServer.writeBytes(sentence + '\n'); 
//        }
//        clientSocket.close(); 
//	}
	
	public boolean makeConnection(String username, String Password, String ip, int port) throws Exception
	{
		 setSocket(ip, port); 
	outToServer = new DataOutputStream(getSocket().getOutputStream()); 
		 
		 outToServer.writeBytes(username + " " + Password );
		 
		 outToServer.flush();
		 
		 if (clientSocket.isConnected())
		 {
			 correct = true;
		 }
		 else
		 {
			 correct = false;
		 }
		 
		 return correct;
		 
		 
	}
	public void getTxt(String txt)
	{
		System.out.println(txt);
	}
	
	
	public void sendChatText(String txt) throws IOException
	{
		outToServer = new DataOutputStream(getSocket().getOutputStream()); 
		outToServer.writeBytes(txt);
		
		outToServer.flush();
		
	}
	
	public void setSocket(String ip, int port) throws Exception
	{
		clientSocket = new Socket(ip, port);
	}
	
	public Socket getSocket()
	{
		return clientSocket;
	}
	public boolean closeConnection()
	{
		try {
			clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(clientSocket.isClosed())
		{
			correct = true;
		}
		else
		{
			correct = false;
		}
		
		
		return correct;
	}
	
	
	

}

