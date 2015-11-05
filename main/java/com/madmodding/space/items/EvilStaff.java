package com.madmodding.space.items;

import com.madmodding.space.Main;
import com.madmodding.space.entity.item.EntityFireAttack;
import com.madmodding.space.entity.item.EntityIce;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EvilStaff extends ItemSword {

	public EvilStaff(String name, ToolMaterial mat) {
		super(mat);
		this.setUnlocalizedName(name);
		this.setCreativeTab(Main.aliensTab);
	}
	
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
    	if(player.isSneaking()) {
    		
    		world.spawnEntityInWorld(new EntityFireAttack(world, player));
			}
    	else
    	world.spawnEntityInWorld(new EntityIce(world, player));
		
    	player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        return stack;
    }
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BOW;
    }

    @SideOnly(Side.CLIENT)
    public boolean isFull3D() {
    	return true;
    }
}
