package me.dodocarlos.kitpvp.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class Hunger implements Listener{

	@EventHandler
	public void onFoodLevel(FoodLevelChangeEvent e){
		e.setCancelled(true);
	}
	
}
