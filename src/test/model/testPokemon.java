package model;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Pokemon;

public class testPokemon {

	@Test
	public void testLostHP() {
		Pokemon p = new Pokemon(200);
		p.lostPV(20);
		assertEquals(180, p.getHp());
	}
	
	@Test
	public void testAddHP() {
		Pokemon p = new Pokemon(200);
		p.addPV(20);
		assertEquals(220, p.getHp());
	}
	
	@Test
	public void testHeal(){
		Pokemon p = new Pokemon(200);
		p.lostPV(20);
		assertEquals(180,p.getHp());
		p.heal();
		assertEquals(p.getHpMax(), p.getHp());
	}

}
