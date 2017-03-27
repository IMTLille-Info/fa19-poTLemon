package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import potlemon.core.model.Attack;
import potlemon.core.model.Fight;
import potlemon.core.model.Pokemon;
import potlemon.core.model.Team;
import potlemon.core.model.Type;

public class TestFight {

	@Test
	public void testFightAttack() {
		Attack a1 = new Attack("foudre", 20);
		List<Attack> attacks = new ArrayList<Attack>();
		attacks.add(a1);
		Pokemon p1 = new Pokemon("pikachu", 100, 0, 1, Type.AIR, attacks);
		Team team1 = new Team();
		team1.add(p1);
		potlemon.core.model.Character c1 = new potlemon.core.model.Character("test1",team1);
		
		Attack a2 = new Attack("foudre", 20);
		List<Attack> attacks2 = new ArrayList<Attack>();
		attacks.add(a2);
		Pokemon p2 = new Pokemon("pikachu", 100, 0, 1, Type.AIR, attacks2);
		Team team2 = new Team();
		team2.add(p2);
		potlemon.core.model.Character c2 = new potlemon.core.model.Character("test2", team2);
		
		Fight fight = new Fight(c1, c2);
		fight.attack(a1);
		assertEquals(80,fight.getDefender().getTeam().getFirstPokemon().getHp());
	}
	
	@Test
	public void testFightSwap(){
		Attack a1 = new Attack("foudre", 20);
		List<Attack> attacks = new ArrayList<Attack>();
		attacks.add(a1);
		Pokemon p1 = new Pokemon("pikachu", 100, 0, 1, Type.AIR, attacks);
		Team team1 = new Team();
		team1.add(p1);
		potlemon.core.model.Character c1 = new potlemon.core.model.Character("test1",team1);
		
		Attack a2 = new Attack("foudre", 20);
		List<Attack> attacks2 = new ArrayList<Attack>();
		attacks.add(a2);
		Pokemon p2 = new Pokemon("pikachu", 100, 0, 1, Type.AIR, attacks2);
		Team team2 = new Team();
		team2.add(p2);
		potlemon.core.model.Character c2 = new potlemon.core.model.Character("test2", team2);
		
		Fight fight = new Fight(c1, c2);
		fight.swap();
		assertEquals(fight.getAttacker(), c2);
	}
	
	@Test
	public void fightCheckDeadKO(){
		Attack a1 = new Attack("foudre", 20);
		List<Attack> attacks = new ArrayList<Attack>();
		attacks.add(a1);
		Pokemon p1 = new Pokemon("pikachu", 100, 0, 1, Type.AIR, attacks);
		Team team1 = new Team();
		team1.add(p1);
		potlemon.core.model.Character c1 = new potlemon.core.model.Character("test1",team1);
		
		Attack a2 = new Attack("foudre", 20);
		List<Attack> attacks2 = new ArrayList<Attack>();
		attacks.add(a2);
		Pokemon p2 = new Pokemon("pikachu",0, 0, 1, Type.AIR, attacks2);
		Team team2 = new Team();
		team2.add(p2);
		potlemon.core.model.Character c2 = new potlemon.core.model.Character("test2", team2);
		
		Fight fight = new Fight(c1, c2);
		
		assertEquals(true, fight.getDefender().getTeam().getFirstPokemon().checkDead());
	}
	
	@Test
	public void fightCheckDeadOK(){
		Attack a1 = new Attack("foudre", 20);
		List<Attack> attacks = new ArrayList<Attack>();
		attacks.add(a1);
		Pokemon p1 = new Pokemon("pikachu", 100, 0, 1, Type.AIR, attacks);
		Team team1 = new Team();
		team1.add(p1);
		potlemon.core.model.Character c1 = new potlemon.core.model.Character("test1",team1);
		
		Attack a2 = new Attack("foudre", 20);
		List<Attack> attacks2 = new ArrayList<Attack>();
		attacks.add(a2);
		Pokemon p2 = new Pokemon("pikachu",100, 0, 1, Type.AIR, attacks2);
		Team team2 = new Team();
		team2.add(p2);
		potlemon.core.model.Character c2 = new potlemon.core.model.Character("test2", team2);
		
		Fight fight = new Fight(c1, c2);
		
		assertEquals(false, fight.getDefender().getTeam().getFirstPokemon().checkDead());
	}

}
