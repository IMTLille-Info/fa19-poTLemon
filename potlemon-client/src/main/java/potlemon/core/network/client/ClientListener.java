package potlemon.core.network.client;

import potlemon.core.network.events.NetworkEvent;

/**
 * Created by Pierre on 25/03/2017.
 */
public abstract class ClientListener {
    public abstract void onEvent(NetworkEvent event, Object o);
}
