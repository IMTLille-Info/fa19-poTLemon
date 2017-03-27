package potlemon.core.model;

import java.util.ArrayList;


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
	
	public Fight() {
	
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
		this.getC2().getTeam().getFirstPokemon().setHp( this.defender.getTeam().getFirstPokemon().getHp()-attackPoint);
		if(this.attacker.getTeam().getFirstPokemon().checkDead()){
			this.defender.getTeam().remove(0);
		}
	}
	
	/**
	 * 
	 * @return true if all team dead
	 */
	public boolean isFinish(){
		return defender.getTeam().size()==0;
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
}
