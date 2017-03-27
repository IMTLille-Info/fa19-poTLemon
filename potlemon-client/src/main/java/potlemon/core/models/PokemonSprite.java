package potlemon.core.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import potlemon.core.model.Pokemon;

/**
 * Created by Pierre on 26/03/2017.
 */
public class PokemonSprite extends Sprite {

    private Pokemon pokemon;
    private Sound cry;

    public PokemonSprite(Pokemon pokemon, Sound cry, boolean back) {
        super(new Texture(Gdx.files.internal("sprites/pokemon/" + pokemon.getId() + (back == true ? "-back" : "") + ".png")));
        this.pokemon = pokemon;
        this.cry = cry;
        System.out.println("Loading sprite for " + "sprites/pokemon/" + pokemon.getId() + (back == true ? "-back" : "") + ".png");
    }

    /**
     * Gets the pokemon.
     *
     * @return
     */
    public Pokemon getPokemon() {
        return pokemon;
    }


    @Override
    public String toString() {
        return "Asset:" + getX()+","+getY()+": " + pokemon.toString();
    }

    public Sound getCry() {
        return cry;
    }
}
