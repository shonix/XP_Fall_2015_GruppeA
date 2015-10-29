package Client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import Gui.ChatNode;


public class ClientConnection extends Thread{
	
	Socket clientSocket;
	DataOutputStream outToServer;
	boolean correct = false;
	BufferedReader in;
	
        char splitChar = (char) 007;
        
	public ClientConnection ()
	{
		
	}
	
	public void run() {
		
		try {
		    Thread.sleep(500);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		if(getSocket().isClosed())
		{
			this.interrupt();
		}
		
		
		while(this.isAlive())
		{
			try {
				getTxt();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}
	

	
	public boolean makeConnection(String username, String Password, String ip, int port) throws Exception
	{
		 setSocket(ip, port); 
		 outToServer = new DataOutputStream(getSocket().getOutputStream()); 
		 
		 outToServer.writeBytes("LOGIN"+ splitChar + username + splitChar + Password + '\n' );
		 outToServer.flush();
		 
		 
		 
		 if (getSocket().isConnected())
		 {
			 this.start();
			 correct = true;
			 System.out.println(correct);
		 }
		 else
		 {
			 correct = false;
		 }
		 
		 return correct;
		 
		 
	}
	public void getTxt() throws Exception
	{
	
		String txt;
	
		 in = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
		
		 while (in != null)
		 {	
			
			txt = in.readLine().toString();
			 ChatNode.updateChats("claus", txt);
			 
		 }
		 
		 
	
	}
	
	
	public void sendChatText(String txt) throws IOException
	{
		outToServer = new DataOutputStream(getSocket().getOutputStream()); 
		outToServer.writeBytes("CHAT" + splitChar + txt + '\n');
		
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
			outToServer = new DataOutputStream(getSocket().getOutputStream()); 
			
			outToServer.writeBytes("EXIT" + '\n');
			
			clientSocket.close();
			this.interrupt();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(clientSocket.isClosed())
		{
			correct = true;
			System.out.println(correct);
		}
		else
		{
			correct = false;
		}
		
		
		return correct;
	}
	
	
	

}

