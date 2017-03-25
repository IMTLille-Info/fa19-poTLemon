package potlemon.core.network.server;

/**
 * Created by Pierre on 25/03/2017.
 */
public abstract class NetPackage {

    public static final byte TCP = 1;
    public static final byte UDP = 2;

    public long time = 0;
    public byte connectionType = 0;

    public NetPackage() {
    }

    public NetPackage(long time, byte type) {
        this.time = time;
        this.connectionType = type;
    }

    @Override
    public String toString() {
        return String.format("[NetPackage]: type: %s, time: %d", /*type == TCP ? */"TCP"/* : "UDP"*/, time);
    }
}
