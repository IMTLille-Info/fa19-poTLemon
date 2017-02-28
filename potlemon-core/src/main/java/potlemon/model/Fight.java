package potlemon.model;


/**
 * Combat's action
 * @author acourand
 *
 */
public class Fight {
	
	Character attacker, defender;
	
	public Fight(Character c1, Character c2) {
		this.attacker = c1;
		this.defender = c2;
	}
	
	/**
	 * 
	 * @param attack
	 */
	public void attack(Attack attack){
		int attackPoint = attack.getAttackDommage() + this.attacker.getTeam().getFirstPokemon().getLevel() - this.defender.getTeam().getFirstPokemon().getLevel();
		if(attackPoint<0){
			attackPoint = 0;
		}
		if(this.getC2().getTeam().getFirstPokemon().getHp()-attackPoint <0){
			this.getC2().getTeam().getFirstPokemon().setHp(0);	
		} else {
			this.getC2().getTeam().getFirstPokemon().setHp( this.defender.getTeam().getFirstPokemon().getHp()-attackPoint);
		}
	}
	
	public Character getC1() {
		return attacker;
	}
	
	public Character getC2() {
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
	
	public boolean checkDead(){
		if(defender.getTeam().getFirstPokemon().getHp()==0){
			return true;
		}
		return false;
	}
	
	public void swapDead(Pokemon p){
		
	}
}
