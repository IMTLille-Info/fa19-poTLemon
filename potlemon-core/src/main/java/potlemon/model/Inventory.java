package potlemon.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Represent the inventory of the character
 * @author asvevi
 *
 */
public class Inventory {
	
	private Map<Item,Integer> inventory; // Key = Item and value = number of this items in the inventory
	
	
	public Inventory(){
		inventory = new HashMap<Item,Integer>();
	}
	
	
	public void addOne(Item item){
		Integer nbr = inventory.get(item);
		if(nbr==null){
			inventory.put(item, 1);
		}
		else{
			inventory.put(item, nbr+1);
		}
	}
	
	public void removeOne(Item item){
		Integer nbr = inventory.get(item);
		if(nbr==null){
			return;
		}
		else if(nbr==1){
			inventory.remove(item);
		}
		else{
			inventory.put(item, nbr-1);
		}
	}
	
	public int getNumber(Item item){
		Integer nbr = inventory.get(item);
		if(nbr==null){
			return 0;
		}
		else{
			return nbr;
		}
	}
	
	public Set<Item> getKeys(){
		return inventory.keySet();
	}
	
}
