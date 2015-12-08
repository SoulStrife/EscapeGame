import java.util.ArrayList;

public class UseableWorldObject extends WorldObject {
	private String sUsed;		//message returned when successfully used
	private String fUsed;		//message returned when unsuccessfully used
	private String tool;		//tool used for object to be successfully used
	private int x;
	private int y;
	
	public UseableWorldObject(char icon,int x, int y, String descrip, boolean useable,
			String sUsed,String fUsed,String tool){
		super(icon, descrip, useable);
		this.x = x;
		this.y = y;
		this.sUsed = sUsed;
		this.fUsed = fUsed;
		this.tool = tool;
	}
	
	/*
	 * Constructor for temp WorldObject for finding objects in ArrayList
	 */
	public UseableWorldObject(int x, int y){
		super(' ',"",false);
		this.x = x;
		this.y = y;
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
	
	public boolean isEquals(Object o){
		if(!this.equals(o)){
			return false;
		}
		
		return (x == ((UseableWorldObject)o).getX()) && (y == ((UseableWorldObject)o).getY());
	}
}
