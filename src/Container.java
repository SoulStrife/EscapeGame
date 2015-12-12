
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
	
	public Container(int y, int x){
		super('C', true, "Container");
		this.x = x;
		this.y = y;
		contains = null;
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
	
	public boolean equals(Object o){
		if(!(o instanceof Container)){
			return false;
		}
		
		return (x == ((Container)o).getX()) && (y == ((Container)o).getY()) && (this.getIcon() == ((WorldObject)o).getIcon());
	}
}
