package model;

import static org.junit.Assert.*;

import org.junit.Test;

import potlemon.core.model.Pokemon;

public class TestPokemon {

    @Test
    public void TestLostHp() {
        Pokemon p = new Pokemon("p1",200);
        p.lostPV(20);
        assertEquals(180, p.getHp());
    }

    @Test
    public void TestAddHp() {
        Pokemon p = new Pokemon("p1",200);
        p.setHpMax(220);
        p.addPV(20);
        assertEquals(220, p.getHp());
    }

    @Test
    public void TestHeal() {
        Pokemon p = new Pokemon("p1",200);
        p.lostPV(20);
        assertEquals(180, p.getHp());
        p.heal();
        assertEquals(p.getHpMax(), p.getHp());
    }
    
    @Test
    public void TestXp(){
    	Pokemon p = new Pokemon("p1",200);
    	assertEquals(1, p.getLevel());
    	
    	p.gainXp(p.getXpBeforeLevel());
    	assertEquals(2, p.getLevel());
    	assertEquals(0, p.getXp());
    	assertEquals((int)Math.pow(2, 3), p.getXpBeforeLevel());
    	
    	p.setLevel(1);
    	int xpToGain2Levels = (int) (Math.pow(1, 3) + Math.pow(2, 3));
    	p.gainXp(xpToGain2Levels);
    	assertEquals(3, p.getLevel());
    	assertEquals(0, p.getXp());
    	assertEquals((int)Math.pow(3, 3), p.getXpBeforeLevel());

    	
    }

}


