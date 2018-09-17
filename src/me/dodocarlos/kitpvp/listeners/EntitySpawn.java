package me.dodocarlos.kitpvp.listeners;

import org.bukkit.entity.Animals;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EntitySpawn implements Listener{

	@EventHandler
	public void entitySpawn(EntitySpawnEvent e){
		if(e.getEntity() instanceof Monster || e.getEntity() instanceof Animals){
			e.setCancelled(true);
		}
	}
	
}
