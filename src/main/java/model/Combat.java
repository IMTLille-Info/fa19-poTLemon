package model;

import java.util.List;

/**
 * Combat's action
 * @author acourand
 *
 */
public class Combat {
	
	/**
	 * List of fighters
	 */
	List<Character> characters;
	
	public Combat(List<Character> characters) {
		this.characters = characters;
	}
}
