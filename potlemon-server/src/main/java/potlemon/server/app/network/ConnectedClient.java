package potlemon.server.app.network;

/**
 * Created by Pierre on 25/03/2017.
 */
public class ConnectedClient {
    private final String username;
    private final int id;

    private float x;
    private float y;

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

    public int getId() {
        return id;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
