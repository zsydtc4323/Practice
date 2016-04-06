import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class EchoServer {
	
	private int port = 8000;
	private ServerSocket serverSocket;
	

	public EchoServer()throws IOException{
		serverSocket = new ServerSocket(port);
		System.out.println("Server Starts!");
	}
	
	public String echo(String msg){
		return "echo: " + msg;
	}
	
	private PrintWriter getWriter(Socket socket) throws IOException{
		OutputStream socketOut = socket.getOutputStream();
		return new PrintWriter(socketOut, true);
	} 
	
	private BufferedReader getReader(Socket socket) throws IOException{
		
		InputStream socketIn = socket.getInputStream();
		return new BufferedReader(new InputStreamReader(socketIn));
	}
	
	public void service(){
		while(true){
			
			Socket socket = null;
			try{
				socket = serverSocket.accept();
				System.out.println("New connection accepted " + socket.getInetAddress() + ":" + socket.getPort());
				
				BufferedReader reader = getReader(socket);
				PrintWriter writer = getWriter(socket);
				
				
				String msg = null;
				while ((msg=reader.readLine()) != null){
					System.out.println(msg);
					writer.println(echo(msg));
					
					if(msg.equals("bye"))
						break;
				}
			}catch(IOException e){
				e.printStackTrace();
			}finally{
				
				try {
					if (socket!=null) socket.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		}
	}
	
	static public void  main(String[] args) throws IOException {
		new EchoServer().service();
	}
}
