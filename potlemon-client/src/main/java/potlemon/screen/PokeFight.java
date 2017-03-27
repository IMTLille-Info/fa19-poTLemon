package potlemon.screen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import potlemon.core.GameManager;
import potlemon.core.model.Pokemon;
import potlemon.core.model.Team;
import potlemon.core.models.PokemonSprite;
import potlemon.render.TiledMapGame;
import potlemon.screen.listeners.FightController;

import java.util.*;

public class PokeFight extends AbstractScreen {


    public static final int MODE_FIGHT = 1;
    public static final int MODE_POKEMON_SELECTION = 2;
    public static final int MODE_END_MODE = 3;

    public int windowMode = PokeFight.MODE_FIGHT;

    private SpriteBatch batch;

    private ShapeRenderer shapeRenderer;
    private Texture textureinterface;
    private Sprite spriteinterface;

    public List<PokemonSprite> pokemonSpriteList = new ArrayList<>();

    public Map<Pokemon, PokemonSprite> allPokemons = new HashMap<>();

    private Texture texturearrow;
    private Sprite spritearrow;
    public int arrowposition[] = {0, 1};
    private BitmapFont namesFont, pvsFont;
    private FightController fightController;
    private GameManager gameManager;
    public static TiledMapGame renderer;
    public Music lostMusic;
    public Music winMusic;


    public PokeFight() {
        super();
        gameManager = GameManager.getInstance();
    }

    public PokeFight(TiledMapGame renderer) {
        this();

        this.renderer = renderer;
    }

    /**
     * Called on creation! :)
     */
    public void show() {

        // get init delta time for animation

        // listener...
        fightController = new FightController(this);
        Gdx.input.setInputProcessor(fightController);


        // add music
        music = Gdx.audio.newMusic(Gdx.files.internal("musics/battle_wild.mp3"));
        music.setLooping(true);
        music.play();

        lostMusic = Gdx.audio.newMusic(Gdx.files.internal("musics/lost.mp3"));
        winMusic = Gdx.audio.newMusic(Gdx.files.internal("musics/victory.mp3"));

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


        // create all pokemons
        createAllPokemons();

    }

    /**
     * Create all sprites for pokemons.
     */
    private void createAllPokemons() {

        // my team
        Team myTeam = fightController.getPlayerCharacter().getTeam();
        addPokemonSprites(myTeam.getTeam(), false);

        Team advTeam = fightController.getAdvCharacter().getTeam();
        addPokemonSprites(advTeam.getTeam(), true);

    }

    /**
     * Adds a list of pokemon as sprite.
     *
     * @param pokemons
     * @param adver
     */
    private void addPokemonSprites(List<Pokemon> pokemons, boolean adver) {
        for (Pokemon pok :
                pokemons) {
            PokemonSprite pokemonSprite = new PokemonSprite(pok, loadCrySound(pok.getId()), !adver);
            pokemonSprite.setScale(3, 3);

            // set window position
            if (!adver)
                pokemonSprite.setPosition((float) (Gdx.graphics.getWidth() * 0.2), (float) (Gdx.graphics.getHeight() * 0.45));
            else
                pokemonSprite.setPosition((float) (Gdx.graphics.getWidth() * 0.7), (float) (Gdx.graphics.getHeight() * 0.7));


            pokemonSpriteList.add(pokemonSprite);
            allPokemons.put(pok, pokemonSprite);
        }
    }

    /**
     * Each step... = repaint in swing
     *
     * @param delta
     */
    public void render(float delta) {
        Gdx.gl20.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        switch (windowMode) {
            case MODE_FIGHT:
                renderForFight(delta);
                break;

            case MODE_POKEMON_SELECTION:
                renderForSelection(delta);
                break;

            case MODE_END_MODE:
                renderForEnd(delta);
                break;
        }

    }

    private void renderForEnd(float delta) {
        // win or lost

        String sentence = "";
        if (fightController.haveWin) {
            sentence += "Congrats, you won this fight! Click [enter]";
        } else {
            sentence += "Game over... Click [enter]";
        }

        int x = 300,
                y = Gdx.graphics.getHeight() - 200;

        batch.begin();
        namesFont.setColor(Color.YELLOW);
        namesFont.draw(batch, sentence, x, y);

        batch.end();

    }

    private void renderForSelection(float delta) {

        batch.begin();

        int i = 0;
        List<Pokemon> myPokes = gameManager.getPlayerCharacter().getTeam().getTeam();
        for (Pokemon pokemon :
                myPokes) {
            PokemonSprite pokemonSprite = allPokemons.get(pokemon);

            int x = 300,
                    y = Gdx.graphics.getHeight() - 100 - 100 * i;

            int posArrow = Math.abs(arrowposition[1] % myPokes.size());
            if (posArrow == i) {
                spritearrow.setPosition(x - 100, y - spritearrow.getHeight() / 2 - 15);

                spritearrow.draw(batch);
                pokemonSprite.setArrowSelected(true);
            } else {
                pokemonSprite.setArrowSelected(false);
            }

            if (pokemonSprite.isUserSelected()) {
                namesFont.setColor(Color.YELLOW);
            } else {
                namesFont.setColor(Color.BLACK);
            }
            namesFont.draw(batch, pokemon.getName().toUpperCase() + (pokemon.checkDead() ? " - [DEAD]" : ""), x, y);
            i++;
        }

        batch.end();

    }


    /**
     * If it's fight...
     *
     * @param delta
     */
    private void renderForFight(float delta) {
        batch.begin();

        // interface
        spriteinterface.draw(batch);

        // draw arrow
        drawArrow();

        /**
         * Draw all the pokemon sprites...
         */
        PokemonSprite myPokeSprite = null, hisPokeSprite = null;

        // get my pokemon
        Pokemon myPokemon = fightController.getPlayerCharacter().getTeam().getFirstPokemonInLife();

        if (myPokemon != null) {
            myPokeSprite = allPokemons.get(myPokemon);
            namesFont.draw(batch, myPokeSprite.getPokemon().getName().toUpperCase(), 790, 430);
            pvsFont.draw(batch, String.valueOf(myPokeSprite.getPokemon().getHp()), 800, 340);
            pvsFont.draw(batch, String.valueOf(myPokeSprite.getPokemon().getHpMax()), 970, 340);
            myPokeSprite.draw(batch);
        }


        // his pokemon
        Pokemon hisPokemon = fightController.getAdvCharacter().getTeam().getFirstPokemonInLife();
        if (hisPokemon != null) {
            hisPokeSprite = allPokemons.get(hisPokemon);
            namesFont.draw(batch, hisPokeSprite.getPokemon().getName().toUpperCase(), 130, 730);
            hisPokeSprite.draw(batch);
        }

        batch.end();


        // BECAUSE SHAPE RENDERER IS USED, SHOULD BE AFTER THE BATCH END
        drawLifeBars(myPokeSprite, true);

        drawLifeBars(hisPokeSprite, false);
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
        if (pokemonSprite == null) {
            return;
        }

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
            shapeRenderer.rect(770, 355, width, height);
        } else {
            // top
            shapeRenderer.rect(255, 668, width, height);
        }

        shapeRenderer.end();
    }


    /**
     * Draw menu arrow
     */
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

        Set<Pokemon> pokemons = allPokemons.keySet();
        for (Pokemon pokKey :
                pokemons) {
            allPokemons.get(pokKey).getTexture().dispose();
        }

        winMusic.dispose();
        lostMusic.dispose();

        namesFont.dispose();
        shapeRenderer.dispose();
        spriteinterface.getTexture().dispose();
        spriteinterface.getTexture().dispose();

    }


    /**
     * Play sound
     *
     * @param name
     */
    public void playSound(String name) {
        loadSound(name);
        switch (name) {
            case "click":
                soundMap.get(name).play();
                break;
        }

    }

    /**
     * Sound loading.
     *
     * @param sound
     */
    public void playSound(Sound sound) {
        sound.play();
    }

    Map<String, Sound> soundMap = new HashMap<>();

    /**
     * Loads sounds
     *
     * @param name
     */
    private void loadSound(String name) {
        if (!soundMap.containsKey(name)) {
            Sound sound = Gdx.audio.newSound(Gdx.files.internal("musics/" + name + ".mp3"));
            soundMap.put(name, sound);
        }

    }

    private Sound loadCrySound(int id) {
        String name = "cry_" + id;
        if (!soundMap.containsKey(id)) {
            Sound sound = Gdx.audio.newSound(Gdx.files.internal("pokesounds/" + id + ".mp3"));
            soundMap.put(name, sound);
        }

        return soundMap.get(name);
    }

}
