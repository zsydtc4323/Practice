import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;


public class EchoClient {

	private String host = "localhost";
	private int port = 8000;
	private Socket socket;
	
	public EchoClient()throws IOException {
		socket = new Socket(host, port);
	}
	
	
	private PrintWriter getWriter(Socket socket) throws IOException{
		
		OutputStream socketOut = socket.getOutputStream();
		return new PrintWriter(socketOut, true);
	}
	
	private BufferedReader getReader(Socket socket) throws IOException{
		
		InputStream socketIn = socket.getInputStream();
		
		return new BufferedReader(new InputStreamReader(socketIn));
	}
	
	private void talk() throws IOException{
		

		try {
			BufferedReader reader = getReader(socket);
			PrintWriter writer = getWriter(socket);
			
			BufferedReader localReader = new BufferedReader(new InputStreamReader(System.in));
			
			String msg = null;
			
			while((msg=localReader.readLine()) != null) {
				writer.println(msg);
				System.out.println(reader.readLine());
				
				if(msg.equals("bye")) break;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try{
				socket.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		new EchoClient().talk();
	}
}