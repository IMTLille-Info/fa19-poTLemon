package core;

public class Pokemon {
	
	private String name;
	private int pv;
	private int pvMax;
	private int level;
	private int attack;
	private int defense;
	

	public Pokemon() {
		
	}
	
	public Pokemon(int pv) {
		this.pvMax = pv;
		this.pv = pv;
	}

	/**
	 * Remove the amount of pv in parameter
	 * @param lost
	 */
	public void lostPV(int lost) {
		pv -= lost;
	}
	
	/**
	 * Remove the amount of pv in parameter
	 * @param lost
	 */
	public void addPV(int gain) {
		pv += gain;
	}

	public void heal(){
		this.pv = this.pvMax;
	}
	
	public int getPv() {
		return pv;
	}

	public void setPv(int pv) {
		this.pv = pv;
	}

	public int getPvMax() {
		return pvMax;
	}

	public void setPvMax(int pvMax) {
		this.pvMax = pvMax;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}
	
	

}
