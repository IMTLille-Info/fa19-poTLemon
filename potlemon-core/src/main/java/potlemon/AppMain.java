package potlemon;

import java.util.ArrayList;
import java.util.Scanner;

import potlemon.model.Attack;
import potlemon.model.Character;
import potlemon.model.Fight;
import potlemon.model.Inventory;
import potlemon.model.Item;
import potlemon.model.Pokemon;
import potlemon.model.Team;
import potlemon.model.Type;

/**
 *
 */
public class AppMain {
	
	public static Integer getChoiceInt() {
		Scanner scanner = new Scanner(System.in);
		Integer name = scanner.nextInt();
		return name;
	}

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
	 * Show inventory's information
	 * @param character
	 */
	public static void showFight(Character c1, Character c2){
		System.out.println("Your fight :");
		Fight fight = new Fight(c1, c2);

		while(!fight.isFinish()) {
			System.out.println("1.Attack");
			System.out.println("2.Show inventory");
			System.out.println("3.Manage Team");
			System.out.println("4.fuir");
			Scanner scanner = new Scanner(System.in);
			int choice = scanner.nextInt();
			switch (choice) {
			case 1: // attack
				showAttack(fight.getC1());
				fight.attack(fight.getC1().getTeam().getFirstPokemon().getAttacks().get(getChoiceInt()-1));
				if(!fight.isFinish()){
					System.out.println(fight.getC2().getTeam().getFirstPokemon().getName() + " a maintenant " +  fight.getC2().getTeam().getFirstPokemon().getHp() );
					fight.swap();
				}
				else{
					System.out.println("Combat terminé. " + fight.getC1().getName() + " a gagné");
				}
				break;
			case 2:
				
				break;
			case 3:

				break;
			case 4:
				
				break;
			default:
				System.out.println("Erreur de saisie");
				break;
			}
		}
	}

	private static void showAttack(Character c1) {
		System.out.println("Selectionnez la position de l'attaque");
		for (Attack attack : c1.getTeam().getFirstPokemon().getAttacks()) {
			System.out.println(attack.getName());
		}
	}

	/**
	 * Show the default menu
	 * @param character
	 */
	public static void showMenu(Character character, Character c2){
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
			showFight(character, c2);
			break;
		default:
			System.out.println("Erreur de saisie");
			showMenu(character, c2);
			break;
		}
	}



	public static void main(String[] args) {
		Character character = createCharacter();
		System.out.println("Hi " + character.getName() + " !");
		
		ArrayList<Attack> attacks = new ArrayList<Attack>();
		attacks.add(new Attack("Foudre", 20));
		attacks.add(new Attack("Prout", 50));
		Pokemon p1 = new Pokemon("Dracofeu", 200, 10, 1, Type.FIRE, attacks);
		Pokemon p2 = new Pokemon("Bulbizarre", 150, 8, 1, Type.PLANT, attacks);
		character.getTeam().add(p1);
		character.getTeam().add(p2);

		Character mereNature = createCharacter();
		Pokemon m2 = new Pokemon("Dracofeu", 200, 10, 1, Type.FIRE, attacks);
		Pokemon m1 = new Pokemon("Bulbizarre", 150, 8, 1, Type.PLANT, attacks);
		mereNature.getTeam().add(m1);
		mereNature.getTeam().add(m2);

		Item i1 = new Item("potion", "heal");
		Item i2 = new Item("pokeball", "ball");
		for(int i = 0; i < 6; i++){
			character.getInventory().addOne(i1); // add 6 potions
		}

		for(int i = 0; i < 3; i++){
			character.getInventory().addOne(i2); // add 3 pokeball
		}

		showMenu(character,mereNature);
	}


}
