package potlemon.core.network.dto;

import potlemon.core.network.server.NetPackage;

/**
 * Created by Pierre on 25/03/2017.
 */
public class PlayerDTO extends NetPackage implements NetworkDTOData {
    private int id;
    public float x;
    public float y;

    public PlayerDTO(int id, float x, float y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }
    public PlayerDTO(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public PlayerDTO() {
    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }
}
