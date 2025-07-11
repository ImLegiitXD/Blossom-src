package net.minecraft.network.play.server;

import java.io.IOException;
import moshi.blossom.util.network.PacketDir;
import moshi.blossom.util.network.Packets;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S2BPacketChangeGameState

implements Packet<INetHandlerPlayClient>
{
    public PacketDir pDir() {
        return PacketDir.SERVER;

    }

    public Packets pType() {
        return Packets.S_CHANGE_GAME_STATE;

    }

public static final String[] MESSAGE_NAMES = new String[] { "tile.bed.notValid" };

    private int state;

    private float field_149141_c;

public S2BPacketChangeGameState() {}

    public S2BPacketChangeGameState(int stateIn, float p_i45194_2_) {
        this.state = stateIn;

        this.field_149141_c = p_i45194_2_;

    }

    public void readPacketData(PacketBuffer buf) throws IOException {
        this.state = buf.readUnsignedByte();

        this.field_149141_c = buf.readFloat();

    }

    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeByte(this.state);

        buf.writeFloat(this.field_149141_c);

    }

    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleChangeGameState(this);

    }

    public int getGameState() {
        return this.state;

    }

    public float func_149137_d() {
        return this.field_149141_c;

    }

}
