package potlemon.core.network.client;

import com.esotericsoftware.kryonet.Client;
import potlemon.core.network.KryoRegisterClasses;
import potlemon.core.network.dto.NetworkDTO;
import potlemon.core.network.events.NetworkEvent;
import potlemon.core.network.config.ServerConfigs;
import potlemon.core.network.exceptions.NetworkClientException;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by Pierre on 24/03/2017.
 */
public class PotlemonClient {
    private static Client client;

    private static PotlemonClient potlemonClient = null;

    private boolean started = false;
    private Thread clientThread;


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

            registerClasses();

            serverSend_HELLO();

            started = true;
        } catch (IOException e) {
            System.out.println("ERROR: can't connect");
            e.printStackTrace();

        }
    }

    public void stop() {
        if (client.isConnected()) {
            client.stop();
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
    private void serverSend_HELLO() {
        client.sendTCP(NetworkEvent.TCP_HELLO);
    }


}
