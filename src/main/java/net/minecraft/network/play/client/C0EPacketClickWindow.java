package net.minecraft.network.play.client;

import java.io.IOException;
import moshi.blossom.util.network.PacketDir;
import moshi.blossom.util.network.Packets;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C0EPacketClickWindow implements Packet<INetHandlerPlayServer> {

    private int windowId;

    private int slotId;

    private int usedButton;

    private short actionNumber;

    private ItemStack clickedItem;

    private int mode;

    public PacketDir pDir() {
        return PacketDir.CLIENT;

    }

    public Packets pType() {
        return Packets.C_CLICK_WINDOW;

    }

public C0EPacketClickWindow() {}

    public C0EPacketClickWindow(int windowId, int slotId, int usedButton,
    int mode, ItemStack clickedItem, short actionNumber) {
        this.windowId = windowId;

        this.slotId = slotId;

        this.usedButton = usedButton;

        this.clickedItem = clickedItem != null ? clickedItem.copy() : null;

        this.actionNumber = actionNumber;

        this.mode = mode;

    }

    public void processPacket(INetHandlerPlayServer handler) {
        handler.processClickWindow(this);

    }

    public void readPacketData(PacketBuffer buf) throws IOException {
        this.windowId = buf.readByte();

        this.slotId = buf.readShort();

        this.usedButton = buf.readByte();

        this.actionNumber = buf.readShort();

        this.mode = buf.readByte();

        this.clickedItem = buf.readItemStackFromBuffer();

    }

    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeByte(this.windowId);

        buf.writeShort(this.slotId);

        buf.writeByte(this.usedButton);

        buf.writeShort(this.actionNumber);

        buf.writeByte(this.mode);

        buf.writeItemStackToBuffer(this.clickedItem);

    }

    // Getters
    public int getWindowId() {
        return this.windowId;

    }

    public int getSlotId() {
        return this.slotId;

    }

    public int getUsedButton() {
        return this.usedButton;

    }

    public short getActionNumber() {
        return this.actionNumber;

    }

    public ItemStack getClickedItem() {
        return this.clickedItem;

    }

    public int getMode() {
        return this.mode;

    }

}
