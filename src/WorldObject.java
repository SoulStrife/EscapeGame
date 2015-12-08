import java.util.ArrayList;

public class WorldObject {
	private char icon;
	private int x;
	private int y;
	private String descrip;		//description of object
	private String sUsed;		//message returned when successfully used
	private String fUsed;		//message returned when unsuccessfully used
	private String tool;		//tool used for object to be successfully used
	
	public WorldObject(char icon,int x, int y, String descrip,
			String sUsed,String fUsed){
		this.icon = icon;
		this.x = x;
		this.y = y;
		this.descrip = descrip;
		this.sUsed = sUsed;
		this.fUsed = fUsed;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public String isUsed(ArrayList<String> inventory){
		if(inventory.contains(tool)){
			return sUsed;
		}
		return fUsed;
	}
	
	public char getIcon(){
		return icon;
	}
	
	public String getDecription(){
		return descrip;
	}
}
