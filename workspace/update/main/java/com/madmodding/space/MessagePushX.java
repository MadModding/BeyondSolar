package com.madmodding.space;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessagePushX implements IMessage {
	private double mx;
	public MessagePushX() {
	}

	public MessagePushX(double motionX) {
		mx = motionX;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		mx = ByteBufUtils.readVarInt(buf, 2)/100;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeVarInt(buf, (int) (mx*100), 2);
	}

	public static class Handler implements IMessageHandler<MessagePushX, IMessage> {
		@Override
		public IMessage onMessage(final MessagePushX message, MessageContext ctx) {
			final EntityPlayerSP player = (EntityPlayerSP) (ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer
					: ctx.getServerHandler().playerEntity);
			player.motionX += message.mx;
			return null; 
		}
	}
}
