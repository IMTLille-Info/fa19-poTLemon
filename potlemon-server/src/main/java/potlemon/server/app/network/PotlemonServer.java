package potlemon.server.app.network;

import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;
import potlemon.core.network.KryoRegisterClasses;
import potlemon.core.network.dto.NetworkDTO;
import potlemon.core.network.dto.PlayerDTO;
import potlemon.core.network.events.NetworkEvent;
import potlemon.core.network.server.NetPackage;
import potlemon.server.app.exceptions.ServerException;
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

        // IS NETWORKDTO?
        if (o instanceof NetworkDTO) {
            processDTO(connection, (NetworkDTO) o);
        } else {
            Logger.log(getClass().toString(), "Unknown object...");

        }

    }

    /**
     * Process DTO
     *
     * @param o
     */
    private void processDTO(Connection connection, NetworkDTO o) {
        // already logged, can get the object
        ConnectedClient connectedClient = connectedClients.get(connection.getID());

        /**
         * HELLO
         */
        if (o.event.equals(NetworkEvent.TCP_HELLO)) {
            Logger.log(getClass().toString(), "Received HELLO");

            // player position in parameter
            // get connected player

            connectedClient.setX(((PlayerDTO) o.data).getX());
            connectedClient.setY(((PlayerDTO) o.data).getY());

            // send others player the notification a player just logged
            sendToAllExceptTCP(connection.getID(), new NetworkDTO(NetworkEvent.TCP_NEW_PLAYER, new PlayerDTO(connectedClient.getID(), connectedClient.getX(), connectedClient.getY())));

            // have to send to this client, the whole connected players
            PlayerDTO[] allClients = new PlayerDTO[connectedClients.size - 1];
            int i = 0;
            ObjectMap.Keys<Integer> keys = connectedClients.keys();
            for (Integer key :
                    keys) {
                ConnectedClient c = connectedClients.get(key);
                if (c.getID() == connectedClient.getID()) {
                    continue;
                }

                allClients[i++] = new PlayerDTO(c.getId(), c.getX(), c.getY());
            }


            // send all clients to logged player
            sendToTCP(connection, new NetworkDTO(NetworkEvent.TCP_ALL_PLAYERS, allClients));

        }

        /**
         *  PLAYER UPDATE POSITION
         */
        else if (o.event.equals(NetworkEvent.TCP_SEND_POSITION)) {
            if (!(o.data instanceof PlayerDTO)) {
                Logger.log(getClass().toString(), "SEND_POSITION failed, not a player dto");
                return;
            }
            connectedClient.setX(((PlayerDTO)o.data).getX());
            connectedClient.setY(((PlayerDTO)o.data).getY());

            // HAVE TO FIND POSITION

        Logger.log(getClass().toString(), "Player " + connectedClient.getID() +" has updated position: "+ connectedClient.getX()+ ","+connectedClient.getY());
        }

    }


    public void sendToAllTCP(NetPackage pkg) {
        pkg.connectionType = NetPackage.TCP;
        pkg.time = tickTime;
        kryoServer.sendToAllTCP(pkg);
    }

    public void sendToAllExceptTCP(int i, NetPackage pkg) {
        pkg.connectionType = NetPackage.TCP;
        pkg.time = tickTime;
        kryoServer.sendToAllExceptTCP(i, pkg);
        Logger.log(getClass().toString(), "Forwarding an event to all except");
    }

    public void sendToAllUDP(NetPackage pkg) {
        pkg.connectionType = NetPackage.UDP;
        pkg.time = tickTime;
        kryoServer.sendToAllUDP(pkg);
    }

    public void sendToAllExceptUDP(int i, NetPackage pkg) {
        pkg.connectionType = NetPackage.UDP;
        pkg.time = tickTime;
        kryoServer.sendToAllExceptUDP(i, pkg);
    }

    public void sendToTCP(Connection connection, NetPackage pkg) {
        pkg.connectionType = NetPackage.TCP;
        pkg.time = tickTime;
        kryoServer.sendToTCP(connection.getID(), pkg);
    }

    public void sendToTCP(Connection connection, NetPackage[] pkg) {
        kryoServer.sendToTCP(connection.getID(), pkg);
    }

    public void sendToUDP(Connection connection, NetPackage pkg) {
        pkg.connectionType = NetPackage.UDP;
        pkg.time = tickTime;
        kryoServer.sendToUDP(connection.getID(), pkg);
    }

}

