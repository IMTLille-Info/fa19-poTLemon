package potlemon.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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

    public List<PokemonSprite> pokemonSpriteList = new ArrayList<>();
    private Texture texturearrow;
    private Sprite spritearrow;
    public int arrowposition[] = {0, 0};
    private BitmapFont namesFont, pvsFont;


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

        namesFont = new BitmapFont();
        namesFont.setColor(Color.BLACK);
        namesFont.getData().setScale(3);

        pvsFont = new BitmapFont();
        pvsFont.setColor(Color.BLACK);
        pvsFont.getData().setScale(2);

        shapeRenderer = new ShapeRenderer();

        textureinterface = new Texture(Gdx.files.internal("sprites/pokemon/interface.png"));
        spriteinterface = new Sprite(textureinterface);
        spriteinterface.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteinterface.setPosition(0, 0);

        texturearrow = new Texture(Gdx.files.internal("sprites/pokemon/arrow.png"));
        spritearrow = new Sprite(texturearrow);
        spritearrow.setPosition(600, 40);


        /**
         * EXAMPLES LOADING POKEMON,
         * maybe make the same thing with all your team
         */
        PokemonSprite myPokemon = new PokemonSprite(new Pokemon(22, "Rapasdepic", 100, 100), true);
        myPokemon.setScale(3, 3);
        myPokemon.setPosition((float) (Gdx.graphics.getWidth() * 0.2), (float) (Gdx.graphics.getHeight() * 0.45));
        pokemonSpriteList.add(myPokemon);


        PokemonSprite advPokemon = new PokemonSprite(new Pokemon(7, "Carapuce", 100, 100), false);
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
        Gdx.gl20.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl20.glClearColor(255, 255, 255, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);


        batch.begin();

        // interface
        spriteinterface.draw(batch);

        // draw arrow
        drawArrow();

        /**
         * Draw all the pokemon sprites...
         */
        int id = 0;
        for (PokemonSprite pokesprite :
                pokemonSpriteList) {
            pokesprite.draw(batch);

            switch (id) {
                case 0:
                    // write name of my pokemon
                    namesFont.draw(batch, pokesprite.getPokemon().getName().toUpperCase(), 670, 400);

                    pvsFont.draw(batch, String.valueOf(pokesprite.getPokemon().getHp()), 800, 330);
                    pvsFont.draw(batch, String.valueOf(pokesprite.getPokemon().getHpMax()), 890, 330);

                    break;
                default:
                    namesFont.draw(batch, pokesprite.getPokemon().getName().toUpperCase(), 54, 500);
                    break;

            }

            id++;
        }


        batch.end();

        // factorize this into functions... just an example here! :)
        drawLifeBars(pokemonSpriteList.get(0), true);
        drawLifeBars(pokemonSpriteList.get(1), false);

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
            shapeRenderer.rect(770, 325, width, height);
        } else {
            // top
            shapeRenderer.rect(255, 640, width, height);
        }

        shapeRenderer.end();
    }


    public void drawArrow() {
        arrowposition[0] %= 2;
        arrowposition[1] %= 2;

        // X
        if (arrowposition[0] == 0) {
            spritearrow.setPosition(600, spritearrow.getY());
        } else {
            spritearrow.setPosition(980, spritearrow.getY());
        }

        // Y
        if (arrowposition[1] == 0) {
            spritearrow.setPosition(spritearrow.getX(), 30);
        } else {
            spritearrow.setPosition(spritearrow.getX(), 110);
        }


        // draw arrow
        spritearrow.draw(batch);
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

        for (PokemonSprite pok :
                pokemonSpriteList) {
            pok.getTexture().dispose();
        }

        namesFont.dispose();
        shapeRenderer.dispose();
        spriteinterface.getTexture().dispose();
        spriteinterface.getTexture().dispose();

    }


}
