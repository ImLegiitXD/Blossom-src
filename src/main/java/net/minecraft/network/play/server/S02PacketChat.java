package net.minecraft.network.play.server;

import java.io.IOException;
import moshi.blossom.util.network.PacketDir;
import moshi.blossom.util.network.Packets;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.IChatComponent;

public class S02PacketChat implements Packet<INetHandlerPlayClient> {

    private IChatComponent chatComponent;

    public PacketDir pDir() {
        return PacketDir.SERVER;

    }

    private byte type;

    public Packets pType() {
        return Packets.S_CHAT;

    }

public S02PacketChat() {}

    public S02PacketChat(IChatComponent component) {
        this(component, (byte)1);

    }

    public S02PacketChat(IChatComponent message, byte typeIn) {
        this.chatComponent = message;

        this.type = typeIn;

    }

    public void readPacketData(PacketBuffer buf) throws IOException {
        this.chatComponent = buf.readChatComponent();

        this.type = buf.readByte();

    }

    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeChatComponent(this.chatComponent);

        buf.writeByte(this.type);

    }

    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleChat(this);

    }

    public IChatComponent getChatComponent() {
        return this.chatComponent;

    }

    public boolean isChat() {
        return (this.type == 1 || this.type == 2);

    }

    public byte getType() {
        return this.type;

    }

}
