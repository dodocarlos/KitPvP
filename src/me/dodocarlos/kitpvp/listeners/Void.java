package me.dodocarlos.kitpvp.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Void implements Listener{

	@EventHandler
	public static void voidDeath(PlayerMoveEvent e){
		if(e.getFrom().getY() <= -10){
			e.getPlayer().setHealth(0d);
		}
	}
	
}
