package potlemon.core.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;
import potlemon.core.interfaces.NetworkElement;
import potlemon.core.tools.MapKeys;


public class NetworkPlayer extends Player implements NetworkElement {

    public NetworkPlayer(Sprite sprite, TiledMapTileLayer collisionLayer) {
        super(sprite, collisionLayer);
    }

    public NetworkPlayer(String texturePath, TiledMapTileLayer collisionLayer) {
        super(texturePath, collisionLayer);
    }
}
