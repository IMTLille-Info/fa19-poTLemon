package potlemon.core;

import potlemon.core.tools.BDDFactory;

import java.sql.Connection;

/**
 * Contains all the core models.
 */
public class GameManager {

    private static GameManager gameManager = null;
    private final Connection bddFactory;

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


    private GameManager() {
        bddFactory = BDDFactory.getConnection();

    }

}
