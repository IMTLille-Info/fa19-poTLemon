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


            // TICK UPDATE
            Thread runner = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        long then = System.currentTimeMillis();

                        potlemonServer.tick();

                        long now = System.currentTimeMillis();
                        long sleeptime = 16 - (now-then);
                        if (sleeptime > 0)
                            try {
                                Thread.sleep(sleeptime);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                    }
                }
            });
            runner.start();


        } catch (ServerException e) {
            Logger.log(this.getClass().toString(), "Nope, error...");
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
            potlemonServer.dispose();
        }

    }


}


