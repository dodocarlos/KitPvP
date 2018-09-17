package me.dodocarlos.kitpvp.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import me.dodocarlos.kitpvp.utils.Vars;

public class Ping implements Listener{
	
	@EventHandler
	public void ping(ServerListPingEvent e){
		e.setMotd(Vars.Motd);
	}
	
}
