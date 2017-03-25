package potlemon.server.app.controlers;

import potlemon.server.app.exceptions.ServerException;
import potlemon.server.app.loaders.RoomsLoader;
import potlemon.server.app.network.PotlemonServer;
import potlemon.server.app.tools.Logger;

import javax.swing.*;

/**
 * Created by Pierre on 23/03/2017.
 */
public class ServerController {

    /****************
     * ATTRIBUTES
     * **************
     */

    private static ServerController serverController = new ServerController();

    private PotlemonServer potlemonServer = null;
    private RoomsLoader roomsLoader = null;


    /**
     * Returns the instance.
     *
     * @return
     */
    public static ServerController getInstance() {
        return serverController;
    }

    private ServerController() {
        Logger.log(this.getClass().toString(), "creating server controller");

        loadRooms();
    }


    /*****************
     * INIT METHODS
     ***************/
    private void loadRooms() {
        roomsLoader = RoomsLoader.getInstance();
    }

    /*****************
     * SERVER METHODS
     * ***************/


    /**
     * Starts the server.
     *
     * @param tcp
     * @param udp
     */
    public void startServer(int tcp, int udp) {
        Logger.log(this.getClass().toString(), "trying to create the server");
        potlemonServer = new PotlemonServer(tcp, udp);
        try {
            Logger.log(this.getClass().toString(), "Starting...");
            potlemonServer.start();
        } catch (ServerException e) {
            Logger.log(this.getClass().toString(), "Nope, error...");
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            Logger.log(this.getClass().toString(), "NOpe... EXCEPTION");
        }
    }

    /**
     * Stops server.
     */
    public void stopServer() {
        Logger.log(this.getClass().toString(), "trying to destroy the server");

        if (potlemonServer.isStarted()) {
            potlemonServer.stop();
        }

    }


}


