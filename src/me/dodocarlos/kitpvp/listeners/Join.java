package me.dodocarlos.kitpvp.listeners;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.dodocarlos.kitpvp.kits.KitManager;
import me.dodocarlos.kitpvp.utils.Arrays;
import me.dodocarlos.kitpvp.utils.DailyKit;
import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;

public class Join implements Listener{
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		e.setJoinMessage(null);
		
		Arrays.playerTag.put(e.getPlayer().getName(), new String[]{"§7", "NORMAL"});
		
		if(Arrays.admin.contains(e.getPlayer().getName())){
			Arrays.admin.remove(e.getPlayer().getName());
		}
		
		e.getPlayer().getInventory().clear();
		e.getPlayer().getInventory().setArmorContents(null);
		e.getPlayer().setHealth(20d);
		e.getPlayer().setFoodLevel(20);
		e.getPlayer().setGameMode(GameMode.ADVENTURE);
		e.getPlayer().setFlying(false);
		e.getPlayer().setAllowFlight(false);		
		e.getPlayer().setLevel(0);
		e.getPlayer().setExp(0f);
		e.getPlayer().teleport(Vars.spawn);
		
		if(!Arrays.spawnProtection.contains(e.getPlayer().getName())){
			e.getPlayer().sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Você ganhou a proteçao do spawn!"));
			Arrays.spawnProtection.add(e.getPlayer().getName());
		}
		
		DailyKit.checkRemoveKit(e.getPlayer());
		
		KitManager.removeKit(e.getPlayer());
		
		Methods.giveSpawnItems(e.getPlayer());
		Methods.updatePvPScore(e.getPlayer());
		
	}
	
}
