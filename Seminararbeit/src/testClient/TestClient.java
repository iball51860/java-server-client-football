package testClient;

import java.io.*;
import java.net.*;

import control.*;
import main.WMTournament;


public class TestClient extends Thread{
	
	private static int count;
	private Socket socket;
	private BufferedReader fromServer;
	private PrintWriter toServer;
	private boolean runForrestRun = true;
	private String name;
	
	
	public TestClient(int port) //TODO delete constructor
	{
		this(port, "dummy");
	}
	
	public TestClient(int port, String name)
	{
		try 
		{
			count++;
			socket = new Socket(InetAddress.getLocalHost(), port);
			this.name = name;
			this.setName("TestClientThread-" + count); //set Name of Thread, NOT of the Team
		} 
		catch (UnknownHostException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Communication with Server
	 * 1. Reader + Writer gets initialized
	 * 2. Reader reads String from Server
	 * 3. Switch case generates some answers with using the method below
	 */
	public void run()
	{
		try 
		{
			fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			toServer = new PrintWriter(socket.getOutputStream(), true);
		} 
		catch (IOException ioe) 
		{
			ioe.printStackTrace();
		}
		
		while(runForrestRun)
		{
			String message = "default";
			String frontSubString = "default";
			String backSubString = "default";
			try
			{
				if((message = fromServer.readLine()) != null)
				{
					frontSubString = message.substring(0, 3);
					//backSubString = message.substring(3);
				}
			}
			catch(IOException ioe)
			{
				ioe.printStackTrace();
			}
			switch(frontSubString)
			{
				case Communication.NAME:
					sendToServer(name);
					break;
				case Communication.STRENGTH:
					break;
				case Communication.NEWGAME:
					break;
				case Communication.NEWROUND:
					break;
				case Communication.NEWMATCH:
					break;
				case Communication.SHOOT:
					sendToServer(generateDecision());
					break;
				case Communication.KEEP:
					sendToServer(generateDecision());
					break;
				case Communication.SHOTRESULT:
					break;
				case Communication.MATCHRESULT:
					break;
				case Communication.GAMEOVER:
					runForrestRun = false;
					break;
				default:
					System.out.println(message);
			}
		}
	}
	
	
	/**
	 * Method to send Strings to the Server
	 * @param msg
	 */
	private void sendToServer(String msg)
	{
		toServer.println(msg);
	}
	
	private String generateDecision()
	{
		double random = Math.random();
		if(random < 1/3) 
		{
			return "l";
		}
		if(random > 2/3)
		{
			return "m";
		}
		return "r";
	}
	
	public Socket getSocket()
	{
		return this.socket;
	}
	
}
