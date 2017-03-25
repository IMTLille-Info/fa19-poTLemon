package potlemon.core.network;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a server room.
 */
public class ServerRoom {

    /**
     * Contains a TMX name file.
     */
    protected final String roomName;

    protected List<Object> players;


    public ServerRoom(String roomName) {
        this.roomName = roomName;
        players = new ArrayList<>();
    }


    @Override
    public String toString() {
        return "ServerRoom{" +
                "roomName='" + roomName + '\'' +
                ", players=" + players.size() + " connected" +
                '}';
    }
}
