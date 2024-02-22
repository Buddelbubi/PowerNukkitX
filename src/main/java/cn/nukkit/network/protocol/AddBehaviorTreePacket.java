package cn.nukkit.network.protocol;

import lombok.ToString;

@ToString
public class AddBehaviorTreePacket extends DataPacket {

    public String unknown;

    @Override
    public int pid() {
        return ProtocolInfo.ADD_BEHAVIOR_TREE_PACKET;
    }

    @Override
    public void decode() {

    }

    @Override
    public void encode() {
        this.reset();
        this.putString(unknown);
    }

    public void handle(PacketHandler handler) {
        handler.handle(this);
    }
}
