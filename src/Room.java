import java.io.*;

public class Room {
	char[][] room;
	/*
	 * Room constructor
	 * 
	 * @param file the file to be opened that contains the room
	 */
	Room(String file) throws IOException{
		room = new char[5][20];
		try(
			FileReader in = new FileReader(new File(file));
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
