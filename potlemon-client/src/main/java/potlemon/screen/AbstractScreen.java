package potlemon.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import potlemon.core.interfaces.NetworkElement;
import potlemon.core.model.NetworkPlayer;
import potlemon.core.model.Player;
import potlemon.core.model.PlayerEvent;
import potlemon.core.network.client.PotlemonClient;
import potlemon.core.network.dto.NetworkDTO;
import potlemon.core.network.dto.PlayerDTO;
import potlemon.core.network.events.NetworkEvent;
import potlemon.core.network.server.NetPackage;
import potlemon.core.tools.ClientListener;

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

    protected Player player;

    public Music music;

    protected float initTime = 0;

    // create network client
    public AbstractScreen() {
        initTime=Gdx.graphics.getDeltaTime();

        networkClient = PotlemonClient.getInstance();

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera(1, h / w);

    }


    /**
     *
     */
    public void addNetworkElement(NetPackage netPackage) {
        // switch for element
        if (netPackage instanceof PlayerDTO) {
            PlayerDTO playerDTO = (PlayerDTO) netPackage;

            // TEST network elements
            networkElements.add(new NetworkPlayer(playerDTO.getId(), new Sprite(new Texture("sprites/human/network.png")), (TiledMapTileLayer) map.getLayers().get(0)));
            ((NetworkPlayer) networkElements.get(0)).setPosition(playerDTO.getX(), playerDTO.getY());

        }
    }

    /**
     * Add network listener.
     */
    protected void addNetworkListener() {

        // add a listener to the client
        networkClient.addListener(new ClientListener() {
            @Override
            public void onEvent(PlayerEvent event, Object o) {

            }

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

                            if (netDTO.event == null) {
                                System.out.println("EVENT NULL");
                                return;
                            }

                            // switch on event
                            switch (netDTO.event) {

                                case TCP_NEW_PLAYER:
                                    System.out.println("Received event: TCP_NEW_PLAYER");
                                    // add a player
                                    addNetworkElement((PlayerDTO) netDTO.data);
                                    break;

                                case TCP_ALL_PLAYERS:
                                    System.out.println("Received event: TCP_ALL_PLAYERS");
                                    // gets all player
                                    for (PlayerDTO playerDTO :
                                            (PlayerDTO[]) netDTO.dataArray) {
                                        addNetworkElement(playerDTO);
                                    }
                                    break;

                                case TCP_UPDATE_POSITIONS:
                                    // a player has updated its position
                                    System.out.println("Received event: TCP_UPDATE_POSITIONS");

                                    PlayerDTO movedPlayer = (PlayerDTO) ((NetworkDTO) o).data;

                                    // find the network element with the same id...
                                    for (NetworkElement networkElement :
                                            networkElements) {
                                        if (!(networkElement instanceof NetworkPlayer)) {
                                            continue;
                                        }

                                        NetworkPlayer networkPlayer = (NetworkPlayer) networkElement;
                                        if (networkPlayer.getId() == movedPlayer.getId()) {
                                            networkPlayer.setX(movedPlayer.getX());
                                            networkPlayer.setY(movedPlayer.getY());
                                            System.out.println("moved player");
                                        }
                                    }

                                    break;

                                default:
                                    // unknown

                                    System.out.println("Received event: UNKNOWN" + netDTO.event.name());
                                    break;


                            }


                        }

                    }
                });

            }

            @Override
            public void onPlayerEvent(PlayerEvent playerEvent, Player player) {
            }

        });

        // add emit listener for the player
        player.addListener(new ClientListener() {
            @Override
            public void onEvent(PlayerEvent event, Object o) {

            }

            @Override
            public void onEvent(NetworkEvent event, Object o) {
            }

            @Override
            public void onPlayerEvent(PlayerEvent playerEvent, Player player) {
                switch (playerEvent) {
                    case UPDATED_POSITON:
                        //System.out.println("SEND POSITION TO SERVER");

                        networkClient.sendUDPServer(new NetworkDTO(NetworkEvent.TCP_SEND_POSITION, new PlayerDTO(player.getX(), player.getY())));

                        break;
                }
            }
        });
    }
}
