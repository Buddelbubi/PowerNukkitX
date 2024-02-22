package cn.nukkit.network.protocol;

import cn.nukkit.math.BlockVector3;
import lombok.ToString;

/**
 * @author Pub4Game
 * @since 03.07.2016
 */
@ToString
public class ItemFrameDropItemPacket extends DataPacket {

    public static final int NETWORK_ID = ProtocolInfo.ITEM_FRAME_DROP_ITEM_PACKET;

    public int x;
    public int y;
    public int z;

    @Override
    public void decode() {
        BlockVector3 v = this.getBlockVector3();
        this.z = v.z;
        this.y = v.y;
        this.x = v.x;
    }

    @Override
    public void encode() {

    }

    @Override
    public int pid() {
        return NETWORK_ID;
    }

    public void handle(PacketHandler handler) {
        handler.handle(this);
    }
}
