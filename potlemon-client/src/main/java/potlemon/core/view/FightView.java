
package potlemon.core.view;

import potlemon.model.Player;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class FightView implements Screen{

	private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    
    private SpriteBatch spriteBatch;

    private TextureAtlas playerAtlas;
    private Player player;
    
    public void show() {
        map = new TmxMapLoader().load("../sprites/TileMap/WorldStart.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        
        camera = new OrthographicCamera();
        camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        
        player = new Player(new Sprite(new Texture("../sprites/human/brendan.png")), (TiledMapTileLayer) map.getLayers().get(0));
        spriteBatch = new SpriteBatch();
        player.setPosition(220, 500);
        
        Gdx.input.setInputProcessor(player);
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        //camera.position.set(player.getX() + player.getWidth() /2, player.getHeight() /2,0);
        
        camera.update();

        renderer.setView(camera);
        renderer.render();
        
        spriteBatch.begin();
        player.draw(spriteBatch);
        spriteBatch.end();
    }

    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }

    public void pause() { }

    public void resume() { }

    public void hide() { 
        dispose();
    }

    public void dispose() {
        map.dispose();
        renderer.dispose();
        player.getTexture().dispose();
    }
}

