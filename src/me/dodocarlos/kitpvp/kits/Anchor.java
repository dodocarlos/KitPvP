package me.dodocarlos.kitpvp.kits;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import me.dodocarlos.kitpvp.utils.Arrays;
import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;

public class Anchor implements Listener{

	public static ArrayList<Player> anchor = new ArrayList<Player> ();
	
	public static void darAnchor(Player p){
		KitManager.preparePlayer(p);
		p.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
		KitManager.darRecraft(p);
		anchor.add(p);
		Arrays.kitPlayer.put(p.getName(), "Anchor");
		Methods.updatePvPScore(p);
		p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Voce escolheu o kit " + Vars.infoColor + "Anchor"));
		KitManager.tpToRandomLocation(p);
	}
	
	@EventHandler
	public void dano(EntityDamageByEntityEvent e){
		if(e.getEntity() instanceof Player){
			final Player p = (Player) e.getEntity();
			if(anchor.contains(p)){
			      Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Vars.main, new Runnable()
			      {
			        public void run()
			        {
			          p.setVelocity(new Vector());
			        }
			      }, 1L);
			}	
		}
		
		if(e.getDamager() instanceof Player){
			Player bateu = (Player) e.getDamager();
			if(e.getEntity() instanceof Player){
				final Player p = (Player) e.getEntity();
				if(anchor.contains(bateu)){
				      Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Vars.main, new Runnable()
				      {
				        public void run()
				        {
				          p.setVelocity(new Vector());
				        }
				      }, 1L);
				}
			}
		}
	
	}
	
}
