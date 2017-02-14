package model;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Pokemon;
import model.Team;

public class testTeam {

	@Test
	public void TestAdd() {
		Team t = new Team();
		Pokemon p = new Pokemon();
		assertEquals(0, t.size());
		t.add(p);
		assertEquals(1, t.size());
		t.add(p);
		assertEquals(2, t.size());
	}
	
	@Test
	public void TestSwap(){
		Pokemon p1 = new Pokemon(200);
		p1.setName("pokemon1");
		
		Pokemon p2 = new Pokemon(180);
		p2.setName("pokemon2");
		
		Team t = new Team();
		t.add(p1);
		t.add(p2);
		
		assertEquals(t.getFirstPokemon().getName(), "pokemon1");
		t.swapPokemon(0, 1);
		assertEquals(t.getFirstPokemon().getName(), "pokemon2");
	}

}
