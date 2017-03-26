package potlemon.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import potlemon.core.model.Pokemon;
import potlemon.core.models.PokemonSprite;
import potlemon.screen.listeners.FightListener;

import java.util.ArrayList;
import java.util.List;

public class PokeFight extends AbstractScreen {


    private SpriteBatch batch;

    private ShapeRenderer shapeRenderer;
    private Texture textureinterface;
    private Sprite spriteinterface;

    private List<PokemonSprite> pokemonSpriteList = new ArrayList<>();


    public PokeFight() {
        super();
    }

    /**
     * Called on creation! :)
     */
    public void show() {

        // get init delta time for animation


        // add music
        music = Gdx.audio.newMusic(Gdx.files.internal("musics/battle_wild.mp3"));
        music.setLooping(true);
        music.play();

        /**
         * Load all resources here...
         */
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        textureinterface = new Texture(Gdx.files.internal("sprites/pokemon/interface.png"));
        spriteinterface = new Sprite(textureinterface);
        spriteinterface.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteinterface.setPosition(0, 0);

        /**
         * EXAMPLES LOADING POKEMON,
         * maybe make the same thing with all your team
         */
        PokemonSprite myPokemon = new PokemonSprite(new Pokemon(22, "Rapasdepic", 100, 100), true);
        myPokemon.setScale(3, 3);
        myPokemon.setPosition((float) (Gdx.graphics.getWidth() * 0.2), (float) (Gdx.graphics.getHeight() * 0.45));
        pokemonSpriteList.add(myPokemon);



        PokemonSprite advPokemon = new PokemonSprite(new Pokemon(7, "Carapuce", 30, 100), false);
        advPokemon.setScale(3, 3);
        advPokemon.setPosition((float) (Gdx.graphics.getWidth() * 0.7), (float) (Gdx.graphics.getHeight() * 0.7));
        pokemonSpriteList.add(advPokemon);


        // listener...
        Gdx.input.setInputProcessor(new FightListener(this));
    }

    /**
     * Each step... = repaint in swing
     *
     * @param delta
     */
    public void render(float delta) {
        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.begin();

        // interface
        spriteinterface.draw(batch);

        /**
         * Draw all the pokemon sprites...
         */
        for (PokemonSprite pokesprite :
                pokemonSpriteList) {
            pokesprite.draw(batch);
        }


        batch.end();

        // factorize this into functions... just an example here! :)
        drawLifeBars(pokemonSpriteList.get(0),true);
        drawLifeBars(pokemonSpriteList.get(1),false);

    }

    /**
     * On window resize
     *
     * @param width
     * @param height
     */
    public void resize(int width, int height) {

    }


    /**
     * @param pokemonSprite
     * @param position      true: BOTTOM; false: TOP
     */
    private void drawLifeBars(PokemonSprite pokemonSprite, boolean position) {

        float p = pokemonSprite.getPokemon().getLifePercentage();
        // get life percent
        if (p < 0.4) {
            shapeRenderer.setColor(Color.RED);
        } else {
            shapeRenderer.setColor(Color.GREEN);
        }

        // gdx draw green rect
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        float height = 20.0f;
        float width = p * 380.0f;

        //System.out.println("Pokemon: "+ pokemonSprite.getPokemon().getName() +" -> " + p +" ("+pokemonSprite.getPokemon().getHp()+"/"+pokemonSprite.getPokemon().getHpMax()+")");

        // bottom
        if (position == true) {
            shapeRenderer.rect(770, 345, width, height);
        } else {
            // top
            shapeRenderer.rect(255, 630, width, height);
        }

        shapeRenderer.end();
    }


    /**
     * When the window is paused
     */
    public void pause() {

    }

    /**
     * When the window is resumed
     */
    public void resume() {
    }

    public void hide() {
        dispose();
    }

    /**
     * When the window is destroyed... Have to clear every resources to avoid memory leaks :)
     */
    public void dispose() {
        music.stop();
        music.dispose();
    }


}
