package chat; 
import java.io.DataInputStream; 
import java.io.EOFException; 
import java.io.IOException; 
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.BindException; 
import java.net.ServerSocket; 
import java.net.Socket; 

public class ChatServer { 
  public static void main(String[] args) { 
 
    boolean isStart = false; 

    ServerSocket ss = null; 
 
    Socket socket = null; 
 
    DataInputStream dataInputStream = null; 
    try { 
 
      ss = new ServerSocket(8888); 
    } catch (BindException e) { 
      System.out.println("port under use"); 
 
      System.exit(0); 
    } catch (Exception e) { 
      e.printStackTrace(); 
    } 
    try { 
      isStart = true; 
      while (isStart) { 
        boolean isConnect = false; 
 
        socket = ss.accept(); 
        System.out.println("one client connect"); 
        isConnect = true; 
        while (isConnect) { 
 
          dataInputStream = new DataInputStream( 
              socket.getInputStream()); 
 
          String message = dataInputStream.readUTF(); 
          System.out.println("client says:" + message); 
        } 
      } 
    } catch (EOFException e) { 
      System.out.println("client closed!"); 
    } catch (Exception e) { 
      e.printStackTrace(); 
    } finally { 
    	
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

private static String readFromClient() {

	return null;
} 
}