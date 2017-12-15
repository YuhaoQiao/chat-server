package chatserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	static ServerSocket serverSocket = null;
	ExecutorService executorService = null;
	
	public static void main(String[] args) {
		try {
			serverSocket = new ServerSocket(8888);
			ExecutorService executorService = Executors.newFixedThreadPool(5);
			while (true) {
				Socket socket = serverSocket.accept();
				ThreadPool threadPool = new ThreadPool(socket);
				executorService.execute(threadPool);
	
			}
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}
class ThreadPool extends Thread{

	Socket socket = null;
	BufferedReader br = null;
	String message = null;
	InputStreamReader isr = null;
	OutputStream os = null;
	PrintWriter pw = null;
	
	public ThreadPool(Socket socket) {
		this.socket = socket;
	} 
	
	public void run() {
		
		
		try {		
			String ip=InetAddress.getLocalHost().getHostAddress().toString();
			
			isr = new InputStreamReader(socket.getInputStream());
			br = new BufferedReader(isr);

			os = socket.getOutputStream();
			pw = new PrintWriter(os);
			while((message = br.readLine())!=null) {
				pw.println("HELO text\nIP: " + ip + "\nPort: " + socket.getPort() + "\nStudentID: 17303647");
				pw.flush();
				System.out.println(message);
			}
		} catch (IOException e) {
			System.out.println("Port " + socket.getPort() + " leave");
		}
	}
}