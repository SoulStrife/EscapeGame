import java.io.*;
import java.util.ArrayList;

public class Room {
	char[][] room;
	ArrayList<WorldObject> objects;
	ArrayList<UseableWorldObject> gameObjects;
	ArrayList<Container> containers;
	
	/*
	 * Room constructor
	 * 
	 * @param file the file to be opened that contains the room
	 */
	Room(String rFile, String objectsFile, String gameObjectsFile) throws IOException{
		room = new char[5][20];
		objects = new ArrayList<WorldObject>();
		gameObjects =  new ArrayList<UseableWorldObject>();
		containers =  new ArrayList<Container>();
		
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
				BufferedReader in = new BufferedReader(new FileReader(objectsFile));
			){
				String line = in.readLine();
				WorldObject temp;
				while(!(line.equals("End World Objects"))){
					temp = new WorldObject(line.charAt(0), line.substring(2, 3).equals("1"), line.substring(4));
					objects.add(temp);
					line = in.readLine();
				}
				
				line = in.readLine();
				while(!(line.equals("End Containers"))){
					temp = new Container(Integer.parseInt(line.substring(0, 1)), Integer.parseInt(line.substring(2, 3)), line.substring(3));
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
		for(int i = 0; i < room.length; i++){
			for(int x = 0; x < room[0].length; x++){
				System.out.print(room[i][x]);
			}
			System.out.println();
		}
		System.out.println("WorldObjects");
		for(WorldObject i: objects){
			System.out.println(i);
		}
		
		System.out.println("Containers");
		for(Container i: containers){
			System.out.println(i);
		}
		
		System.out.println("UseableWorldObjects");
		for(UseableWorldObject i: gameObjects){
			System.out.println(i);
		}
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
}
