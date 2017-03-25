package potlemon.server.app.network;

import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;
import potlemon.core.network.KryoRegisterClasses;
import potlemon.core.network.dto.NetworkDTO;
import potlemon.server.app.exceptions.ServerException;
import potlemon.server.app.listeners.network.ClientListener;
import potlemon.server.app.tools.Logger;

import java.util.concurrent.LinkedBlockingQueue;

public class PotlemonServer extends Listener implements Disposable {

    private final int portTCP;
    private final int portUDP;

    private Server kryoServer;

    private long startTime = 0;
    private boolean started = false;
    private long tickTime = 0l;

    private LinkedBlockingQueue<Runnable> queue;
    private final ObjectMap<Integer, ConnectedClient> connectedClients;

    /**
     * Creates a server on provided port.
     *
     * @param portTCP
     * @param portUDP
     */
    public PotlemonServer(int portTCP, int portUDP) {
        this.portTCP = portTCP;
        this.portUDP = portUDP;

        queue = new LinkedBlockingQueue<>();
        connectedClients = new ObjectMap<>();

        Log.set(Log.LEVEL_INFO);
    }


    /**
     * Starts the server.
     */
    public void start() throws ServerException {

        try {
            kryoServer = new Server();
            kryoServer.start();
            kryoServer.bind(portTCP, portUDP);

            System.out.println("[KRYO] Port binded");

            started = true;
            startTime = System.currentTimeMillis();

            registerClasses();
            registerListeners();

        } catch (Exception e) {
            e.printStackTrace();

            throw new ServerException("Can't bind ports.", e);
        }
    }


    public boolean isStarted() {
        return started;
    }

    @Override
    public void dispose() {
        if (isStarted() && kryoServer != null) {
            kryoServer.stop();
            kryoServer = null;
            started = false;
        }
    }

    /**
     * Tells Kryo which class will be serialized over network.
     */
    private void registerClasses() {
        // main class
        KryoRegisterClasses.registerNetworkClasses(kryoServer.getKryo());
    }


    /**
     * Adds listeners.
     */
    private void registerListeners() {
        kryoServer.addListener(new Listener.QueuedListener(this) {
            @Override
            protected void queue(Runnable runnable) {
                queue.add(runnable);
            }
        });
    }

    public void tick() {
        System.out.println("Tick time...");
        Runnable run = null;
        while ((run = queue.poll()) != null) {
            run.run();
        }
        tickTime++;
    }


    @Override
    public void connected(Connection connection) {
        super.connected(connection);
        Logger.log(getClass().toString(), "Client " + connection.getRemoteAddressTCP() + " connected.");
        connectedClients.put(connection.getID(), new ConnectedClient("TestClient", connection.getID()));
    }

    @Override
    public void disconnected(Connection connection) {
        Logger.log(getClass().toString(), "Client " + connection.getRemoteAddressTCP() + " disconnected.");
        connectedClients.remove(connection.getID());
    }


    @Override
    public void received(Connection connection, Object o) {

        Logger.log(getClass().toString(), "An object has been received...");
        System.out.println(o);

    }
}

