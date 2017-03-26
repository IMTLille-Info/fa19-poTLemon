package potlemon.core.network;

import com.esotericsoftware.kryo.Kryo;
import potlemon.core.network.dto.NetworkDTO;
import potlemon.core.network.dto.NetworkDTOData;
import potlemon.core.network.dto.PlayerDTO;
import potlemon.core.network.events.NetworkEvent;
import potlemon.core.network.server.NetPackage;

/**
 * Created by Pierre on 25/03/2017.
 */
public class KryoRegisterClasses {
    public static void registerNetworkClasses(Kryo kryo){
        kryo.register(NetworkEvent.class);
        kryo.register(NetworkDTO.class);
        kryo.register(NetworkDTOData.class);
        kryo.register(NetworkDTOData[].class);
        kryo.register(NetPackage.class);
        kryo.register(PlayerDTO.class);
        kryo.register(PlayerDTO[].class);

    }
}
