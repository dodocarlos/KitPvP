package me.dodocarlos.kitpvp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.dodocarlos.kitpvp.utils.Methods;

public class Chat implements Listener{

	@SuppressWarnings("deprecation")
	@EventHandler
	public void chat(AsyncPlayerChatEvent e){
		e.setFormat(Methods.toColoredString(e.getPlayer().getScoreboard().getPlayerTeam(Bukkit.getOfflinePlayer(e.getPlayer().getName())).getPrefix() + e.getPlayer().getDisplayName() + " &a➜&f " + e.getMessage()));
	}
	
}
