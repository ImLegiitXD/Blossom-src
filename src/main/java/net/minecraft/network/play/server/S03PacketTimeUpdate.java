package net.minecraft.network.play.server;

import java.io.IOException;
import moshi.blossom.util.network.PacketDir;
import moshi.blossom.util.network.Packets;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S03PacketTimeUpdate implements Packet<INetHandlerPlayClient> {

    private long totalWorldTime;

    public PacketDir pDir() {
        return PacketDir.SERVER;

    }

    private long worldTime;

    public Packets pType() {
        return Packets.S_TIME_UPDATE;

    }

public S03PacketTimeUpdate() {}

    public S03PacketTimeUpdate(long totalWorldTimeIn, long totalTimeIn, boolean doDayLightCycle) {
        this.totalWorldTime = totalWorldTimeIn;

        this.worldTime = totalTimeIn;

        if (!doDayLightCycle) {
            this.worldTime = -this.worldTime;

            if (this.worldTime == 0L)
            {
                this.worldTime = -1L;

            }

        }

    }

    public void readPacketData(PacketBuffer buf) throws IOException {
        this.totalWorldTime = buf.readLong();

        this.worldTime = buf.readLong();

    }

    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeLong(this.totalWorldTime);

        buf.writeLong(this.worldTime);

    }

    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleTimeUpdate(this);

    }

    public long getTotalWorldTime() {
        return this.totalWorldTime;

    }

    public long getWorldTime() {
        return this.worldTime;

    }

}
