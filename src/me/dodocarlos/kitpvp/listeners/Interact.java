package me.dodocarlos.kitpvp.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class Interact implements Listener{

	@EventHandler
	public void interact(PlayerInteractEvent e){
		if(e.getPlayer().getItemInHand().getType().equals(Material.WOOD_SWORD)
				|| e.getPlayer().getItemInHand().getType().equals(Material.STONE_SWORD)
				|| e.getPlayer().getItemInHand().getType().equals(Material.IRON_SWORD)
				|| e.getPlayer().getItemInHand().getType().equals(Material.GOLD_SWORD)
				|| e.getPlayer().getItemInHand().getType().equals(Material.DIAMOND_SWORD)
				|| e.getPlayer().getItemInHand().getType().equals(Material.WOOD_AXE)
				|| e.getPlayer().getItemInHand().getType().equals(Material.STONE_AXE)){
			
			e.getPlayer().getItemInHand().setDurability((short) 1);
			e.getPlayer().updateInventory();
			
		}
	}
	
}
