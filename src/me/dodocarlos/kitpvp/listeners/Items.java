package me.dodocarlos.kitpvp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import me.dodocarlos.kitpvp.utils.Vars;

public class Items implements Listener{
	
	@EventHandler
	public void dropItem(PlayerDropItemEvent e){
		if(e.getItemDrop().getItemStack().getType() == Material.BOWL 
				|| e.getItemDrop().getItemStack().getType() == Material.MUSHROOM_SOUP 
				|| e.getItemDrop().getItemStack().getType() == Material.RED_MUSHROOM 
				|| e.getItemDrop().getItemStack().getType() == Material.BROWN_MUSHROOM
				|| e.getItemDrop().getItemStack().getType() == Material.GLASS_BOTTLE){
			
		}else{
			e.getItemDrop().getItemStack().setAmount(1);
			e.getPlayer().setItemInHand(e.getItemDrop().getItemStack());
			e.getPlayer().updateInventory();
		}
	}
	
	@EventHandler
	public void dropItem(final ItemSpawnEvent e){
		if(e.getEntity().getItemStack().getType() != Material.TNT){
			if(e.getEntity().getItemStack().getType() == Material.BOWL 
					|| e.getEntity().getItemStack().getType() == Material.MUSHROOM_SOUP 
					|| e.getEntity().getItemStack().getType() == Material.RED_MUSHROOM 
					|| e.getEntity().getItemStack().getType() == Material.BROWN_MUSHROOM){
				Bukkit.getScheduler().scheduleSyncDelayedTask(Vars.main, new Runnable(){
					public void run(){
						if(!e.getEntity().isDead()){
							e.getEntity().remove();
							e.getEntity().getWorld().playEffect(e.getLocation(), Effect.COLOURED_DUST, 1);
							e.getEntity().getWorld().playEffect(e.getLocation(), Effect.COLOURED_DUST, 1);
							e.getEntity().getWorld().playEffect(e.getLocation(), Effect.COLOURED_DUST, 1);
						}
					}
				}, 80);			
			}else{
				e.setCancelled(true);
			}
		}
	}
	
}
