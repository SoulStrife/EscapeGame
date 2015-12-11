import java.io.*;
import java.util.ArrayList;

public class Room {
	String name;
	char[][] room;
	ArrayList<WorldObject> objects;
	ArrayList<UseableWorldObject> gameObjects;
	ArrayList<Container> containers;
	WorldObject currentLoc;
	
	/*
	 * Room constructor
	 * 
	 * @param file the file to be opened that contains the room
	 */
	Room(String rFile) throws IOException{
		room = new char[5][20];
		objects = new ArrayList<WorldObject>();
		gameObjects =  new ArrayList<UseableWorldObject>();
		containers =  new ArrayList<Container>();
		currentLoc = null;
		name = "";
		
		try(
			FileReader in = new FileReader(new File(rFile));
		){
			int c;
			for(int i = 0; i < room.length; i++){
				for(int x = 0; x < room[0].length; x++){
					if((c = in.read()) != -1){
						if(c == 13 || c == 10){
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
				for(int i = 0; i < room.length; i++){
					in.readLine();
				}
				
				String line = in.readLine();
				WorldObject temp;
				while(!(line.equals("End World Objects"))){
					temp = new WorldObject(line.charAt(0), line.substring(2, 3).equals("1"), line.substring(4));
					objects.add(temp);
					line = in.readLine();
				}
				
				line = in.readLine();
				while(!(line.equals("End Containers"))){
					temp = new Container(Integer.parseInt(line.substring(0, 1)), Integer.parseInt(line.substring(2, 3)), line.substring(4));
					containers.add((Container)temp);
					line = in.readLine();
				}
				
				line = in.readLine();
				while(!(line.equals("End of File"))){
					temp = new UseableWorldObject(Integer.parseInt(line.substring(0, 1)), Integer.parseInt(line.substring(2, 3)),
							line.charAt(4), line.substring(6, 7).equals("1"), line.substring(8), in.readLine(), in.readLine(), in.readLine());
					gameObjects.add((UseableWorldObject)temp);
					line = in.readLine();
				}
			}
	}
	
	/*
	 * Prints the room
	 */
	public void printRoom(){
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
	
	/*
	 * Gets the name of room
	 * @param name of room
	 */
	public String getName(){
		return name;
	}
	
	/*
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
			if(gameObjects.indexOf(temp) != -1){
				currentLoc = gameObjects.get(gameObjects.indexOf(temp));
				room[p.getY()][p.getX() + count] = Player.ICON;
				matched = true;
			}
			
			temp = new Container(p.getX(), p.getY() + count);
			if(!matched &&(containers.indexOf(temp) != -1)){
				currentLoc = containers.get(containers.indexOf(temp));
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
			
			temp = new Container(p.getX() + count, p.getY());
			if(!matched &&(containers.indexOf(temp) != -1)){
				currentLoc = containers.get(containers.indexOf(temp));
				room[p.getY() + count][p.getX()] =  Player.ICON;
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
			
			temp = new Container(p.getX() + count, p.getY());
			if(!matched &&(containers.indexOf(temp) != -1)){
				currentLoc = containers.get(containers.indexOf(temp));
				room[p.getY() + count][p.getX()] =  Player.ICON;
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
	
	public void createPlayer(int x, int y){
		
		/*WorldObject temp = new UseableWorldObject(room[x][y], x, y);
		if(gameObjects.indexOf(temp) != -1){
			currentLoc = gameObjects.get(gameObjects.indexOf(temp));
			matched = true;
		}
		
		temp = new Container(p.getX() + count, p.getY());
		if(!matched &&(containers.indexOf(temp) != -1)){
			currentLoc = containers.get(containers.indexOf(temp));
			matched = true;
		}*/
		
		WorldObject temp = new WorldObject(room[x][y]);
		if((objects.indexOf(temp) != -1)){
			currentLoc = objects.get(objects.indexOf(temp));
			room[x][y] = Player.ICON;
		}
	}
	
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
	
	public String useObject(Player p){
		if (currentLoc instanceof UseableWorldObject) {
			return ((UseableWorldObject)currentLoc).isUsed(p.getInventory());
		} else {
			return "There is no object to use";
		}
	}
}
