package com.madmodding.space.blocks;

import com.madmodding.space.Main;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Meteor extends Block {
	public Meteor(Material mat) {
		super(mat);
		this.setCreativeTab(Main.aliensTabBlock);
	}
}
