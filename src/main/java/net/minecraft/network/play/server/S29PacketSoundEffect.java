package net.minecraft.network.play.server;

import java.io.IOException;
import moshi.blossom.util.network.PacketDir;
import moshi.blossom.util.network.Packets;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.MathHelper;
import org.apache.commons.lang3.Validate;

public class S29PacketSoundEffect implements Packet<INetHandlerPlayClient> {

    private String soundName;

    public PacketDir pDir() {
        return PacketDir.SERVER;

    }

    private int posX;

    public Packets pType() {
        return Packets.S_SOUND_EFFECT;

    }

    private int posY = Integer.MAX_VALUE;

    private int posZ;

    private float soundVolume;

    private int soundPitch;

    public S29PacketSoundEffect(String soundNameIn, double soundX, double soundY, double soundZ, float volume, float pitch) {
        Validate.notNull(soundNameIn, "name", new Object[0]);

        this.soundName = soundNameIn;

        this.posX = (int)(soundX * 8.0D);

        this.posY = (int)(soundY * 8.0D);

        this.posZ = (int)(soundZ * 8.0D);

        this.soundVolume = volume;

        this.soundPitch = (int)(pitch * 63.0F);

        pitch = MathHelper.clamp_float(pitch, 0.0F, 255.0F);

    }

    public void readPacketData(PacketBuffer buf) throws IOException {
        this.soundName = buf.readStringFromBuffer(256);

        this.posX = buf.readInt();

        this.posY = buf.readInt();

        this.posZ = buf.readInt();

        this.soundVolume = buf.readFloat();

        this.soundPitch = buf.readUnsignedByte();

    }

    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeString(this.soundName);

        buf.writeInt(this.posX);

        buf.writeInt(this.posY);

        buf.writeInt(this.posZ);

        buf.writeFloat(this.soundVolume);

        buf.writeByte(this.soundPitch);

    }

    public String getSoundName() {
        return this.soundName;

    }

    public double getX() {
        return (this.posX / 8.0F);

    }

    public double getY() {
        return (this.posY / 8.0F);

    }

    public double getZ() {
        return (this.posZ / 8.0F);

    }

    public float getVolume() {
        return this.soundVolume;

    }

    public float getPitch() {
        return this.soundPitch / 63.0F;

    }

    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleSoundEffect(this);

    }

public S29PacketSoundEffect() {}

}
