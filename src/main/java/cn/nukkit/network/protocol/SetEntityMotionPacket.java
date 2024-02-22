package cn.nukkit.network.protocol;

import lombok.ToString;

/**
 * @author MagicDroidX (Nukkit Project)
 */
@ToString
public class SetEntityMotionPacket extends DataPacket {
    public static final int NETWORK_ID = ProtocolInfo.SET_ENTITY_MOTION_PACKET;

    public long eid;
    public float motionX;
    public float motionY;
    public float motionZ;

    @Override
    public int pid() {
        return NETWORK_ID;
    }

    @Override
    public void decode() {

    }

    @Override
    public void encode() {
        this.reset();
        this.putEntityRuntimeId(this.eid);
        this.putVector3f(this.motionX, this.motionY, this.motionZ);
    }

    public void handle(PacketHandler handler) {
        handler.handle(this);
    }
}
