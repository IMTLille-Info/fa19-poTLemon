package model;

public class Attack {

	String name;
	int attack;
	
	public Attack(String name, int attack) {
		this.name = name;
		this.attack = attack;
	}
	
	public int getAttack() {
		return attack;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
