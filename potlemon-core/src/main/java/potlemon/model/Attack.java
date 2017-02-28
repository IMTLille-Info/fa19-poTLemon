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
	int attackDommage;
	
	public Attack(String name, int attackDommage) {
		this.name = name;
		this.attackDommage = attackDommage;
	}
	
	/**
	 * get the attack's dommage
	 * @return attack's dommage
	 */
	public int getAttackDommage() {
		return attackDommage;
	}

	/**
	 * Set the attack's dommage
	 * @param attackDommage
	 */
	public void setAttackDommage(int attackDommage) {
		this.attackDommage = attackDommage;
	}
	
	/**
	 * get the attack's name
	 * @return the attacks name
	 */
	public String getName() {
		return name;
	}
}
