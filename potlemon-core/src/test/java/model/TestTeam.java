package model;

import static org.junit.Assert.*;

import org.junit.Test;

import potlemon.core.model.Pokemon;
import potlemon.core.model.Team;

public class TestTeam {

	@Test
	public void TestAdd() {
		Team t = new Team();
		Pokemon p = new Pokemon("p1",200);
		assertEquals(0, t.size());
		t.add(p);
		assertEquals(1, t.size());
		t.add(p);
		assertEquals(2, t.size());
	}
	
	@Test
	public void TestCheckEveryoneDead(){
		Team t = new Team();
		Pokemon p = new Pokemon("p1",200);
		Pokemon p1 = new Pokemon("p2",200);
		t.add(p1);
		t.add(p);
		assertEquals(false, t.everyoneIsDead());
		p.lostPV(200);
		p1.lostPV(200);
		assertEquals(true, t.everyoneIsDead());
	}
	
	@Test
	public void TestFirstInLife(){
		Team t = new Team();
		Pokemon p = new Pokemon("p1",200);
		Pokemon p1 = new Pokemon("p2",200);
		Pokemon p2 = new Pokemon("p3",200);
		t.add(p);
		t.add(p1);
		t.add(p2);
		assertEquals(p, t.getFirstPokemonInLife());
		t.getFirstPokemon().lostPV(200);
		assertEquals(p1, t.getFirstPokemonInLife());
	}
	
	@Test
	public void TestSwap(){
		Pokemon p1 = new Pokemon("pokemon1",200);
		
		Pokemon p2 = new Pokemon("pokemon2",180);
		
		Team t = new Team();
		t.add(p1);
		t.add(p2);
		
		assertEquals(t.getFirstPokemon().getName(), "pokemon1");
		t.swapPokemon(0, 1);
		assertEquals(t.getFirstPokemon().getName(), "pokemon2");
	}

}
