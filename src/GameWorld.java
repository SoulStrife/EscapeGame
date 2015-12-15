import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

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
	
	ArrayList<Room> roomList;
	private static final String ROOM_FOLDER = "Rooms";
	private static final String START_ROOM = "MedBay";
	private static final int START_X = 7;
	private static final int START_Y = 4;
	private static final String SAVE_FOLDER = "Saves";
	private boolean isLoss = false;
	private boolean isWon = false;
	
	private Player player;
	
	/**
	 * The version of the class for serializing purposes
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Contains the list of valid commands that the
	 * server is able to parse. These commands (such
	 * as GET, PLACE, and so on) make up the first
	 * part of any command.
	 */
	private final String[] ACTIONS = {};
	
	/**
	 * Contains the list of all objects that can
	 * appear in the game. These objects are used
	 * as the arguments of some commands.
	 */
	private final String[] OBJECTS = {};
	
	/**
	 * Creates a new GameWorld with save location at 
	 * the given path. Everything else about initial
	 * GameWorlds should be identical.
	 */
	public GameWorld() {
		
		//Initialize the list of rooms
		roomList = new ArrayList<Room>();
		
		//Initialize the list of rooms from the folder Rooms
		File folder = new File(ROOM_FOLDER);
		File[] rooms = folder.listFiles();
		for (File room : rooms) {
			try {
				roomList.add(new Room(room.getPath()));
			} catch (IOException e) {
				continue;
			}
		}
		
		//Initialize the player
		player = new Player(START_Y, START_X, new ArrayList<String>(), START_ROOM);
		player.addTool("Soul");
		findRoom(player.getRoom()).createPlayer(START_Y, START_X);
		System.out.print(player);
	}
	
	/**
	 * Parse the input into a keyword and an argument.
	 * Then, update the current GameWorld object to
	 * reflect the result of the command.
	 * 
	 * @param command the String to be parsed
	 * @throws InvalidSyntaxException thrown if a command cannot be parsed
	 */
	public String parseAndUpdate(String command) throws InvalidSyntaxException, GameExitException {
		
		String[] parsed = command.split("\\s");
		
		switch(parsed[0].toUpperCase()) {
			
			case "MOVE" :
				assert (parsed.length == 3);
				try {
					int x = Integer.parseInt(parsed[1]);
					int y = Integer.parseInt(parsed[2]);
					findRoom(player.getRoom()).movePlayer(0, y, player);
					findRoom(player.getRoom()).movePlayer(x, 0, player);
					return "Player moved to " + parsed[1] + ", " + parsed[2];
				}
				catch (ArrayIndexOutOfBoundsException | NumberFormatException e){
					//throw new InvalidSyntaxException();
					e.printStackTrace();
				}
			case "QUIT" :
				assert (parsed.length == 1);
				throw new GameExitException();
			case "EXIT" :
				assert (parsed.length == 1);
				Exit ex = findRoom(player.getRoom()).exit(player);
				if (ex == null) {
					return "There is no door.";
				}
				String roomTo = ex.getRoom();
				int x = ex.getRoomX();
				int y = ex.getRoomY();
				player.setX(y);
				player.setY(x);
				
				if (ex.getCheck()) {
					player.getRoom(roomTo);
					player.setX(y);
					player.setY(x);
					findRoom(player.getRoom()).createPlayer(x, y);
					System.out.println(player.getX());
					System.out.println(player.getY());
					if (player.getRoom().equals("Outside")) {
						isLoss = true;
					}
					else if (player.getRoom().equals("Observation")) {
						isWon = true;
					}
					return "Moved";
				}
				else {
					return "You need " + ex.getTool();
				}
			case "OPEN" :
				assert (parsed.length == 1);
				return findRoom(player.getRoom()).openContainer(player);
			case "USE" :
				assert (parsed.length == 2);
				return findRoom(player.getRoom()).useObject(player);
			
			case "SAVE" :
				assert (parsed.length == 2);
				try {
					GameWorld.save(parsed[1], this);
					throw new GameExitException();
				}
				catch (ArrayIndexOutOfBoundsException | SaveFailException e) {
					return "Game failed to save correctly.";
				}
			case "INVENTORY" :
				assert (parsed.length == 1);
				return player.getInventory().toString();
			case "W" :
				assert (parsed.length == 1);
				try {
					findRoom(player.getRoom()).movePlayer(0, -1, player);
					return "Player moved to " + player.getX() + ", " + player.getY();
				}
				catch (ArrayIndexOutOfBoundsException | NumberFormatException e){
					throw new InvalidSyntaxException();
				}
			case "A" :
				assert (parsed.length == 1);
				try {
					findRoom(player.getRoom()).movePlayer(-1, 0, player);
					return "Player moved to " + player.getX() + ", " + player.getY();
				}
				catch (ArrayIndexOutOfBoundsException | NumberFormatException e){
					throw new InvalidSyntaxException();
				}
			case "S" :
				assert (parsed.length == 1);
				try {
					findRoom(player.getRoom()).movePlayer(0, 1, player);
					return "Player moved to " + player.getX() + ", " + player.getY();
				}
				catch (ArrayIndexOutOfBoundsException | NumberFormatException e){
					throw new InvalidSyntaxException();
				}
			case "D" :
				assert (parsed.length == 1);
				try {
					findRoom(player.getRoom()).movePlayer(1, 0, player);
					return "Player moved to " + player.getX() + ", " + player.getY();
				}
				catch (ArrayIndexOutOfBoundsException | NumberFormatException e){
					throw new InvalidSyntaxException();
				}
			case "PING" :
				return "Pong";
			
			default :
				throw new InvalidSyntaxException();
		}
		
	}
	
	private Room findRoom(String toFind) {
		for (Room r : roomList) {
			
			if (r.getName().equals(toFind)) {
				return r;
			}
		}
		System.out.println("Could not find name");
		return null;
	}
	
	/**
	 * Get the win state of the game at the current moment.
	 * 
	 * @return true if the game has reached a win condition
	 */
	public boolean isWon() {
		return isWon;
	}
	
	/**
	 * Get the loss state of the game at the current moment.
	 * 
	 * @return true if the game has reached a loss condition
	 */
	public boolean isLoss() {
		return isLoss;
	}
	
	/**
	 * Read the room that the player is currently in and parse
	 * it into a single, displayable String.
	 * 
	 * @return the String representation of the player's current room
	 */
	public String displayCurrentRoom() {
		return (findRoom(player.getRoom()).toString());
		
		//return findRoom(player.getRoom()).toString();
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
		
		File file = new File(SAVE_FOLDER + "\\" + name);
		
		if (file.exists()) {
			throw new SaveFailException();
		}
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))){
			out.writeObject(game);
		} catch (IOException e) {
			e.printStackTrace();
			throw new SaveFailException();
		}
	}
	
	public ArrayList<Room> getRoomList() {
		return roomList;
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
		
		File load = new File(SAVE_FOLDER + "\\" + name);
		GameWorld loaded = null;
		
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(load))) {
			loaded = (GameWorld)in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			throw new SaveFailException();
		}
		
		return loaded;
		
	}
	
	public static String getSaveList() {
		String out = "";
		File saveFolder = new File(SAVE_FOLDER);
		File[] saves = saveFolder.listFiles();
		for (File f : saves) {
			out += f.getName() + '\n';
		}
		return out;
	}
	
}