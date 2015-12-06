import java.io.Serializable;

/**
 * The GameWorld class is the top-level game class
 * which contains everything needed to properly run
 * a complete game. It can be serialized or deserialized
 * for saving and loading purposes.
 * 
 * @author Steven Bertolucci
 * @version 0.1 Skeleton code only
 *
 */
public class GameWorld implements Serializable {
	
	/**
	 * The version of the class for serializing purposes
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Contains the list of valid commands that the
	 * server is able to parse.
	 */
	private final String[] commandList = {};
	
	private final String savePath;
	
	/**
	 * Creates a new GameWorld with save location at 
	 * the given path. Everything else about initial
	 * GameWorlds should be identical.
	 * 
	 * @param path the path to look for the save files at
	 */
	public GameWorld(String path) {
		savePath = path;
	}
	
	/**
	 * Parse the input into a keyword and an argument.
	 * Then, update the current GameWorld object to
	 * reflect the result of the command.
	 * 
	 * @param command the String to be parsed
	 * @throws InvalidSyntaxException thrown if a command cannot be parsed
	 */
	public void parseAndUpdate(String command) throws InvalidSyntaxException {
		
	}
	
	/**
	 * Get the win state of the game at the current moment.
	 * 
	 * @return true if the game has reached a win condition
	 */
	public boolean isWon() {
		return false;
	}
	
	/**
	 * Get the loss state of the game at the current moment.
	 * 
	 * @return true if the game has reached a loss condition
	 */
	public boolean isLoss() {
		return false;
	}
	
	/**
	 * Read the room that the player is currently in and parse
	 * it into a single, displayable String.
	 * 
	 * @return the String representation of the player's current room
	 */
	public String displayCurrentRoom() {
		return null;
	}
	
}