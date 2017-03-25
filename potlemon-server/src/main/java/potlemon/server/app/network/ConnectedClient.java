package potlemon.server.app.network;

/**
 * Created by Pierre on 25/03/2017.
 */
public class ConnectedClient {
    private final String username;
    private final int id;

    public ConnectedClient(String username, int id) {
        this.username = username;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public int getID() {
        return id;
    }
}
