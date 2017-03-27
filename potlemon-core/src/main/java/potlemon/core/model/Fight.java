package potlemon.core.model;


/**
 * Combat's action
 * @author acourand
 *
 */
public class Fight {
	
	Character attacker, defender;
	boolean fin = false;
	
	public Fight(Character c1, Character c2) {
		this.attacker = c1;
		this.defender = c2;
	}
	
	/**
	 * 
	 * @param attack
	 */
	public void attack(Attack attack){
		Pokemon attacker = this.attacker.getTeam().getFirstPokemon();
		Pokemon defender = this.defender.getTeam().getFirstPokemon();

		int attackPoint = attack.getAttackDommage() + attacker.getLevel() - defender.getLevel();

		if(attackPoint<0){
			attackPoint = 0;
		}


		defender.lostPV(attackPoint);
		if(this.attacker.getTeam().getFirstPokemon().checkDead()){
			this.defender.getTeam().remove(0);
		}
	}
	
	public Character getAttacker() {
		return attacker;
	}
	
	public Character getDefender() {
		return defender;
	}
	
	/**
	 * Simule la fin du tour
	 */
	public void swap(){
		Character temp = this.defender;
		this.defender = attacker;
		this.attacker = temp;
	}
}
