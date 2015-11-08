package com.madmodding.space;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessagePushZ implements IMessage {
	private double mz;
	public MessagePushZ() {
	}

	public MessagePushZ(double motionZ) {
		mz = motionZ;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		mz = ByteBufUtils.readVarInt(buf, 2)/100;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeVarInt(buf, (int) (mz*100), 2);
	}

	public static class Handler implements IMessageHandler<MessagePushZ, IMessage> {
		@Override
		public IMessage onMessage(final MessagePushZ message, MessageContext ctx) {
			final EntityPlayerSP player = (EntityPlayerSP) (ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer
					: ctx.getServerHandler().playerEntity);
			player.motionZ += message.mz;
			return null; 
		}
	}
}
