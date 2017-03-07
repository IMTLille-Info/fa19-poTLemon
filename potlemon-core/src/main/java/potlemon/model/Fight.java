package potlemon.model;

import java.util.ArrayList;


/**
 * Combat's action
 * @author acourand
 *
 */
public class Fight {
	
	Character attacker, defender;
	boolean fin = false;
	ArrayList<Pokemon> deadAttacker,deadDefender;
	
	public Fight(Character c1, Character c2) {
		this.attacker = c1;
		this.defender = c2;
		this.deadAttacker = new ArrayList<Pokemon>();
		this.deadDefender = new ArrayList<Pokemon>();
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
		if(checkDead()){
			this.deadDefender.add(this.getC2().getTeam().getFirstPokemon());
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
		
		ArrayList<Pokemon> tempMort = this.deadDefender;
		this.deadDefender = this.deadAttacker;
		this.deadAttacker = tempMort;
	}
	
	/**
	 * Verifie si le pokemon en combat est mort
	 * @return
	 */
	public boolean checkDead(){
		if(defender.getTeam().getFirstPokemon().getHp()<=0){
			return true;
		}
		return false;
	}
}
