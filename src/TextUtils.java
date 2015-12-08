import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 * Provides utilities for reading and writing game information
 * to and from files. Includes methods for saving and loading
 * GameWorld objects. All public methods in this class throw
 * SaveFailExceptions, a subclass of IOException, which indicates
 * that the save file could not be loaded.
 * 
 * @author Steven Bertolucci
 * @version 2.1 December 6th, 2015
 *
 */
public class TextUtils {
	
	/**
	 * Constant path to the location of saved games.
	 * Change to save to a different location.
	 */
	private static final String SAVE_PATH = "saves.dat";
	
	/**
	 * Helper method to retrieve the save map from the
	 * file at the given path
	 * 
	 * @param file the file containing the save map
	 * @return the HashMap of the save files
	 * @throws SaveFailException if anything goes wrong retrieving the map
	 */
	@SuppressWarnings("unchecked")
	private static HashMap<String, GameWorld> getSaveMap(File file) throws SaveFailException {
		
		HashMap<String, GameWorld> saveMap;
		
		assert(file.exists());
		
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			saveMap = (HashMap<String,GameWorld>)in.readObject();
			in.close();
		}
		catch (FileNotFoundException e) {
			throw new SaveFailException();
		}
		catch (ClassNotFoundException e) {
			throw new SaveFailException();
		}
		catch (IOException e) {
			throw new SaveFailException();
		}
		
		return saveMap;
	}
	
	/**
	 * Helper method for writing the save map to the file
	 * 
	 * @param toWrite the file to write to
	 * @throws SaveFailException thrown if a save error occurs
	 */
	private static void setSaveMap(HashMap<String, GameWorld> toSet, File toWrite) throws SaveFailException {
		
		try {
			ObjectOutputStream oop = new ObjectOutputStream(new FileOutputStream(toWrite));
			oop.writeObject(toSet);
			oop.close();
		}
		catch (IOException e) {
			throw new SaveFailException();
		}
		
	}
	
	/**
	 * Create the initial save map at the given path
	 * 
	 * @param file the file containing the save map
	 * @throws SaveFailException thrown if the HashMap could not be written
	 */
	private static void createInitialSaveMap(File file) throws SaveFailException {
		
		HashMap<String, GameWorld> saveMap = new HashMap<String, GameWorld>();
		
		try {
			file.createNewFile();
			setSaveMap(saveMap, file);
		}
		catch (IOException e) {
			throw new SaveFailException();
		}
	}
	
	/**
	 * Save the given GameWorld to the file at the given path. This method calls
	 * the helper method createInitialSaveMap to ensure that the save file exists,
	 * then writes the game to the returned map. The map is then re-written to the
	 * file.
	 * 
	 * @param name the name of the save file
	 * @param game the game to be saved
	 * @throws SaveFailException thrown if a write failure occurs while saving the game
	 */
	public static void save(String name, GameWorld game) throws SaveFailException {
		
		File file = new File(SAVE_PATH);
		
		if (!file.exists()) {
			createInitialSaveMap(file);
		}
		
		HashMap<String, GameWorld> saveMap = getSaveMap(file);
		saveMap.put(name, game);
		setSaveMap(saveMap, file);
	}
	
	/**
	 * Loads the game with the specified name from the given path. This method assumes
	 * that the given file exists, and contains the save map.
	 * 
	 * @param name the specific name of the save file
	 * @return the loaded GameWorld
	 * @throws SaveFailException thrown if the save map cannot be retrieved from the given path
	 */
	public static GameWorld load(String name) throws SaveFailException {
		
		File file = new File(SAVE_PATH);
		
		if (!file.exists()) {
			throw new SaveFailException();
		}
		
		HashMap<String,GameWorld> saveMap = getSaveMap(file);
		GameWorld load = saveMap.get(name);
		
		if (load == null) {
			throw new SaveFailException();
		}
		
		return load;
		
	}
	
}
