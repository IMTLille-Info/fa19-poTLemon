package potlemon.server.app.controlers;

import potlemon.server.app.exceptions.ServerException;
import potlemon.server.app.network.PotlemonServer;

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


    /**
     * Returns the instance.
     *
     * @return
     */
    public static ServerController getInstance() {
        return serverController;
    }

    private ServerController() {
        System.out.println("[ServerController] creating server controller");
    }


    /**
     * PUBLIC METHODS
     */

    public void startServer(int tcp, int udp) {
        System.out.println("[ServerController] trying to create the server");
        potlemonServer = new PotlemonServer(tcp, udp);
        try {
            System.out.println("Starting...");
            potlemonServer.start();
        } catch (ServerException e) {
            System.out.println("Nope, error...");
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch(Exception e){
            System.out.println("NOpe... EXCEPTION");
        }
    }


    public void stopServer() {
        System.out.println("[ServerController] trying to destroy the server");

        if (potlemonServer.isStarted()) {
            potlemonServer.stop();
        }

    }


}


