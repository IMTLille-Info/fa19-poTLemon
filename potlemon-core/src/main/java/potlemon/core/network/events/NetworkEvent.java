package potlemon.core.network.events;

import java.io.Serializable;


/**
 * Created by Pierre on 25/03/2017.
 */
public enum NetworkEvent implements Serializable
{
    TCP_HELLO, TCP_NEW_PLAYER, TCP_ALL_PLAYERS,
    TCP_SEND_POSITION,
    TCP_UPDATE_POSITIONS;

    NetworkEvent(){

    }
}
