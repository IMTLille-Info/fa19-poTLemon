package potlemon.screen.listeners;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import potlemon.core.GameManager;
import potlemon.core.model.Character;
import potlemon.core.model.Fight;
import potlemon.core.model.Pokemon;
import potlemon.core.model.Team;
import potlemon.screen.PokeFight;

import javax.swing.*;

/**
 * Created by Pierre on 26/03/2017.
 */
public class FightController implements InputProcessor {
    private final Fight fight;

    private final Character playerCharacter;
    private final Character advCharacter;

    private PokeFight pokeFight;


    public FightController(PokeFight pokeFight) {
        this.pokeFight = pokeFight;


        Team teamIA = new Team();
        teamIA.add(new Pokemon(6, "Dracaufeu", 230, 230));
        advCharacter = new Character("IA", teamIA);

        playerCharacter = GameManager.getInstance().getPlayerCharacter();

        this.fight = new Fight(playerCharacter, advCharacter);







    }

    @Override
    public boolean keyDown(int pressedKey) {
        System.out.println(pressedKey);

        // FOR CURSOR
        if (pressedKey >= 19 && pressedKey <= 22) {
            moveCursor(pressedKey);
            return true;
        }


        switch (pressedKey) {

            case 81:
                pokeFight.pokemonSpriteList.get(0).getPokemon().addPV(10);
                pokeFight.pokemonSpriteList.get(1).getPokemon().lostPV(10);
                break;

            case Input.Keys.ENTER:
            case Input.Keys.DPAD_CENTER:
                // validate
                validateChoice();
                break;
        }


        return false;
    }

    /**
     *
     */
    private void validateChoice() {
        System.out.println("Current: " + pokeFight.arrowposition[0] + "-" + pokeFight.arrowposition[1]);
        if (pokeFight.arrowposition[0] == 0 && pokeFight.arrowposition[1] == 1) {
            actionFight();
        }
    }

    private void moveCursor(int key) {
        switch (key) {
            // up
            case 19:
                pokeFight.arrowposition[1]--;
                break;
            // down
            case 20:
                pokeFight.arrowposition[1]++;
                break;
            // left
            case 21:
                pokeFight.arrowposition[0]--;
                break;
            // right
            case 22:
                pokeFight.arrowposition[0]++;
                break;
        }

        pokeFight.playSound("click");
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // we can also handle mouse movement without anything pressed
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // ignore if its not left mouse button or first touch pointer
        if (button != Input.Buttons.RIGHT || pointer > 0) return false;
        System.out.println(screenX + "," + screenY + " ! " + pointer + " " + button);
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (button != Input.Buttons.LEFT || pointer > 0) return false;
        return true;
    }


    @Override
    public boolean scrolled(int i) {
        return false;
    }


    /**
     * Action fight
     */
    private void actionFight() {
        Character attacker = fight.getAttacker();

        Pokemon attackingPokemon = attacker.getTeam().getFirstPokemonInLife();
        fight.attack(attackingPokemon.getAttacks().get(0));
        System.out.println("attack done");
        fight.swap();
    }

    public Fight getFight() {
        return fight;
    }


    public Character getPlayerCharacter() {
        return playerCharacter;
    }

    public Character getAdvCharacter() {
        return advCharacter;
    }
}
