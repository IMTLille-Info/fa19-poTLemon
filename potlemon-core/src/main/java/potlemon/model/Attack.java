package potlemon.model;

/**
 * Attack of one pokemon
 * @author acourand
 *
 */
public class Attack {

	/**
	 * attack's name
	 */
	String name;
	
	/**
	 * attack's dommage
	 */
	int attack;
	
	public Attack(String name, int attack) {
		this.name = name;
		this.attack = attack;
	}
	
	/**
	 * get the attack's dommage
	 * @return attack's dommage
	 */
	public int getAttack() {
		return attack;
	}
	
	/**
	 * get the attack's name
	 * @return the attacks name
	 */
	public String getName() {
		return name;
	}
}
