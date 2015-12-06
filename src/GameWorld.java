import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	
	/**
	 * The path to save and load games from
	 */
	private final String savePath;
	
	/**
	 * Creates a new GameWorld with save location at path.
	 * 
	 * @param path the path to look for the save files at
	 */
	public GameWorld(String savePath) {
		this.savePath = savePath;
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
	
	/**
	 * Gets the current save path
	 * 
	 * @return the path where games are saved
	 */
	public String getSavePath() {
		return savePath;
	}
	
	/**
	 * Save the given GameWorld to the given path. Works by
	 * serializing the GameWorld, and writing the output to
	 * the given file path.
	 * 
	 * @param game the GameWorld to be saved
	 * @param path the path to save to
	 * @throws IOException thrown if save file is corrupted
	 */
	public static void save(GameWorld game, String path) throws IOException {
		
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(path)));
		out.writeObject(game);
		out.close();
		
	}
	
	/**
	 * Load a saved game from the given file path. Works by
	 * deserializing a stored GameWorld from the given file
	 * path.
	 * 
	 * @param path the path to load the GameWorld from
	 * @return the loaded GameWorld
	 * @throws IOException thrown if the given file is not accessible
	 */
	public static GameWorld load(String path) throws IOException {
		
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(path)));
		GameWorld w;
		
		try {
			
			w = (GameWorld)in.readObject();
			in.close();
			
		}
		catch (ClassNotFoundException e) {
			w = null;
		}
		
		return w;
	}
	
}