package potlemon.core.network.client;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import potlemon.core.network.KryoRegisterClasses;
import potlemon.core.network.dto.NetworkDTO;
import potlemon.core.network.config.ServerConfigs;
import potlemon.core.network.exceptions.NetworkClientException;
import potlemon.core.tools.ClientListener;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pierre on 24/03/2017.
 */
public class PotlemonClient extends Listener {
    private static Client client;

    private static PotlemonClient potlemonClient = null;

    private boolean started = false;
    private Thread clientThread;

    private List<ClientListener> listeners = new ArrayList<>();

    /**
     * Gets client instance.
     *
     * @return
     */
    public static PotlemonClient getInstance() {
        if (potlemonClient == null) {
            potlemonClient = new PotlemonClient();
        }
        return potlemonClient;
    }

    private PotlemonClient() {

    }

    public void start() throws NetworkClientException {

        client = new Client();
        clientThread = new Thread(client);
        clientThread.start();

        InetAddress inetAddress = client.discoverHost(ServerConfigs.PORT_UDP, 3);

        if (inetAddress != null) {
            startConnection(inetAddress);
        } else {
            client.stop();
            throw new NetworkClientException("No server discovered on this network.");
        }

    }

    private void startConnection(InetAddress inetAddress) {

        try {
            System.out.println("Trying to connect on: " + inetAddress.getHostName() + "...");
            client.connect(5000, inetAddress, ServerConfigs.PORT_TCP, ServerConfigs.PORT_UDP);

            System.out.println("successfully connected");
            started = true;

            registerClasses();

            // have to register listener
            client.addListener(this);

        } catch (IOException e) {
            System.out.println("ERROR: can't connect");
            e.printStackTrace();

        }
    }

    public void stop() {
        if (client!=null && client.isConnected()) {
            client.stop();
            started=false;
        }
    }

    /**
     *
     */
    private void registerClasses() {
        KryoRegisterClasses.registerNetworkClasses(client.getKryo());
    }


    /************************
     * EVENTS SENT TO SERVER
     ***********************/


    /**
     * SEND HELLO TO THE SERVER
     */

    public boolean isStarted() {
        return started;
    }

    public void sendTCPServer(NetworkDTO networkDTO) {
        System.out.println("Sending to server...");
        client.sendTCP(networkDTO);
    }

    public void sendUDPServer(NetworkDTO networkDTO) {
        client.sendUDP(networkDTO);
    }

    @Override
    public void received(Connection connection, Object o) {
        for (ClientListener list :
                listeners) {
            list.onEvent(null, o);
        }
    }

    public void addListener(ClientListener clientListener) {
        listeners.add(clientListener);
    }

    /**
     * Removes all listeners
     */
    public void removeListeners() {
        listeners = new ArrayList<>();
    }

}

