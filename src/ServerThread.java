import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * This class handles communication between the client
 * and the server. The communication and game flow logic
 * are contained herein. This class is run by the Server
 * class each time a new client connects, allowing for
 * multiple simultaneous clients.
 * 
 * @author Steven Bertolucci
 * @version 1.0 December 5th, 2015
 *
 */
public class ServerThread implements Runnable {
	
	private Socket csoc;
	private PrintWriter out; //PrintWriter wrapper for port output stream
	private BufferedReader in; //BufferedReader wrapper for port input stream
	
	/**
	 * Constructor for new ServerThread objects, which opens a connection
	 * between the given socket and the client. It also registers the input
	 * and output streams needed to communicate with the client.
	 * 
	 * @param soc the Socket to communicate on
	 */
	public ServerThread(Socket soc) {
		
		csoc = soc;
		
		try {
			out = new PrintWriter(csoc.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(csoc.getInputStream()));
		}
		catch (IOException e) {
			System.err.println("Failed to get IO streams from socket " + csoc);
		}
	}
	
	/**
	 * Starts the thread, and performs setup and execution
	 * of the game environment. All logic needed to communicate
	 * with the client is contained within this method.
	 */
	@Override
	public void run() {
		
		try {
			
			out.println("Welcome to the game server"); //Welcome message (temporary)
			
			String command;
			
			newGame : while (true) {
			
				out.println("Start a new game (y/n)?");
				command = in.readLine();
				
				if (command.equals("y") || command.equals("n")) {
					break newGame;
				}
				else {
					out.println("Please enter \"y\" to start a new game or \"n\" to load a game.");
					continue;
				}
			}
			
			//We now know whether or not to load a game
			assert (command.equals("y") || command.equals("n"));
			
			GameWorld game = null;
			
			if (command .equals("y")) {
				game = new GameWorld();
			}
			else if (command.equals("n")) {
				
				loadGame : while (true) {
					
					out.println("Please enter the name of your save file.");
					String saveName = in.readLine();
					
					if (saveName == null) {
						throw new IOException(); //The connection has been lost
					}
					
					try {
						game = GameWorld.load(saveName);
						break;
					} catch (IOException e) {
						out.println("Save file not found. Please enter a valid name. Valid names are:\n" + GameWorld.getSaveList());
						continue loadGame;
					}
				}
			}
			
			//The world is now loaded
			assert (game != null);
			
			gameLoop : while(!(game.isLoss() || game.isWon())) {
				
				out.print(game.displayCurrentRoom());
				
				validCommandLoop : while (true) {
					
					out.println("\nEnter a command: ");
					command = in.readLine();
					out.println();
					
					try {
						
						out.println(game.parseAndUpdate(command));
						
					} catch (InvalidSyntaxException e) {
						
						out.println("Invalid command, syntax should be \"ACTION OBJECT\"");
						continue validCommandLoop;
						
					} catch (GameExitException e) {
						out.println("Game successfully exited.");
						break gameLoop;
					}
					
					break validCommandLoop;
					
				}
			}
			
			out.println("Thank you for playing!");
		}
		
		catch (IOException | NullPointerException e) {
			//System.err.println("Error in stream, thread terminating");
			e.printStackTrace();
		}
	}
}
