package model;

import static org.junit.Assert.*;

import org.junit.Test;

import potlemon.model.Inventory;
import potlemon.model.Item;

public class TestInventory {

	@Test
	public void TestAddOne() {
		Inventory inventory = new Inventory();
		Item i = new Item("item", "heal");
		
		inventory.addOne(i);
		inventory.addOne(i);
		
		assertEquals(2, inventory.getNumber(i));
	}
	
	@Test
	public void TestRemoveOne() {
		Inventory inventory = new Inventory();
		Item i = new Item("item", "heal");
		
		inventory.addOne(i);
		inventory.addOne(i);
		inventory.addOne(i);
		
		assertEquals(3, inventory.getNumber(i));
		
		inventory.removeOne(i);
		
		assertEquals(2, inventory.getNumber(i));
	}
	
	@Test
	public void TestGetNumbers() {
		Inventory inventory = new Inventory();
		Item i = new Item("item", "heal");
		inventory.removeOne(i);
		assertEquals(0, inventory.getNumber(i));
	}
	

}
