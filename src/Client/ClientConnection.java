package Client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import Gui.ChatNode;
import Gui.GameBoard;


public class ClientConnection extends Thread{
	
	Socket clientSocket;
	DataOutputStream outToServer;
	boolean correct = false;
	BufferedReader in;
	GameBoard gb;
	String username;
        char splitChar = (char) 007;
        
	public ClientConnection ()
	{
		
	}
	
	public void run() {
		
		
		
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
	public void makeMove(int x, int y, GameBoard g)
	{
		setGameboard(g);
		 try {
			 
			System.out.println( getSocket().toString());
			 System.out.println(x);
			outToServer = new DataOutputStream(getSocket().getOutputStream());
		System.out.println(x);
		 outToServer.writeBytes("MOVE"+ splitChar + x + splitChar + y + '\n' );
		 
		 
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
			
			
			
			
			if(Chat.equals("CLIENT"))
				{
				System.out.println(Message);
				System.out.println(userName);
				}
			
			if(Chat.equals("CHAT"))
			{
			 ChatNode.updateChats(userName, Message);
			}
			 
			 if(Chat.equals("CONNCLOSE"))
			 {
				 
					this.interrupt();
					System.out.println("du er nu logget af");
			 }
			 if(Chat.equals("FALSE"))
			 {
				 System.out.println("dit move er ikke okay.");
				 gb.setTic(Message,false);
			 }
			 
			 if(Chat.equals("TRUE"))
			 {
				 System.out.println("legit");
				 gb.setTic(Message,true);
				 //gui.ValidMove(True, Message);
			 }
			 
			 if(Chat.equals("EXMOVE"))
			 {
				 gb.setTic(Message,true);
//				 gui.makemove(Message, userName);
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
	
	public void amX()
	{
		try{
			outToServer = new DataOutputStream(getSocket().getOutputStream()); 
			outToServer.writeBytes("X" + splitChar + getUsername()+ '\n');
			
			outToServer.flush();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	public void amY()
	{
		try{
			outToServer = new DataOutputStream(getSocket().getOutputStream()); 
			outToServer.writeBytes("Y" + splitChar + getUsername()+ '\n');
			
			outToServer.flush();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	public void setUsername(String userName)
	{
		username = userName;
	}
	public String getUsername()
	{
		return username;
	}
	
	public void newGame()
	{
		try{
			outToServer = new DataOutputStream(getSocket().getOutputStream()); 
			outToServer.writeBytes("NEW" + splitChar + getUsername()+ '\n');
			
			outToServer.flush();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	public void setGameboard(GameBoard g)
	{
		gb = g ;
		
	}
	
	

}

