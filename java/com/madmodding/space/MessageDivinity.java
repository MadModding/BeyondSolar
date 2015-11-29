package com.madmodding.space;

import java.util.Random;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageDivinity implements IMessage {
	private int entityid;

	public MessageDivinity() {
	}

	public MessageDivinity(int id) {
		entityid = id;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		entityid = ByteBufUtils.readVarInt(buf, 5);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeVarInt(buf, (int) (entityid), 5);
	}

	public static class Handler implements IMessageHandler<MessageDivinity, IMessage> {
		@Override
		public IMessage onMessage(final MessageDivinity message, MessageContext ctx) {
			final EntityPlayerSP player = (EntityPlayerSP) (ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer
					: ctx.getServerHandler().playerEntity);
			int x = (int) (player.posX + player.getRNG().nextInt(8) - 4);
			int z = (int) (player.posZ + player.getRNG().nextInt(8) - 4);
			int y = player.worldObj.getTopSolidOrLiquidBlock(new BlockPos(x, player.posY, z)).getY();
			player.setPosition(x, y, z);
			player.setVelocity(0, 0, 0);
			return null;
		}
	}
}
