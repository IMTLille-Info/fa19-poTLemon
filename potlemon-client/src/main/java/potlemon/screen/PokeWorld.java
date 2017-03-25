package potlemon.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import potlemon.core.interfaces.NetworkElement;
import potlemon.core.model.NetworkPlayer;
import potlemon.core.model.Player;

import java.util.ArrayList;
import java.util.List;

public class PokeWorld implements Screen {

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    private TextureAtlas playerAtlas;
    private Player player;

    // for network elements
    private List<NetworkElement> networkElements = new ArrayList<>();


    public void show() {
        map = new TmxMapLoader().load("sprites/TileMap/WorldStart.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        player = new Player("sprites/human/brendan.png", (TiledMapTileLayer) map.getLayers().get(0));
        player.setPosition(200, 500);


        // TEST network elements
        networkElements.add(new NetworkPlayer(new Sprite(new Texture("sprites/human/network.png")), (TiledMapTileLayer) map.getLayers().get(0)));
        ((NetworkPlayer)networkElements.get(0)).setPosition(200, 500);


        Gdx.input.setInputProcessor(player);
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

            ((NetworkPlayer)elm).draw();

        }

        // Draw player
        player.draw();
    }

    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
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
    }

}
