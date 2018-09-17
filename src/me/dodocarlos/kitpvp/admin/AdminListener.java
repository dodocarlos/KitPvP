package me.dodocarlos.kitpvp.admin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import me.dodocarlos.kitpvp.utils.Arrays;

public class AdminListener implements Listener{

	@EventHandler
	public void interact(PlayerInteractEntityEvent e){
		if(Arrays.admin.contains(e.getPlayer().getName())){
			if(e.getRightClicked() instanceof Player){
				Player clicado = (Player) e.getRightClicked();
				Player p = e.getPlayer();
				p.openInventory(clicado.getInventory());
			}
		}
	}

	@EventHandler
	public void join(PlayerJoinEvent e){
		for(String pname : Arrays.admin){
			Player pl = Bukkit.getPlayer(pname);
			if(!e.getPlayer().hasPermission("cmd.admin")){
				e.getPlayer().hidePlayer(pl);
			}
		}
	}
	
}
