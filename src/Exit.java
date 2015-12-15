import java.util.ArrayList;

public class Exit extends WorldObject{
	private int x;
	private int y;
	private String tool;
	
	private String roomName;
	private int roomX;
	private int roomY;
	
	public Exit(int x, int y, String roomName, int roomX, int roomY, String tool){
		super('=',true,"Exit");
		this.x = x;
		this.y = y;
		this.roomName = roomName;
		this.roomX = roomX;
		this.roomY = roomY;
		this.tool = tool;
	}
	
	public Exit(String roomName){
		super('=',true,"Exit");
		x = -1;
		y = -1;
		this.roomName = roomName;
		roomX = -1;
		roomY = -1;
		tool = "";
	}
	
	public Exit(int y, int x){
		super('=',true,"Exit");
		this.x = x;
		this.y = y;
		this.roomName = "";
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
	
	public String isUsed(ArrayList<String> inventory){
		if(inventory.contains(tool)){
			return "The door opens";
		}
		return "You need: " + tool;
	}
}
