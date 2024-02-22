package cn.nukkit.network.protocol;

import lombok.ToString;

@ToString
public class ShowCreditsPacket extends DataPacket {

    public static final int NETWORK_ID = ProtocolInfo.SHOW_CREDITS_PACKET;

    public static final int STATUS_START_CREDITS = 0;
    public static final int STATUS_END_CREDITS = 1;

    public long eid;
    public int status;

    @Override
    public int pid() {
        return NETWORK_ID;
    }

    @Override
    public void decode() {
        this.eid = this.getEntityRuntimeId();
        this.status = this.getVarInt();
    }

    @Override
    public void encode() {
        this.reset();
        this.putEntityRuntimeId(this.eid);
        this.putVarInt(this.status);
    }

    public void handle(PacketHandler handler) {
        handler.handle(this);
    }
}
