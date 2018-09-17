package me.dodocarlos.kitpvp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.dodocarlos.kitpvp.utils.Arrays;
import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;

public class Respawn implements Listener{

	@EventHandler
	public void respawn(final PlayerRespawnEvent e){
		Methods.giveSpawnItems(e.getPlayer());
		Bukkit.getScheduler().scheduleSyncDelayedTask(Vars.main, new Runnable(){
			public void run(){
				e.getPlayer().teleport(Vars.spawn);
				if(!Arrays.spawnLocations.contains(e.getPlayer().getName())){
					e.getPlayer().sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Você ganhou a proteçao do spawn!"));
					Arrays.spawnProtection.add(e.getPlayer().getName());
				}
			}
		}, 5);
	}
	
}
