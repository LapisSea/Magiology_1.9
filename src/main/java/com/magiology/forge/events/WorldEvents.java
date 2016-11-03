package com.magiology.forge.events;

import com.magiology.util.interf.IBlockBreakListener;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WorldEvents{
	
	public static final WorldEvents instance=new WorldEvents();
	
	@SubscribeEvent
	public void blockBreakEvent(BreakEvent e){
		
		IBlockState state=e.getState();
		Block block=state.getBlock();
		if(block instanceof IBlockBreakListener)((IBlockBreakListener)block).onBroken(e.getWorld(), e.getPos(), state);
		else{
			TileEntity tile=e.getWorld().getTileEntity(e.getPos());
			if(tile instanceof IBlockBreakListener)((IBlockBreakListener)tile).onBroken(e.getWorld(), e.getPos(), state);
		}
		
	}
	
}