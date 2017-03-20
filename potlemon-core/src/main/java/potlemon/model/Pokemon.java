package potlemon.model;

import java.util.ArrayList;
import java.util.List;

public class Pokemon {
	
	private String name;
	private int hp;//hp at this moment
	private int hpMax;//maximum hp when the pokemon is full life
	private int level;
	private int defense;
	private Type type; //enum
	private List<Attack> attacks;
	private int xpBeforeLevel;
	private int xp;

	
	public Pokemon(String name, int pv) {
		this.name = name;
		this.hpMax = pv;
		this.hp = pv;
		this.level = 1;
		this.xpBeforeLevel = calculateXpBeforeLevel();
		this.xp = 0;
		this.defense = 0;
		this.attacks = new ArrayList<Attack>();
	}
	

	public Pokemon(String name, int hpMax, int defense,int level, Type type, List<Attack> attacks) {
		this.name = name;
		this.hpMax = hpMax;
		this.defense = defense;
		this.level = level;
		this.xpBeforeLevel = calculateXpBeforeLevel();
		this.xp = 0;
		this.type = type;
		this.hp = hpMax;
		this.attacks = attacks;
	}

	
	public int calculateXpBeforeLevel(){
		return (int) Math.pow(level, 3);
	}
	
	
	
	/**
	 * Remove the amount of pv in parameter
	 * @param lost
	 */
	public void lostPV(int lost) {
		hp -= lost;
		if(hp < 0){
			hp = 0;
		}
	}
	
	/**
	 * Remove the amount of pv in parameter
	 * @param lost
	 */
	public void addPV(int gain) {
		hp += gain;
		if(hp > hpMax){
			hp = hpMax;
		}
	}

	public void heal(){
		this.hp = this.hpMax;
	}
	
	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getHpMax() {
		return hpMax;
	}

	public void setHpMax(int hpMax) {
		this.hpMax = hpMax;
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
		xpBeforeLevel = calculateXpBeforeLevel();
		xp = 0;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	public List<Attack> getAttacks() {
		return attacks;
	}
	
	public void setAttacks(Attack attack) {
		this.attacks.add(attack);
	}
	
	
	/**
	 * Add amount to XP and check for level up
	 * @param amount
	 */
	public void gainXp(int amount){
		if(amount >= xpBeforeLevel-xp){
			amount -= xpBeforeLevel-xp;
			level += 1;
			xpBeforeLevel = calculateXpBeforeLevel();
			xp = 0;
			gainXp(amount);
		}
		else{
			xp += amount;
		}
	}


	public int getXpBeforeLevel() {
		return xpBeforeLevel;
	}


	public int getXp() {
		return xp;
	}
	
	
}
