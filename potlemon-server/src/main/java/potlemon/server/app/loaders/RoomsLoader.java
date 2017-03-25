package potlemon.server.app.loaders;

import potlemon.core.network.server.ServerRoom;
import potlemon.server.app.tools.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Loads rooms.
 */
public class RoomsLoader {

    private static RoomsLoader instance;
    private List<ServerRoom> rooms;

    /**
     * Returns instance of room loader.
     * @return
     */
    public static RoomsLoader getInstance(){
        if(instance==null){
            instance=new RoomsLoader();
        }
        return instance;
    }

    private RoomsLoader() {
        Logger.log(this.getClass().toString(), "Access");
        rooms = new ArrayList<>();

        loadRooms();
    }


    /**
     * Loads rooms.
     */
    private void loadRooms() {
        rooms.add(new ServerRoom("WorldStart"));
        Logger.log(this.getClass().toString(), "Rooms loaded");
    }


}
