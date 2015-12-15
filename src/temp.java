import java.io.IOException;

public class temp {

	public static void main(String[] args) throws IOException {
		Room r1 = new Room("MedBay.txt");
		Player p = new Player(4,7);
		System.out.print(r1.toString());
		r1.createPlayer(4, 7);
		System.out.print(r1.toString());
		
		r1.movePlayer(1, 0, p);
		System.out.print(r1.toString());
		
		r1.movePlayer(0, -1, p);
		System.out.print(r1.toString());
		
		r1.movePlayer(1, 0, p);
		System.out.print(r1.toString());
		
		r1.movePlayer(0, 1, p);
		System.out.print(r1.toString());
		
		r1.movePlayer(0, 1, p);
		System.out.print(r1.toString());
		
		r1.movePlayer(0, -1, p);
		System.out.print(r1.toString());
		
		r1.movePlayer(0, 1, p);
		System.out.print(r1.toString());
		
		r1.movePlayer(1, 0, p);
		System.out.print(r1.toString());
		
		r1.movePlayer(-1, 0, p);
		System.out.print(r1.toString());
		
		r1.movePlayer(1, 0, p);
		System.out.print(r1.toString());
		
		r1.movePlayer(0, -1, p);
		System.out.print(r1.toString());
		
		r1.movePlayer(-1, 0, p);
		System.out.print(r1.toString());
	}

}
