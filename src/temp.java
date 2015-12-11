import java.io.IOException;

public class temp {

	public static void main(String[] args) throws IOException {
		Room r1 = new Room("r1.txt");
		Player p = new Player(3,7);
		r1.printRoom();
		r1.createPlayer(3, 7);
		r1.printRoom();
		
		r1.movePlayer(0, 2, p);
		r1.printRoom();
		
		r1.movePlayer(0, -2, p);
		r1.printRoom();
		
		r1.movePlayer(-4, 0, p);
		r1.printRoom();
		//r1.saveRoom("r1.txt");
		r1.movePlayer(-4, 0, p);
		r1.printRoom();
		
		r1.movePlayer(0, -3, p);
		r1.printRoom();
		
		r1.movePlayer(24, 0, p);
		r1.printRoom();
		
		r1.movePlayer(0, 6, p);
		r1.printRoom();
	}

}
