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

public class ServerThread {
	private Socket socket;
    private String userName;
    private boolean isAlived;
    private final LinkedList<String> messagesToSend;
    private boolean hasMessages = false;

    public ServerThread(Socket socket, String userName){
        this.socket = socket;
        this.userName = userName;
        messagesToSend = new LinkedList<String>();
    }

    public void addNextMessage(String message){
        synchronized (messagesToSend){
            hasMessages = true;
            messagesToSend.push(message);
        }
    }
    public void run() {
		try {

			String content = null;

			while ((content = readFromClient()) != null) {
				for (Socket s : ChatServer.sockets) {
					PrintStream ps;
					ps = new PrintStream(s.getOutputStream());
					ps.println(content);
				}

			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
 
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
