package potlemon.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import potlemon.core.interfaces.NetworkElement;
import potlemon.core.model.NetworkPlayer;
import potlemon.core.model.Player;
import potlemon.core.network.dto.NetworkDTO;
import potlemon.core.network.dto.PlayerDTO;
import potlemon.core.network.events.NetworkEvent;

public class PokeFight extends AbstractScreen {


    public PokeFight() {
        super();
    }


    public void show() {

    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public void resize(int width, int height) {
    }

    public void pause() {
    }

    public void resume() {
    }

    public void hide() {
        dispose();
    }

    public void dispose() {
    }


}
