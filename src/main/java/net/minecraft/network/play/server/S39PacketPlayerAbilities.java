package net.minecraft.network.play.server;

import java.io.IOException;
import moshi.blossom.util.network.PacketDir;
import moshi.blossom.util.network.Packets;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S39PacketPlayerAbilities implements Packet<INetHandlerPlayClient> {

    private boolean invulnerable;

    private boolean flying;

    private boolean allowFlying;

    public PacketDir pDir() {
        return PacketDir.SERVER;

    }

    private boolean creativeMode; private float flySpeed; private float walkSpeed;

    public Packets pType() {
        return Packets.S_PLAYER_ABILITIES;

    }

public S39PacketPlayerAbilities() {}

    public S39PacketPlayerAbilities(PlayerCapabilities capabilities) {
        setInvulnerable(capabilities.disableDamage);

        setFlying(capabilities.isFlying);

        setAllowFlying(capabilities.allowFlying);

        setCreativeMode(capabilities.isCreativeMode);

        setFlySpeed(capabilities.getFlySpeed());

        setWalkSpeed(capabilities.getWalkSpeed());

    }

    public void readPacketData(PacketBuffer buf) throws IOException {
        byte b0 = buf.readByte();

        setInvulnerable(((b0 & 0x1) > 0));

        setFlying(((b0 & 0x2) > 0));

        setAllowFlying(((b0 & 0x4) > 0));

        setCreativeMode(((b0 & 0x8) > 0));

        setFlySpeed(buf.readFloat());

        setWalkSpeed(buf.readFloat());

    }

    public void writePacketData(PacketBuffer buf) throws IOException {
        byte b0 = 0;

        if (isInvulnerable())
        {
            b0 = (byte)(b0 | 0x1);

        }

        if (isFlying())
        {
            b0 = (byte)(b0 | 0x2);

        }

        if (isAllowFlying())
        {
            b0 = (byte)(b0 | 0x4);

        }

        if (isCreativeMode())
        {
            b0 = (byte)(b0 | 0x8);

        }

        buf.writeByte(b0);

        buf.writeFloat(this.flySpeed);

        buf.writeFloat(this.walkSpeed);

    }

    public void processPacket(INetHandlerPlayClient handler) {
        handler.handlePlayerAbilities(this);

    }

    public boolean isInvulnerable() {
        return this.invulnerable;

    }

    public void setInvulnerable(boolean isInvulnerable) {
        this.invulnerable = isInvulnerable;

    }

    public boolean isFlying() {
        return this.flying;

    }

    public void setFlying(boolean isFlying) {
        this.flying = isFlying;

    }

    public boolean isAllowFlying() {
        return this.allowFlying;

    }

    public void setAllowFlying(boolean isAllowFlying) {
        this.allowFlying = isAllowFlying;

    }

    public boolean isCreativeMode() {
        return this.creativeMode;

    }

    public void setCreativeMode(boolean isCreativeMode) {
        this.creativeMode = isCreativeMode;

    }

    public float getFlySpeed() {
        return this.flySpeed;

    }

    public void setFlySpeed(float flySpeedIn) {
        this.flySpeed = flySpeedIn;

    }

    public float getWalkSpeed() {
        return this.walkSpeed;

    }

    public void setWalkSpeed(float walkSpeedIn) {
        this.walkSpeed = walkSpeedIn;

    }

}
