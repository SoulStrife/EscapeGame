
public class Container extends WorldObject{
	private int x;					//x value in room
	private int y;					//y value in room
	private String contains;		//tool container has
	
	public Container(int x, int y, String contains){
		super('C', true, "Container");
		this.x = x;
		this.y = y;
		this.contains = contains;
	}
	
	public String isOpened(){
		return contains;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public String toString(){
		return x + " " + y + " " + contains;
	}
}
