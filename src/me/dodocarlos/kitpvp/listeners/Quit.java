package me.dodocarlos.kitpvp.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.dodocarlos.kitpvp.utils.Arrays;

public class Quit implements Listener{
	
	@EventHandler
	public void quit(PlayerQuitEvent e){
		e.setQuitMessage(null);
		
		if(Arrays.admin.contains(e.getPlayer().getName())){
			Arrays.admin.remove(e.getPlayer().getName());
		}
		
		if(Arrays.build.contains(e.getPlayer().getName())){
			Arrays.build.remove(e.getPlayer().getName());
		}
		
	}
	
}
