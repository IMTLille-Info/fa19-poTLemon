package core;

import static org.junit.Assert.*;

import org.junit.Test;

public class testPokemon {

	@Test
	public void testLostPV() {
		Pokemon p = new Pokemon(200);
		p.lostPV(20);
		assertEquals(180, p.getPv());
	}
	
	@Test
	public void testAddPV() {
		Pokemon p = new Pokemon(200);
		p.addPV(20);
		assertEquals(220, p.getPv());
	}

}
