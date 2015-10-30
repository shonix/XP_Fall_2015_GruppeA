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
	String username;
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
		 setUsername(username);
		 outToServer = new DataOutputStream(getSocket().getOutputStream()); 
		 
		 outToServer.writeBytes("LOGIN"+ splitChar + getUsername() + splitChar + Password + '\n' );
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
	public void makeMove(int x, int y)
	{
		 try {
			outToServer = new DataOutputStream(getSocket().getOutputStream());
		
		 outToServer.writeBytes("MOVE"+ splitChar + 3 + splitChar + 3 + '\n' );
		 outToServer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		} 
		 
	}
	public void requestParty(String oppent)
	{
		try {
		outToServer = new DataOutputStream(getSocket().getOutputStream()); 
		
			outToServer.writeBytes("PARTY" + splitChar + oppent + getUsername() + '\n');
		
		
		outToServer.flush();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void getTxt() throws Exception
	{
	
		String txt;
	
		 in = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
		
		 while (in != null)
		 {	
			
			txt = in.readLine().toString();
			
			String split = String.valueOf(splitChar);
			
			String[] parts = txt.split(split);
			String Chat = parts[0]; // 004
			String Message = parts[1]; // 034556
			String userName = parts[2]; // 004
			
			System.out.println(Message);
			System.out.println(userName);
			 ChatNode.updateChats(userName, Message);
			 
			 if(Chat.equals("CONNCLOSE"))
			 {
				 
					this.interrupt();
					System.out.println("du er nu logget af");
			 }
			 if(Chat.equals("FALSE"))
			 {
				 System.out.println("dit move er ikke okay.");
				 // gui.falsemove();
			 }
			 
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
	public void setUsername(String userName)
	{
		username = userName;
	}
	public String getUsername()
	{
		return username;
	}
	
	
	

}

