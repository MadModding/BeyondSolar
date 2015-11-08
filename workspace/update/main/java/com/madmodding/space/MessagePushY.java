package com.madmodding.space;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessagePushY implements IMessage {
	private double my;
	public MessagePushY() {
	}

	public MessagePushY(double motionY) {
		my = motionY;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		my = ByteBufUtils.readVarInt(buf, 2)/100;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeVarInt(buf, (int) (my*100), 2);
	}

	public static class Handler implements IMessageHandler<MessagePushY, IMessage> {
		@Override
		public IMessage onMessage(final MessagePushY message, MessageContext ctx) {
			final EntityPlayerSP player = (EntityPlayerSP) (ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer
					: ctx.getServerHandler().playerEntity);
			player.motionY += message.my;
			return null; 
		}
	}
}
