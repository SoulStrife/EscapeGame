

public class WorldObject {
	private char icon;
	private String descrip;		//description of object
	private boolean useable;	//true if object is useable
	
	
	public WorldObject(char icon, boolean useable, String descrip){
		this.icon = icon;
		this.descrip = descrip;
		this.useable = useable;
	}
	
	public WorldObject(char icon){
		this.icon = icon;
		this.descrip = null;
		this.useable = false;
	}

	public char getIcon(){
		return icon;
	}
	
	public String getDecription(){
		return descrip;
	}
	
	public boolean isUseable(){
		return useable;
	}
	
	public boolean equals(Object o){
		if(!(o instanceof WorldObject)){
			return false;
		}
		
		return icon == ((WorldObject)o).getIcon();
	}
	
	public String toString(){
		return icon + " " + descrip + " " + useable;
	}
}
