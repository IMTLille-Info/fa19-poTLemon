package potlemon.core.model;

/**
 * This class represents a character.
 */
public class Character {
	
	private String name;
	private Team team;
	private Inventory inventory;
	
	public Character(){
		
	}
	
	public Character(String name, Team team, Inventory inventory){
		this.name = name;
		this.team = team;
		this.inventory = inventory;
	}
	
	public Character(String name, Team team){
		this.name = name;
		this.team = team;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
	public Inventory getInventory(){
		return inventory;
	}
	
	
}
