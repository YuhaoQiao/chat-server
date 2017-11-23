package chat; 
import java.io.DataInputStream; 
import java.io.EOFException; 
import java.io.IOException; 
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
 
      try { 
        dataInputStream.close(); 
        socket.close(); 
      } catch (IOException e) { 
        e.printStackTrace(); 
      } 
    } 
  } 
}