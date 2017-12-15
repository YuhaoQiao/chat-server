package chatserver;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.util.Map;
import java.util.Map.Entry;

public class ServerThread implements Runnable {

	BufferedReader br;
	Socket s;
	int id;

	public ServerThread(Socket s) throws IOException {
		// TODO Auto-generated constructor stub
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
	}

	
	// constantly receive all the message from clients when connect
	@Override
	public void run() {
		try {
			/*
			 * System.out.println(
			 * "JOINED_CHATROOM"+Thread.currentThread().getName()
			 * +"SERVER_IP"+s.getLocalAddress() +"PORT"+s.getLocalPort()
			 * +"ROOM_REF"+id +"JOIN_ID"+s.getInetAddress().getHostAddress());
			 */
			// TODO Auto-generated method stub
			
			String content = null;

			while ((content = readFromClient()) != null) {
				for (Socket s : Server.sockets) {
					PrintStream ps;
					// push all messages to all Sockets
					ps = new PrintStream(s.getOutputStream());

					ps.println(content);
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// method to read message from clients
 
    private String readFromClient() throws Exception {

    	String readCon = br.readLine();
    	System.out.println(readCon);

    	if (readCon.equalsIgnoreCase("HELLO")) {
    		System.out.println("I am in hello");
    	} else if (readCon.equalsIgnoreCase("KILL_SERVICE")) {
    		this.s.close();
    		Server.sockets.remove(s);
    		System.out.println("closed socket");
   		} else if (readCon.equalsIgnoreCase("HELLO text")) {

    		System.out.println("HELO text" + "\"IP:[ip address]" + s.getInetAddress().getHostAddress() + "\n"
    				+ "Port:[port number]" + ((InetSocketAddress) s.getRemoteSocketAddress()).getPort() + "\n"
    				+ "StudentID:[your student ID]" + 12345 + "\n");

    	}

    	return null;    	
      try { 
        dataInputStream.close(); 
        socket.close(); 
      } catch (IOException e) { 
        e.printStackTrace(); 
      } 
    } 
}
