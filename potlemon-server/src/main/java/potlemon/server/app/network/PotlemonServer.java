package potlemon.server.app.network;

import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;
import potlemon.server.app.exceptions.ServerException;

import java.io.IOException;

public class PotlemonServer {

    private final int portTCP;
    private final int portUDP;

    private Server kryoServer;


    private boolean started = false;

    /**
     * Creates a server on provided port.
     *
     * @param portTCP
     * @param portUDP
     */
    public PotlemonServer(int portTCP, int portUDP) {
        this.portTCP = portTCP;
        this.portUDP = portUDP;

        Log.set(Log.LEVEL_DEBUG);
    }


    /**
     * Starts the server.
     */
    public void start() throws ServerException {

        try {
            kryoServer = new Server();
            kryoServer.start();
            kryoServer.bind(portTCP,portUDP);

            System.out.println("[KRYO] Port binded");

            started =true;
        } catch (Exception e) {
            e.printStackTrace();

            throw new ServerException("Can't bind ports.", e);
        }
    }


    public boolean isStarted() {
        return started;
    }

    public void stop() {
        if(isStarted() && kryoServer!=null){
            kryoServer.stop();
            kryoServer=null;
            started=false;
        }
    }
}

