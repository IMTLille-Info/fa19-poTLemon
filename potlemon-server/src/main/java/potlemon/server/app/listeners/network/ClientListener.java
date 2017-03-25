package potlemon.server.app.listeners.network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import potlemon.server.app.tools.Logger;

/**
 * Created by Pierre on 25/03/2017.
 */
public class ClientListener extends Listener {
    @Override
    public void connected(Connection connection) {
        super.connected(connection);
        Logger.log(getClass().toString(), "A client is now connected");
    }

    @Override
    public void disconnected(Connection connection) {
        super.disconnected(connection);
        Logger.log(getClass().toString(), "A client has disconnected");

    }
}
