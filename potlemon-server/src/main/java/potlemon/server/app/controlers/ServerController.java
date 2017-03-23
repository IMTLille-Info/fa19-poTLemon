package potlemon.server.app.controlers;

/**
 * Created by Pierre on 23/03/2017.
 */
public class ServerController {

    private static ServerController serverController = new ServerController();

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
}


