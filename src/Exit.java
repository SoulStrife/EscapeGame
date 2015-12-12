
public class Exit extends WorldObject{
	private int x;
	private int y;
	
	private String roomName;
	private int roomX;
	private int roomY;
	
	public Exit(int x, int y, String roomName, int roomX, int roomY){
		super('=',true,"Exit");
		this.x = x;
		this.y = y;
		this.roomName = roomName;
		this.roomX = roomX;
		this.roomY = roomY;
	}
	
	public Exit(String roomName){
		super('=',true,"Exit");
		x = -1;
		y = -1;
		this.roomName = roomName;
		roomX = -1;
		roomY = -1;
	}
	
	public int getX(){
		return x;
	}
	
	public int getRoomX(){
		return roomX;
	}
	
	public int getY(){
		return y;
	}
	
	public int getRoomY(){
		return roomY;
	}
	
	public String getRoom(){
		return roomName;
	}
	
	public boolean equals(Object o){
		if(!(o instanceof Exit)){
			return false;
		}
		
		return (((Exit)o).getX() == x) && (((Exit)o).getY() == y);
	}
}
