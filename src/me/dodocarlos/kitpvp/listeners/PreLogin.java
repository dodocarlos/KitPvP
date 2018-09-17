package me.dodocarlos.kitpvp.listeners;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import me.dodocarlos.kitpvp.utils.Vars;

public class PreLogin implements Listener{

	@SuppressWarnings("deprecation")
	@EventHandler
	public void preLogin(PlayerLoginEvent e){
		if(e.getPlayer().hasPermission("dkitpvp.joinfull") && Bukkit.getOnlinePlayers().length >= Bukkit.getMaxPlayers()){
			Player p = Bukkit.getServer().getOnlinePlayers()[new Random().nextInt(Bukkit.getOnlinePlayers().length)];
			if(!p.hasPermission("dkitpvp.joinfull")){
				p.kickPlayer(Vars.tag + Vars.defaultColor + "Voce foi kickado por um vip! \nCompre vip em nosso site!");
			}
		}
	}
	
}
