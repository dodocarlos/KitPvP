package me.dodocarlos.kitpvp.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Sopa implements Listener{

	@EventHandler
	public void tomarSopa(PlayerInteractEvent e){
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(e.getPlayer().getItemInHand().getType() == Material.MUSHROOM_SOUP){
				Damageable d = (Damageable) e.getPlayer();
				if(d.getHealth() < 20){
					d.setHealth(d.getHealth() + 7 > 20 ? d.getMaxHealth() : d.getHealth() + 7);
					e.getPlayer().getItemInHand().setType(Material.BOWL);
				}
			}
		}
	}
	
}
