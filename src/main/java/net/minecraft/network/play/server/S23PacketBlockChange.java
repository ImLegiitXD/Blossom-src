package net.minecraft.network.play.server;

import java.io.IOException;
import moshi.blossom.util.network.PacketDir;
import moshi.blossom.util.network.Packets;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class S23PacketBlockChange implements Packet<INetHandlerPlayClient> {

    private BlockPos blockPosition;

    public PacketDir pDir() {
        return PacketDir.SERVER;

    }

    private IBlockState blockState;

    public Packets pType() {
        return Packets.S_BLOCK_CHANGE;

    }

public S23PacketBlockChange() {}

    public S23PacketBlockChange(World worldIn, BlockPos blockPositionIn) {
        this.blockPosition = blockPositionIn;

        this.blockState = worldIn.getBlockState(blockPositionIn);

    }

    public void readPacketData(PacketBuffer buf) throws IOException {
        this.blockPosition = buf.readBlockPos();

        this.blockState = (IBlockState)Block.BLOCK_STATE_IDS.getByValue(buf.readVarIntFromBuffer());

    }

    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeBlockPos(this.blockPosition);

        buf.writeVarIntToBuffer(Block.BLOCK_STATE_IDS.get(this.blockState));

    }

    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleBlockChange(this);

    }

    public IBlockState getBlockState() {
        return this.blockState;

    }

    public BlockPos getBlockPosition() {
        return this.blockPosition;

    }

}
