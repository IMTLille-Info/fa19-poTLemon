package potlemon.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import potlemon.core.GameManager;
import potlemon.core.interfaces.NetworkElement;
import potlemon.core.model.NetworkPlayer;
import potlemon.core.model.Player;
import potlemon.core.model.PlayerEvent;
import potlemon.core.network.dto.NetworkDTO;
import potlemon.core.network.dto.PlayerDTO;
import potlemon.core.network.events.NetworkEvent;
import potlemon.core.tools.ClientListener;
import potlemon.core.tools.MapKeys;
import potlemon.render.TiledMapGame;

import java.util.Iterator;
import java.util.Random;

public class PokeWorld extends AbstractScreen {


    private static String mapfile;
    private static TiledMapGame renderGame;

    private static GameManager gameManager = GameManager.getInstance();

    private static Random random = new Random();

    public PokeWorld() throws Exception {
        // should have a map file...
        if (mapfile == null) {
            throw new Exception("Can't be called.");
        }
    }

    public PokeWorld(String mapfile) {
        super();
        this.mapfile = mapfile;
    }

    public PokeWorld(String mapfile, TiledMapGame renderGame) {
        super();
        this.mapfile = mapfile;
        this.renderGame = renderGame;
    }

    public void show() {
        map = new TmxMapLoader().load(mapfile);
        renderer = new OrthogonalTiledMapRenderer(map);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


        // check if player position is defined

        player = new Player("sprites/human/brendan.png", (TiledMapTileLayer) map.getLayers().get(0));
        player.setPosition(200, 500);
        Gdx.input.setInputProcessor(player);


        // add listener for player
        player.addListener(new ClientListener() {
            @Override
            public void onEvent(PlayerEvent event, Object o) {
                if (event.equals(PlayerEvent.NEW_CELL)) {
                    processNewCellEvent((TiledMapTileLayer.Cell) o);
                }
            }

            @Override
            public void onEvent(NetworkEvent event, Object o) {
            }

            @Override
            public void onPlayerEvent(PlayerEvent playerEvent, Player player) {
            }


            /**
             * If new cell processed
             * @param cell
             */
            public void processNewCellEvent(TiledMapTileLayer.Cell cell) {
                if (cell == null) {
                    return;
                }

                MapProperties properties = cell.getTile().getProperties();

                if (properties.containsKey(MapKeys.MAP.getString())) {
                    // load new map...
                    System.out.println("load new map...");

                    renderGame.changeMap(properties.get(MapKeys.MAP.getString()).toString());

                } else if (properties.containsKey(MapKeys.HERB.getString())) {
                    // declare new fight...
                    // check we're allowed to declare a fight... every 16 frame
                    if (Gdx.graphics.getDeltaTime() % 1024 > 2) {
                        return;
                    }

                    System.out.println("new fight...");
                    if (random.nextInt(1000) > 990) {
                        renderGame.loadFight();
                    }

                }

            }
        });


        // SEND SERVER
        if (networkClient.isStarted()) {
            // add listener for network elements
            addNetworkListener();

            //send HELLO to server
            networkClient.sendTCPServer(new NetworkDTO(NetworkEvent.TCP_HELLO, new PlayerDTO(player.getX(), player.getY())));
        }

        // START MUSIC
        music = Gdx.audio.newMusic(Gdx.files.internal("musics/map.mp3"));
        music.setLooping(true);
        music.play();


    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //camera.position.set(player.getX() + player.getWidth() /2, player.getHeight() /2,0);

        camera.update();

        renderer.setView(camera);
        renderer.render();


        /**
         * DRAW NETWORK ELEMENTS
         */
        // Draw player
        player.draw();

        for (NetworkElement elm :
                networkElements) {
            if (!(elm instanceof NetworkPlayer)) {
                continue;
            }

            ((NetworkPlayer) elm).draw();

        }

        // Draw player
        player.draw();
    }

    public void resize(int width, int height) {
       /* camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();*/
    }

    public void pause() {
    }

    public void resume() {
    }

    public void hide() {
        dispose();
    }

    public void dispose() {
        map.dispose();
        renderer.dispose();
        player.getTexture().dispose();

        // free elms
        for (NetworkElement netElm : networkElements) {
            if (netElm instanceof Sprite) {
                ((Sprite) netElm).getTexture().dispose();
            }
        }

        // clear music
        music.stop();
        music.dispose();
    }


}
