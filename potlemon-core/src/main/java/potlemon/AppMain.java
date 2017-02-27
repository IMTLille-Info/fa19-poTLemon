package potlemon;

import java.util.Scanner;

import potlemon.model.Character;
import potlemon.model.Inventory;
import potlemon.model.Item;
import potlemon.model.Pokemon;
import potlemon.model.Team;
import potlemon.model.Type;

/**
 *
 */
public class AppMain {

	/**
	 * Menu to create the character
	 * @return
	 */
    public static Character createCharacter(){
    	System.out.println("Create your character :");
    	System.out.println("Name : ");
    	Scanner scanner = new Scanner(System.in);
    	String name = scanner.nextLine();
    	
    	return new Character(name,new Team(), new Inventory());
    }
    
    /**
     * Show team's Informations
     * @param character
     */
    public static void showTeam(Character character){
    	Team team = character.getTeam();
    	System.out.println("Your team :");
    	for(int i = 0; i < team.size();i++){
    		System.out.printf("%d. %s - lvl %d\n",i,team.get(i).getName(), team.get(i).getLevel() );
    	}
    	
    }
    
    /**
     * Show inventory's information
     * @param character
     */
    public static void showInventory(Character character){
    	Inventory inventory = character.getInventory();
    	System.out.println("Your Inventory :");
    	Object[] keys = inventory.getKeys().toArray();
    	for(int i = 0; i < keys.length;i++){
    		Item item = (Item) keys[i];
    		System.out.printf("%d. %s : %d\n",i,item.getName(), inventory.getNumber(item) );
    	}
    	
    	
    }
    
    /**
     * Show the default menu
     * @param character
     */
    public static void showMenu(Character character){
    	System.out.println("What do you want to do ?");
    	System.out.println("1.Manage Team");
    	System.out.println("2.Show inventory");
    	System.out.println("3.Fight");
    	
    	Scanner scanner = new Scanner(System.in);
    	int choice = scanner.nextInt();
    	
    	switch (choice) {
		case 1:
			showTeam(character);
			break;
		case 2:
			showInventory(character);
			break;
		case 3:
			
			break;
		default:
			System.out.println("Erreur de saisie");
			showMenu(character);
			break;
		}
    }
    


    public static void main(String[] args) {
        Character character = createCharacter();
        System.out.println("Hi " + character.getName() + " !");
        Pokemon p1 = new Pokemon("Dracofeu", 200, 10, 1, Type.FIRE, null);
        Pokemon p2 = new Pokemon("Bulbizarre", 150, 8, 1, Type.PLANT, null);
        character.getTeam().add(p1);
        character.getTeam().add(p2);
        
        Item i1 = new Item("potion", "heal");
        Item i2 = new Item("pokeball", "ball");
        for(int i = 0; i < 6; i++){
        	character.getInventory().addOne(i1); // add 6 potions
        }
        
        for(int i = 0; i < 3; i++){
        	character.getInventory().addOne(i2); // add 3 pokeball
        }
        
        showMenu(character);
    }
    

}
