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

	public static void setThrowableHeading(EntityLivingBase elb, Random rand, double x, double y, double z,
			float velocity) {
		float f2 = MathHelper.sqrt_double(x * x + y * y + z * z);
		x /= (double) f2;
		y /= (double) f2;
		z /= (double) f2;
		x *= (double) velocity;
		y *= (double) velocity;
		z *= (double) velocity;
		float f3 = MathHelper.sqrt_double(x * x + z * z);
		elb.prevRotationYaw = elb.rotationYaw = (float) (Math.atan2(x, z) * 180.0D / Math.PI);
		elb.prevRotationPitch = elb.rotationPitch = (float) (Math.atan2(y, (double) f3) * 180.0D / Math.PI);
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
			//setThrowableHeading((EntityLivingBase) player, player.getRNG(),
			//        player.posX - player.worldObj.getEntityByID(message.entityid).posX,
			//        player.posY - player.worldObj.getEntityByID(message.entityid).posY,
			//        player.posZ - player.worldObj.getEntityByID(message.entityid).posZ, 1);
			player.setVelocity(0, 0, 0);
			return null;
		}
	}
}
