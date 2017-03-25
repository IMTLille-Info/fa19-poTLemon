package potlemon.core.network.client;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Server;
import potlemon.core.network.ServerConfigs;
import potlemon.core.network.ServerConfigs;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

/**
 * Created by Pierre on 24/03/2017.
 */
public class PotlemonClient {
    private static Client client;

    private static PotlemonClient potlemonClient = null;

    /**
     * Gets client instance.
     * @return
     */
    public static PotlemonClient getInstance(){
        if(potlemonClient==null){
            potlemonClient=new PotlemonClient();
        }
        return potlemonClient;
    }

    private PotlemonClient() {

        client = new Client();
        client.start();

        InetAddress inetAddress = client.discoverHost(ServerConfigs.PORT_UDP, 3);

        if(inetAddress!=null){
            startConnection(inetAddress);
        }else {
            System.out.println("No discovered server on this network.");
        }

    }

    private static void startConnection(InetAddress inetAddress) {

        try {
            System.out.println("Trying to connect on: " + inetAddress.getHostName()+"...");
            client.connect(5000, inetAddress, ServerConfigs.PORT_TCP,ServerConfigs.PORT_UDP);

            System.out.println("successfully connected");
        } catch (IOException e) {
            System.out.println("ERROR: can't connect");
            e.printStackTrace();

        }
    }


    public static void main(String[] args) {
        PotlemonClient.getInstance();
    }
}
