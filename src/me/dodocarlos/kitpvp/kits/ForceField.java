package me.dodocarlos.kitpvp.kits;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.dodocarlos.kitpvp.utils.Arrays;
import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;

public class ForceField implements Listener{

	public static ArrayList<String> forcefield = new ArrayList<String> ();
	public static ArrayList<String> ffAtivo = new ArrayList<String>();
	public static HashMap<String, Long> cooldown = new HashMap<>();
	
	public static void darForceField(Player p){
		KitManager.preparePlayer(p);
		p.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
		p.getInventory().addItem(new ItemStack(Material.MAGMA_CREAM));
		KitManager.darRecraft(p);
		cooldown.put(p.getName(), 0l);
		forcefield.add(p.getName());		
		Arrays.kitPlayer.put(p.getName(), "Forcefield");
		Methods.updatePvPScore(p);
		p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Voce escolheu o kit " + Vars.infoColor + "Forcefield"));
		KitManager.tpToRandomLocation(p);
	}
	
	@EventHandler
	public void usarFF(final PlayerInteractEvent e){
		if(forcefield.contains(e.getPlayer().getName())){
			if(e.getItem() != null && e.getItem().getType() == Material.MAGMA_CREAM){
				if(Methods.acabouCooldown(e.getPlayer(), 120, cooldown)){					
						ffAtivo.add(e.getPlayer().getName());
						e.getPlayer().sendMessage(Vars.tag + Vars.defaultColor + "Voce ativou o " + Vars.infoColor + "Forcefield");
						Bukkit.getScheduler().scheduleSyncDelayedTask(Vars.main, new Runnable(){
							public void run(){
								ffAtivo.remove(e.getPlayer().getName());
							}
						}, 600l);
					}else{
						e.getPlayer().sendMessage(Vars.tag + Vars.defaultColor + "Aguarde o cooldown de " + Vars.infoColor + Methods.getRemainingCooldown(e.getPlayer(), 120, cooldown) + Vars.defaultColor + " segundos");
					}
		}
	}
	}
}
