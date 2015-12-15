import java.util.ArrayList;

public class UseableWorldObject extends WorldObject {
	private String sUsed;		//message returned when successfully used
	private String fUsed;		//message returned when unsuccessfully used
	private String tool;		//tool used for object to be successfully used
	private String gainedTool;
	private int x;
	private int y;
	
	public UseableWorldObject(int x, int y, char icon, boolean useable, String descrip,
			String sUsed, String fUsed, String tool, String gainedTool){
		super(icon, useable, descrip);
		this.x = x;
		this.y = y;
		this.sUsed = sUsed;
		this.fUsed = fUsed;
		this.tool = tool;
		this.gainedTool = gainedTool;
	}
	
	/*
	 * Constructor for temp WorldObject for finding objects in ArrayList
	 */
	public UseableWorldObject(char icon, int y, int x){
		super(icon, false, "");
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
			inventory.add(gainedTool);
			return sUsed;
		}
		return fUsed;
	}
	
	public boolean equals(Object o){
		if(!(o instanceof UseableWorldObject)){
			return false;
		}
		
		return (x == ((UseableWorldObject)o).getX()) && (y == ((UseableWorldObject)o).getY()) && (this.getIcon() == ((WorldObject)o).getIcon());
	}
	
	public String toString(){
		return super.toString() + " " + x + " " + y + "\n" + sUsed + "\n" + fUsed + "\n" + tool;
	}
}
