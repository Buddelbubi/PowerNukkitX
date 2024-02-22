package cn.nukkit.network.protocol;


public class RemoveObjectivePacket extends DataPacket {

    public String objectiveName;

    @Override
    public int pid() {
        return ProtocolInfo.REMOVE_OBJECTIVE_PACKET;
    }

    @Override
    public void decode() {
        //only server -> client
    }

    @Override
    public void encode() {
        this.reset();
        this.putString(this.objectiveName);
    }

    public void handle(PacketHandler handler) {
        handler.handle(this);
    }
}
