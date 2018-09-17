package me.dodocarlos.kitpvp.kits;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

import me.dodocarlos.kitpvp.utils.Arrays;
import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;

public class Lava implements Listener{
	
	public static ArrayList<String> lava = new ArrayList<> ();
	public static HashMap<String, Integer> timer = new HashMap<>();
	public static HashMap<String, Integer> segundos = new HashMap<>();
	
	public static void darKit(Player p){
		KitManager.preparePlayer(p);
		p.getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
		KitManager.darRecraft(p);
		lava.add(p.getName());
		Arrays.kitPlayer.put(p.getName(), "Lava");
		Methods.updatePvPScore(p);
	}
	
	@EventHandler
	public void damage(EntityDamageEvent e){
		if(e.getEntity() instanceof Player && e.getCause().equals(DamageCause.LAVA)){
			Player p = (Player) e.getEntity();
			if(lava.contains(p.getName())){
				if(!timer.containsKey(p.getName())){
					p.setLevel(0);
					int task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Vars.main, new Runnable(){
						public void run(){
							if(p.getLocation().getBlock().getType().equals(Material.LAVA) 
									|| p.getLocation().getBlock().getType().equals(Material.STATIONARY_LAVA)){
								if(!segundos.containsKey(p.getName())){
									segundos.put(p.getName(), 0);
								}
								segundos.put(p.getName(), segundos.get(p.getName()) + 1);
								p.setLevel(p.getLevel() + 1);
							}else{
								Bukkit.getScheduler().cancelTask(timer.get(p.getName()));
								timer.remove(p.getName());
								p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Voce sobreviveu " + Vars.infoColor 
										+ segundos.get(p.getName()) + Vars.defaultColor + " segundos!"));
								if(segundos.get(p.getName()) >= 120){
									Bukkit.broadcastMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "O jogador" + Vars.infoColor 
											+ p.getName() + Vars.defaultColor + " durou " + Vars.infoColor + segundos.get(p.getName())
											+ Vars.defaultColor + " segundos no lava challenge"));
								}
								segundos.remove(p.getName());
							}
						}
					}, 0l, 20l);
					timer.put(p.getName(), task);
				}
			}
		}
	}

	@EventHandler
	public void damage2(EntityDamageByEntityEvent e){
		if(e.getDamager() instanceof Player){
			Player damager = (Player) e.getDamager();
			if(lava.contains(damager.getName())){
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void death(PlayerDeathEvent e){
		if(lava.contains(e.getEntity().getName())){
			Bukkit.getScheduler().cancelTask(timer.get(e.getEntity().getName()));
			timer.remove(e.getEntity().getName());
			e.getEntity().sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Voce sobreviveu " + Vars.infoColor 
					+ segundos.get(e.getEntity().getName())  + Vars.defaultColor + " segundos!"));
			if(e.getEntity().getLevel() >= 120){
				Bukkit.broadcastMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "O jogador " + Vars.infoColor 
						+ e.getEntity().getName() + Vars.defaultColor + " durou " + Vars.infoColor + segundos.get(e.getEntity().getName())  
						+ Vars.defaultColor + " segundos no lava challenge"));
			}
			segundos.remove(e.getEntity().getName());
		}
	}
	
}
