package me.dodocarlos.kitpvp.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class Explosion implements Listener{

	@EventHandler
	public void explode(EntityExplodeEvent e){
		e.blockList().clear();
	}
	
}
