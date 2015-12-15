import java.io.*;
import java.util.ArrayList;

public class Room {
	String name;
	char[][] room;
	ArrayList<WorldObject> objects;
	ArrayList<UseableWorldObject> gameObjects;
	ArrayList<Container> containers;
	ArrayList<Exit> exits;
	WorldObject currentLoc;
	
	/**
	 * Room constructor
	 * 
	 * @param file the file to be opened that contains the room
	 */
	Room(String rFile) throws IOException{
		room = new char[8][20];
		objects = new ArrayList<WorldObject>();
		gameObjects =  new ArrayList<UseableWorldObject>();
		containers =  new ArrayList<Container>();
		exits =  new ArrayList<Exit>();
		currentLoc = null;
		
		try(
				BufferedReader in = new BufferedReader(new FileReader(rFile));
		){
			//reads in room
			int c;
			for(int i = 0; i < room.length; i++){
				for(int x = 0; x < room[0].length; x++){
					if((c = in.read()) != -1){
						if(c == 13 || c == 10){ // skips escape and carriage return characters
							x--;
						}
						else{
							room[i][x] = (char)c;
						}
					}
				}
			}
			
		}
		
		try(
				BufferedReader in = new BufferedReader(new FileReader(rFile));
			){
				//skips room
				for(int i = 0; i < room.length; i++){
					in.readLine();
				}
				
				//read in room name
				name = in.readLine();
				
				//read in World Objects
				String line = in.readLine();
				WorldObject temp;
				while(!(line.equals("End World Objects"))){
					temp = new WorldObject(line.charAt(0), line.substring(2, 3).equals("1"), line.substring(4));
					objects.add(temp);
					line = in.readLine();
				}
				
				//read in Containers
				line = in.readLine();
				while(!(line.equals("End Containers"))){
					ArrayList<String> tempStrings = new ArrayList<String>(); 
					for(int i = 0; i < 2; i++){
						tempStrings.add(line.substring(0, line.indexOf(' ')));
						line = line.substring(line.indexOf(' ') + 1);
					}
						
					temp = new Container(Integer.parseInt(tempStrings.remove(0)), Integer.parseInt(tempStrings.remove(0)), line);
					containers.add((Container)temp);
					line = in.readLine();
				}
				
				//read in Exits
				line = in.readLine();
				while(!(line.equals("End Exits"))){
					ArrayList<String> tempStrings = new ArrayList<String>(); 
					for(int i = 0; i < 4; i++){
						tempStrings.add(line.substring(0, line.indexOf(' ')));
						line = line.substring(line.indexOf(' ') + 1);
					}
					
					temp = new Exit(Integer.parseInt(tempStrings.remove(0)), Integer.parseInt(tempStrings.remove(0)), line, Integer.parseInt(tempStrings.remove(0)), Integer.parseInt(tempStrings.remove(0)), in.readLine());
					exits.add((Exit)temp);
					line = in.readLine();
				}
				
				//read in Usable World Objects
				line = in.readLine();
				while(!(line.equals("End of File"))){
					ArrayList<String> tempStrings = new ArrayList<String>(); 
					for(int i = 0; i < 4; i++){
						tempStrings.add(line.substring(0, line.indexOf(' ')));
						line = line.substring(line.indexOf(' ') + 1);
					}
					
					temp = new UseableWorldObject(Integer.parseInt(tempStrings.remove(0)), Integer.parseInt(tempStrings.remove(0)),
							tempStrings.remove(0).charAt(0), tempStrings.remove(0).equals("1"), line, in.readLine(), in.readLine(), in.readLine(), in.readLine());
					gameObjects.add((UseableWorldObject)temp);
					line = in.readLine();
				}
			}
	}

	/**
	 * Prints the room
	*/
	public void aprintRoom(){
		System.out.println(name);
		for(int i = 0; i < room.length; i++){
			for(int x = 0; x < room[0].length; x++){
				System.out.print(room[i][x]);
			}
			System.out.println();
		}
		if(currentLoc != null){
			System.out.println(currentLoc.getIcon() + ": " + currentLoc.getDecription());
		}
	}
	
	/**
	 * Returns a string of the room
	 * @return string of room
	 */
	public String toString() {
		String out = "";
		for (int i = 0; i < room.length; i++){
			for(int x = 0; x < room[0].length; x++){
				out += room[i][x];
			}
			out += "\n\r";
		}
		
		out += name + "\n\r";
		if(currentLoc != null){
			out += (currentLoc.getIcon() + ": " + currentLoc.getDecription()) + "\n\r";
		}
		
		return out;
	}
	
	/**
	 * Returns room array
	 * @return room array
	 */
	public char[][] getChars() {
		return room;
	}
	
	/**
	 * Gets the name of room
	 * @param name of room
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Saves the room to specified file
	 * 
	 * @param file the file the room will be saved to
	 */
	public void saveRoom(String file) throws IOException{
		try(FileWriter out = new FileWriter(new File(file))){
			for(int i = 0; i < room.length; i++){
				for(int x = 0; x < room[0].length; x++){
					out.write(room[i][x]);
				}
				out.write("\n");
			}
		}
	}
	
	public void printCurrentLoc(){
		System.out.println(currentLoc);
	}

	/**
	 * Moves player around the map
	 * *note* can only have one of either dx or dy a value != 0 at a time
	 * 
	 * @param dx change in x value
	 * @param dy change in y value
	 * @param p Player object
	 */
	public void movePlayer(int dx, int dy, Player p){
		if (dx > 0) {
			int count = 0;
			room[p.getY()][p.getX()] = currentLoc.getIcon();
			
			while ((count < dx) && ((p.getX() + count) < room[0].length - 1) && (room[p.getY()][p.getX() + count] != ' ')) {
				count++;
			}
			if ((room[p.getY()][p.getX() + count] == ' ')) {count--;}
			
			boolean matched = false;
			
			WorldObject temp = new UseableWorldObject(room[p.getY()][p.getX() + count], p.getX() + count, p.getY());
			if(gameObjects.indexOf(temp) != -1) {
				currentLoc = gameObjects.get(gameObjects.indexOf(temp));
				room[p.getY()][p.getX() + count] = Player.ICON;
				matched = true;
			}
			
			temp = new Container(p.getX() + count, p.getY());
			if(!matched &&(containers.indexOf(temp) != -1)){
				currentLoc = containers.get(containers.indexOf(temp));
				room[p.getY()][p.getX() + count] = Player.ICON;
				matched = true;
			}
			
			temp = new Exit(p.getX() + count, p.getY());
			if(!matched &&(exits.indexOf(temp) != -1)){
				currentLoc = exits.get(exits.indexOf(temp));
				room[p.getY()][p.getX() + count] = Player.ICON;
				matched = true;
			}
			
			temp = new WorldObject(room[p.getY()][p.getX() + count]);
			if(!matched && (objects.indexOf(temp) != -1)){
				currentLoc = objects.get(objects.indexOf(temp));
				room[p.getY()][p.getX() + count] = Player.ICON;
				matched = true;
			}
			p.addX(count);
			
		} else if (dx < 0) {
			int count = 0;
			room[p.getY()][p.getX()] = currentLoc.getIcon();
			
			while ((count > dx) && ((p.getX() + count - 1) >= 0) && (room[p.getY()][p.getX() + count] != ' ')) {
				count--;
			}
			if ((room[p.getY()][p.getX() + count] == ' ')) {count++;}
			
			boolean matched = false;
			
			WorldObject temp = new UseableWorldObject(room[p.getY()][p.getX() + count], p.getX(), p.getY() + count);
			if(gameObjects.indexOf(temp) != -1){
				currentLoc = gameObjects.get(gameObjects.indexOf(temp));
				room[p.getY()][p.getX() + count] = Player.ICON;
				matched = true;
			}
			
			temp = new Container(p.getX() + count, p.getY());
			if(!matched && (containers.indexOf(temp) != -1)){
				currentLoc = containers.get(containers.indexOf(temp));
				room[p.getY()][p.getX() + count] = Player.ICON;
				matched = true;
			}
			
			temp = new Exit(p.getX() + count, p.getY());
			if(!matched &&(exits.indexOf(temp) != -1)){
				currentLoc = exits.get(exits.indexOf(temp));
				room[p.getY()][p.getX() + count] = Player.ICON;
				matched = true;
			}
			
			temp = new WorldObject(room[p.getY()][p.getX() + count]);
			if(!matched && (objects.indexOf(temp) != -1)){
				currentLoc = objects.get(objects.indexOf(temp));
				room[p.getY()][p.getX() + count] = Player.ICON;
				matched = true;
			}
			p.addX(count);
			
		} else if (dy > 0) {
			int count = 0;
			room[p.getY()][p.getX()] = currentLoc.getIcon();
			
			while ((count < dy) && ((p.getY() + count) < room.length - 1) && (room[p.getY() + count][p.getX()] != ' ')) {
				count++;
			}
			if(room[p.getY() + count][p.getX()] == ' '){count--;}
			
			boolean matched = false;
			
			WorldObject temp = new UseableWorldObject(room[p.getY() + count][p.getX()], p.getX() + count, p.getY());
			if(gameObjects.indexOf(temp) != -1){
				currentLoc = gameObjects.get(gameObjects.indexOf(temp));
				room[p.getY() + count][p.getX()] =  Player.ICON;
				matched = true;
			}
			
			temp = new Container(p.getX(), p.getY() + count);
			if(!matched &&(containers.indexOf(temp) != -1)){
				currentLoc = containers.get(containers.indexOf(temp));
				room[p.getY() + count][p.getX()] =  Player.ICON;
				matched = true;
			}
			
			temp = new Exit(p.getX(), p.getY() + count);
			if(!matched &&(exits.indexOf(temp) != -1)){
				currentLoc = exits.get(exits.indexOf(temp));
				room[p.getY() + count][p.getX()] = Player.ICON;
				matched = true;
			}
			
			temp = new WorldObject(room[p.getY() + count][p.getX()]);
			if(!matched && (objects.indexOf(temp) != -1)){
				currentLoc = objects.get(objects.indexOf(temp));
				room[p.getY() + count][p.getX()] =  Player.ICON;
				matched = true;
			}
			p.addY(count);
			
		} else if (dy < 0){
			int count = 0;
			room[p.getY()][p.getX()] = currentLoc.getIcon();
			
			while ((count > dy) && ((p.getY()  + count - 1) >= 0) && (room[p.getY() + count][p.getX()] != ' ')) {
				count--;
			}
			if (room[p.getY() + count][p.getX()] == ' ') { count++;}
			
			boolean matched = false;
			
			WorldObject temp = new UseableWorldObject(room[p.getY() + count][p.getX()], p.getX(), p.getY() + count);
			if(gameObjects.indexOf(temp) != -1){
				currentLoc = gameObjects.get(gameObjects.indexOf(temp));
				room[p.getY() + count][p.getX()] =  Player.ICON;
				matched = true;
			}
			
			temp = new Container(p.getX(), p.getY() + count);
			if(!matched &&(containers.indexOf(temp) != -1)){
				currentLoc = containers.get(containers.indexOf(temp));
				room[p.getY() + count][p.getX()] =  Player.ICON;
				matched = true;
			}
			
			temp = new Exit(p.getX(), p.getY() + count);
			if(!matched &&(exits.indexOf(temp) != -1)){
				currentLoc = exits.get(exits.indexOf(temp));
				room[p.getY() + count][p.getX()] = Player.ICON;
				matched = true;
			}
			
			temp = new WorldObject(room[p.getY() + count][p.getX()]);
			if(!matched && (objects.indexOf(temp) != -1)){
				currentLoc = objects.get(objects.indexOf(temp));
				room[p.getY() + count][p.getX()] =  Player.ICON;
				matched = true;
			}
			p.addY(count);
		}
	}
	
	/**
	 * Creates player at x, y value
	 * 
	 * @param x x coordinate of player
	 * @param y y coordinate of player
	 */
	public void createPlayer(int x, int y){
		
		WorldObject temp = new Exit(y, x);
		if((exits.indexOf(temp) != -1)){
			currentLoc = exits.get(exits.indexOf(temp));
			room[x][y] = Player.ICON;
		}
		
		temp = new WorldObject(room[x][y]);
		if((objects.indexOf(temp) != -1)){
			currentLoc = objects.get(objects.indexOf(temp));
			room[x][y] = Player.ICON;
		}
	}
	
	/**
	 * Sees if player is at a Container and opens it
	 * 
	 * @param p Player object
	 * @return Message of what player finds
	 */
	public String openContainer(Player p){
		if (currentLoc instanceof Container) {
			String temp;
			temp = ((Container)currentLoc).isOpened();
			p.addTool(temp);
			return "You open the container and find " + temp;
		} else {
			return "There is no container to open";
		}
	}
	
	/**
	 * Sees if player is at a usable object and sees if player can use it
	 * 
	 * @param p Player object
	 * @return Message of whether successful or not
	 */
	public String useObject(Player p){
		if (currentLoc instanceof UseableWorldObject) {
			return ((UseableWorldObject)currentLoc).isUsed(p.getInventory());
		} else {
			return "There is no object to use";
		}
	}
	
	/**
	 * Sees if player is at an exit and if he can use it
	 * Check ArrayList of rooms first and if not found printout message
	 * 
	 * @param p Player object
	 * @return either a room or a message
	 */
	public String exit(Player p){
		if (currentLoc instanceof Exit) {
			if(((Exit)currentLoc).isUsed(p.getInventory()).equals("The door opens")) {
				return ((Exit)currentLoc).getRoom();
			} else {
				return ((Exit)currentLoc).isUsed(p.getInventory());
			}
		} else {
			return "There is no door";
		}
	}
}
