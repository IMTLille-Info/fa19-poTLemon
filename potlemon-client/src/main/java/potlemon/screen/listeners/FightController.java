package potlemon.screen.listeners;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import potlemon.core.GameManager;
import potlemon.core.model.*;
import potlemon.core.model.Character;
import potlemon.core.models.PokemonSprite;
import potlemon.screen.PokeFight;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pierre on 26/03/2017.
 */
public class FightController implements InputProcessor {
    private final Fight fight;

    private final Character playerCharacter;
    private final Character advCharacter;

    private PokeFight pokeFight;

    private boolean systemBusy = false;


    public FightController(PokeFight pokeFight) {
        this.pokeFight = pokeFight;


        Team teamIA = new Team();
        List<Attack> attacks = new ArrayList<>();
        attacks.add(new Attack("Bulle d'O", 3));
        attacks.add(new Attack("Baffe", 7));
        teamIA.add(new Pokemon(6, "Dracaufeu", 230, 230, 12, Type.FIRE, attacks));
        advCharacter = new Character("IA", teamIA);

        playerCharacter = GameManager.getInstance().getPlayerCharacter();

        this.fight = new Fight(playerCharacter, advCharacter);


    }

    @Override
    public boolean keyDown(int pressedKey) {

        // block the system if busy
        if(systemBusy){
            return false;
        }

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
            System.out.println("Should be fight...");
            actionFight();
        } else {
            System.out.println("Humm?");
        }
    }

    private void moveCursor(int key) {
        switch (key) {
            // up
            case 19:
                pokeFight.arrowposition[1]++;
                break;
            // down
            case 20:
                pokeFight.arrowposition[1]--;
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

        pokeFight.arrowposition[0] = Math.max(0, Math.min(pokeFight.arrowposition[0], 1));
        pokeFight.arrowposition[1] = Math.max(0, Math.min(pokeFight.arrowposition[1], 1));

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

        systemBusy=true;

        Thread t = new Thread(() -> {
            // one attack, or more with IA
            do {

                Character attacker = fight.getAttacker();
                Pokemon attackingPokemon = attacker.getTeam().getFirstPokemonInLife();
                PokemonSprite pokeSprite = pokeFight.allPokemons.get(attackingPokemon);

                System.out.println(attackingPokemon.getName() + " is attacking!");
                System.out.println(attackingPokemon);
                fight.attack(attackingPokemon.getAttacks().get(0));
                System.out.println("attack done");
                fight.swap();

                // play attacker sound
                pokeFight.playSound(pokeSprite.getCry());


                // wait for the move
                try {

                    for(int tour=0;tour<3;tour++){

                        for(int i=0;i<20;i++){
                            pokeSprite.setPosition(pokeSprite.getX()+1,pokeSprite.getY()+1 );
                                Thread.sleep(10);
                        }

                        for(int i=0;i<20;i++){
                            pokeSprite.setPosition(pokeSprite.getX()-1,pokeSprite.getY()-1 );
                            Thread.sleep(10);

                        }

                    }




                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                // WAIT FOR NEXT ATTACK
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } while (!(fight.getAttacker() == playerCharacter));


            systemBusy=false;
        });
        t.start();


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
