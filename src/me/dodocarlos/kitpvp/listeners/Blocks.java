package me.dodocarlos.kitpvp.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import me.dodocarlos.kitpvp.utils.Arrays;

public class Blocks implements Listener{
	
	@EventHandler
	public void breakBlock(BlockBreakEvent e){
		if(!Arrays.build.contains(e.getPlayer().getName())){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void placeBlock(BlockPlaceEvent e){
		if(!Arrays.build.contains(e.getPlayer().getName())){
			e.setCancelled(true);
		}
	}
	
}
