package potlemon.screen.listeners;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import potlemon.screen.PokeFight;

/**
 * Created by Pierre on 26/03/2017.
 */
public class FightListener implements InputProcessor {
    private PokeFight pokeFight;

    public FightListener(PokeFight pokeFight) {
        this.pokeFight = pokeFight;
    }

    @Override
    public boolean keyDown(int i) {
        System.out.println(i);
        switch (i) {
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

            case 81:
                pokeFight.pokemonSpriteList.get(0).getPokemon().addPV(10);
                pokeFight.pokemonSpriteList.get(1).getPokemon().addPV(-10);
                break;
        }


        return false;
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
}
