package net.minecraft.network.play.client;

import java.io.IOException;
import moshi.blossom.util.network.PacketDir;
import moshi.blossom.util.network.Packets;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C01PacketChatMessage implements Packet<INetHandlerPlayServer> {

    private String message;

    public PacketDir pDir() {
        return PacketDir.CLIENT;

    }

    public Packets pType() {
        return Packets.C_CHAT_MESSAGE;

    }

public C01PacketChatMessage() {}

    public C01PacketChatMessage(String messageIn) {
        if (messageIn.length() > 100) {
            messageIn = messageIn.substring(0, 100);

        }

        this.message = messageIn;

    }

    public void readPacketData(PacketBuffer buf) throws IOException {
        this.message = buf.readStringFromBuffer(100);

    }

    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeString(this.message);

    }

    public void processPacket(INetHandlerPlayServer handler) {
        handler.processChatMessage(this);

    }

    public String getMessage() {
        return this.message;

    }

}
