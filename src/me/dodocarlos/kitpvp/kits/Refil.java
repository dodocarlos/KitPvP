package me.dodocarlos.kitpvp.kits;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.dodocarlos.kitpvp.utils.Arrays;
import me.dodocarlos.kitpvp.utils.Methods;

public class Refil implements Listener{
	
	public static ArrayList<String> refil = new ArrayList<> ();
	
	public static void darKit(Player p){
		KitManager.preparePlayer(p);
		KitManager.darRecraft(p);
		refil.add(p.getName());
		Arrays.kitPlayer.put(p.getName(), "Refil");
		Methods.updatePvPScore(p);
	}
	
	@EventHandler
	public void damage2(EntityDamageByEntityEvent e){
		if(e.getDamager() instanceof Player){
			Player damager = (Player) e.getDamager();
			if(refil.contains(damager.getName())){
				e.setCancelled(true);
			}
		}
	}
	
}
