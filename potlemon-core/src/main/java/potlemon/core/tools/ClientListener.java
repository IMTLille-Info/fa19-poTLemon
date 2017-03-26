package potlemon.core.tools;

import potlemon.core.model.Player;
import potlemon.core.model.PlayerEvent;
import potlemon.core.network.events.NetworkEvent;

/**
 * Created by Pierre on 25/03/2017.
 */
public abstract class ClientListener {
    public abstract void onEvent(NetworkEvent event, Object o);

    public abstract void onPlayerEvent(PlayerEvent playerEvent, Player player);

}
