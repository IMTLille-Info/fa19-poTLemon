package potlemon.screen;

import com.badlogic.gdx.Gdx;
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
import potlemon.core.network.dto.NetworkDTO;
import potlemon.core.network.dto.PlayerDTO;
import potlemon.core.network.events.NetworkEvent;
import potlemon.core.network.server.NetPackage;

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


    /**
     *
     */
    public void addNetworkElement(NetPackage netPackage) {
        // switch for element
        if (netPackage instanceof PlayerDTO) {
            PlayerDTO playerDTO = (PlayerDTO) netPackage;

            // TEST network elements
            networkElements.add(new NetworkPlayer(new Sprite(new Texture("sprites/human/network.png")), (TiledMapTileLayer) map.getLayers().get(0)));
            ((NetworkPlayer) networkElements.get(0)).setPosition(playerDTO.getX(), playerDTO.getY());

        }
    }

    /**
     * Add network listener.
     */
    protected void addNetworkListener() {
        networkClient.addListener(new ClientListener() {
            @Override
            public void onEvent(NetworkEvent event, Object o) {

                /**
                 * NEED TO GET THE LIBGDX THREAD...
                 */
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {

                        if (o instanceof NetworkDTO) {
                            NetworkDTO netDTO = (NetworkDTO) o;

                            if(netDTO.event==null){
                                System.out.println("EVENT NULL");
                                return;
                            }

                            // switch on event
                            switch (netDTO.event) {

                                case TCP_NEW_PLAYER:
                                    System.out.println("Received event: TCP_NEW_PLAYER");
                                    // add a player
                                    addNetworkElement((PlayerDTO)netDTO.data);
                                    break;

                                case TCP_ALL_PLAYERS:
                                    System.out.println("Received event: TCP_ALL_PLAYERS");
                                    // gets all player
                                    for (PlayerDTO playerDTO :
                                            (PlayerDTO[]) netDTO.dataArray) {
                                        addNetworkElement(playerDTO);
                                    }
                                    break;

                                default:
                                    // unknown

                                    System.out.println("Received event: UNKNOWN"+netDTO.event.name());
                                    break;


                            }


                        }

                    }
                });

            }
        });
    }
}
