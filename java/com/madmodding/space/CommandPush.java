package com.madmodding.space;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class CommandPush implements ICommand {
	private final List aliases;

	public CommandPush() {
		aliases = new ArrayList();
		aliases.add("push");
		aliases.add("p");
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}

	@Override
	public String getName() {
		return "push";
	}

	@Override
	public String getCommandUsage(ICommandSender var1) {
		return "push <motionX> <motionY> <motionZ>";
	}

	@Override
	public List getAliases() {
		return this.aliases;
	}

	@Override
	public void execute(ICommandSender sender, String[] args) throws CommandException {

		World world = sender.getEntityWorld();

		if (world.isRemote) {
		} else {
			try {
				((EntityPlayer) sender.getCommandSenderEntity()).addVelocity(Double.parseDouble(args[0]),
						Double.parseDouble(args[1]), Double.parseDouble(args[2]));
				Main.network.sendTo(new MessagePushX(Double.parseDouble(args[0])),
						((EntityPlayerMP) sender.getCommandSenderEntity()));
				Main.network.sendTo(new MessagePushY(Double.parseDouble(args[1])),
						((EntityPlayerMP) sender.getCommandSenderEntity()));
				Main.network.sendTo(new MessagePushZ(Double.parseDouble(args[2])),
						((EntityPlayerMP) sender.getCommandSenderEntity()));
			} catch (NumberFormatException e) {
				throw new WrongUsageException("commands.push.usage", new Object[0]);
			}
		}
	}

	@Override
	public boolean canCommandSenderUse(ICommandSender var1) {
		return true;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender var1, String[] var2, BlockPos pos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] var1, int var2) {
		// TODO Auto-generated method stub
		return false;
	}

}