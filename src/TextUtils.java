import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Provides utilities for reading and writing game information
 * to and from files. Includes methods for saving and loading
 * GameWorld objects.
 * 
 * @author Steven Bertolucci
 * @version 1.0 December 5th, 2015
 *
 */
public class TextUtils {
	
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
