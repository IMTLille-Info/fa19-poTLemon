package potlemon.core;

import potlemon.core.model.*;
import potlemon.core.model.Character;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains all the core models.
 */
public class GameManager {

    private static GameManager gameManager = null;
    private Character playerCharacter;

    /**
     * Returns a single instance
     *
     * @return
     */
    public final static GameManager getInstance() {
        if (gameManager == null) {
            gameManager = new GameManager();
        }

        return gameManager;
    }


    /**
     * GameManager
     */
    private GameManager() {

        init();


    }

    /**
     * Called first time.
     */
    private void init() {
        // have to create the main character.

        // OSEF
        Team myTeam = new Team();

        myTeam.add(new Pokemon(7, "Carapuce", 100, 100, 3, Type.WATER, initAttacks()));
        myTeam.add(new Pokemon(22, "Rapasdepic", 100, 100, 3, Type.AIR, initAttacks()));
        myTeam.add(new Pokemon(6, "Dracaufeu", 100, 100, 3, Type.FIRE, initAttacks()));

        playerCharacter = new potlemon.core.model.Character("Me", myTeam);
    }

    /**
     * Attacks list
     *
     * @return
     */
    private List<Attack> initAttacks() {
        List<Attack> attacks = new ArrayList<>();
        attacks.add(new Attack("Baffe", 30));
        attacks.add(new Attack("Queue d'fer", 0));
        attacks.add(new Attack("Flammeche", 70));
        attacks.add(new Attack("Charge", 60));

        return attacks;
    }


    public Character getPlayerCharacter() {
        return playerCharacter;
    }
}
