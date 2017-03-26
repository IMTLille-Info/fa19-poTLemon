package potlemon.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import potlemon.core.interfaces.NetworkElement;
import potlemon.core.model.NetworkPlayer;
import potlemon.core.model.Player;
import potlemon.core.network.client.ClientListener;
import potlemon.core.network.client.PotlemonClient;
import potlemon.core.network.dto.PlayerDTO;
import potlemon.core.network.events.NetworkEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pierre on 25/03/2017.
 */
public abstract class AbstractScreen implements Screen {
    protected final PotlemonClient networkClient;
    // for network elements
    protected List<NetworkElement> networkElements = new ArrayList<>();


    protected TiledMap map;
    protected OrthogonalTiledMapRenderer renderer;
    protected OrthographicCamera camera;

    protected TextureAtlas playerAtlas;
    protected Player player;

    // create network client
    public AbstractScreen() {
        networkClient = PotlemonClient.getInstance();
    }

    protected void addNetworkListener() {
        networkClient.addListener(new ClientListener() {
            @Override
            public void onEvent(NetworkEvent event, Object o) {
                if (o instanceof PlayerDTO[]) {
                    // add players
                    for (PlayerDTO playerDTO :
                            (PlayerDTO[]) o) {


                        // @TODO : Sprite loading outside the context??
                        // TEST network elements
                        networkElements.add(new NetworkPlayer(new Sprite(new Texture("sprites/human/network.png")), (TiledMapTileLayer) map.getLayers().get(0)));
                        ((NetworkPlayer) networkElements.get(0)).setPosition(playerDTO.getX(), playerDTO.getY());

                    }
                }
            }
        });
    }

}
