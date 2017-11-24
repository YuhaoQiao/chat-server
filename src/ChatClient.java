package chat; 
import java.awt.BorderLayout; 
import java.awt.Frame; 
import java.awt.TextArea; 
import java.awt.TextField; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.event.WindowAdapter; 
import java.awt.event.WindowEvent; 
import java.io.DataOutputStream; 
import java.io.IOException; 
import java.net.Socket; 
import java.net.UnknownHostException; 

public class ChatClient extends Frame { 
	
  private TextField tfTxt = new TextField(); 
 
  private TextArea tarea = new TextArea(); 
  private Socket socket = null; 
 
  private DataOutputStream dataOutputStream = null; 
  public static void main(String[] args) { 
    new ChatClient().launcFrame(); 
  } 


  public void connect() { 
    try { 
 
      socket = new Socket("127.0.0.1", 8888); 
 
      dataOutputStream = new DataOutputStream(socket.getOutputStream()); 
      System.out.println("server connect"); 
    } catch (UnknownHostException e) { 
      e.printStackTrace(); 
    } catch (IOException e) { 
      e.printStackTrace(); 
    } 
  } 

  public void disConnect() { 
    try { 
      dataOutputStream.close(); 
      socket.close(); 
    } catch (IOException e) { 
      e.printStackTrace(); 
    } 
  } 

  private void sendMessage(String text) { 
    try { 
      dataOutputStream.writeUTF(text); 
      dataOutputStream.flush(); 
    } catch (IOException e1) { 
      e1.printStackTrace(); 
    } 
  } 

  private class TFLister implements ActionListener { 

    public void actionPerformed(ActionEvent e) { 
      String text = tfTxt.getText().trim(); 
      tarea.setText(text); 
      tfTxt.setText(""); 
 
      sendMessage(text); 
    } 
  } 
}