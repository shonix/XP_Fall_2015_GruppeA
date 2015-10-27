package Client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientConnection {
	
	public static void main(String args[]) throws IOException 
	{
		System.out.println("starting TCPClient main"); 
        String sentence; 
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); 
        System.out.println("trying to connect"); 
        Socket clientSocket = new Socket("10.111.176.136", 7778); 
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream()); 
        System.out.print("Please type your text: "); 
        
        while(clientSocket.isConnected()){
	        sentence = inFromUser.readLine(); 
	        outToServer.writeBytes(sentence + '\n'); 
        }
        clientSocket.close(); 
	}

}
