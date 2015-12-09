import java.util.ArrayList;

public class Player {
	private ArrayList<String> inventory;		//Player inventory, holds tools
	private int x;								//x coordinate of player
	private int y;								//y coordinate of player
	
	public static char ICON = 'p';				//player icon
	
	/*
	 * Constructor for player
	 * @param x x coordinate of player
	 * @param y y coordinate of player
	 */
	public Player(int x, int y){
		inventory = new ArrayList<String>();
		this.x = x;
		this.y = y;
	}
	
	/*
	 * Constructor for player
	 * @param x x coordinate of player
	 * @param y y coordinate of player
	 * @param inventory Player's inventory
	 */
	public Player(int x, int y, ArrayList<String> inventory){
		this.inventory = inventory;
		this.x = x;
		this.y = y;
	}
	
	/*
	 * Returns players inventory
	 * @return Player's inventory
	 */
	public ArrayList<String> getInventory(){
		return inventory;
	}
	
	/*
	 * Adds tool to player's inventory
	 * @param tool tool to be added
	 */
	public void addTool(String tool){
		inventory.add(tool);
	}
	
	/*
	 * Returns x coordinate of player
	 * @return x coordinate of player
	 */
	public int getX(){
		return x;
	}
	
	/*
	 * Returns y coordinate of player
	 * @return y coordinate of player
	 */
	public int getY(){
		return y;
	}
	
	/*
	 * Adds dx to x coordinate of player
	 * @param dx value to be added to player
	 */
	public void addX(int dx){
		x += dx;
	}
	
	/*
	 * Adds dy to y coordinate of player
	 * @param dy value to be added to player
	 */
	public void addY(int dy){
		y += dy;
	}
	
	/*
	 * Sets a new value for the x coordinate
	 * @param x new x value
	 */
	public void setX(int x){
		this.x = x;
	}
	
	/*
	 * Sets a new value for the y coordinate
	 * @param y new y value
	 */
	public void setY(int y){
		this.y = y;
	}
}
