package potlemon.core.network.dto;

import potlemon.core.network.NetworkEvent;

/**
 * Created by Pierre on 25/03/2017.
 */
public class NetworkDTO {

    public NetworkEvent event;

    public NetworkDTOData data;

    /**
     * Sends event on network.
     *
     * @param event
     */
    public NetworkDTO(NetworkEvent event) {
        this.event = event;
    }



}
