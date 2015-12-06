import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

/**
 * Main class for running the server. This class
 * only handles creating ServerThread objects
 * when a new client connects, and outputs basic
 * server status messages.
 * 
 * @author Steven Bertolucci
 * @version 1.0 December 5th 2015
 * 
 */
public class Server {
	
	final static int port = 1221;
	static boolean continueLoop = true; //Change to false to exit listening loop
	
	/**
	 * Main method which handles creating the server
	 * socket and spawning new threads to handle client
	 * communication.
	 * 
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		
		try (ServerSocket ssoc = new ServerSocket(port)) {
			
			System.out.println("Server initialized on host " + InetAddress.getLocalHost() + " on port " + port);
			
			while (continueLoop) {
				new Thread(new ServerThread(ssoc.accept())).start();
				System.out.println("New client connected");
			}
		}
		catch (IOException e) {
			System.err.println("Error listening on port " + port);
		}
		finally {
			System.out.println("Server status: Shutting down");
		}
	}
}
