package me.dodocarlos.kitpvp.kits;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;

import me.dodocarlos.kitpvp.utils.Arrays;
import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;

public class Ninja implements Listener{

	public static ArrayList<String> ninja = new ArrayList<String> ();
	public static HashMap<String, String> tpTo = new HashMap<>(); 
	public static HashMap<String, Long> cooldown = new HashMap<>(); 
	
	public static void darNinja(Player p){
		KitManager.preparePlayer(p);
		p.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
		KitManager.darRecraft(p);
		cooldown.put(p.getName(), 0l);
		ninja.add(p.getName());
		Arrays.kitPlayer.put(p.getName(), "Ninja");
		Methods.updatePvPScore(p);
		p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Voce escolheu o kit " + Vars.infoColor + "Ninja"));
		KitManager.tpToRandomLocation(p);
	}
	
	@EventHandler
	public void ninja(PlayerToggleSneakEvent e){
		if(!e.isSneaking()){
			if(ninja.contains(e.getPlayer().getName())){
				if(tpTo.containsKey(e.getPlayer().getName())){
					if(Methods.acabouCooldown(e.getPlayer(), 10, cooldown)){
						Player to = Bukkit.getPlayer(tpTo.get(e.getPlayer().getName()));
						if(to.isOnline() && e.getPlayer().getLocation().distance(to.getLocation()) <= 100){
							e.getPlayer().teleport(to.getLocation());
						}else{
							e.getPlayer().sendMessage(Vars.tag + Vars.defaultColor + "O jogador esta muito longe");
						}
					}else{
						e.getPlayer().sendMessage(Vars.tag + Vars.defaultColor + "Aguarde o cooldown de " + Vars.infoColor + Methods.getRemainingCooldown(e.getPlayer(), 10, cooldown) + Vars.defaultColor + " segundos");
					}
				}
			}
		}
	}
	
	@EventHandler
	public void hit(EntityDamageByEntityEvent e){
		if(e.getDamager() instanceof Player && e.getEntity() instanceof Player){
			Player p = (Player) e.getEntity();
			Player d = (Player) e.getDamager();
			if(ninja.contains(p.getName())){
				tpTo.put(p.getName(), d.getName());
			}
		}
	}
	
	@EventHandler
	public void death(PlayerDeathEvent e){
		if(e.getEntity().getKiller() instanceof Player){
			Player p = (Player) e.getEntity().getKiller();
			tpTo.remove(p.getName());
		}
	}
	
}
