package me.dodocarlos.kitpvp.kits;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

import me.dodocarlos.kitpvp.utils.Arrays;
import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;

public class Fireman implements Listener{

	public static ArrayList<Player> fireman = new ArrayList<>();
	
	public static void darFireman(Player p){
		KitManager.preparePlayer(p);
		p.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
		KitManager.darRecraft(p);
		fireman.add(p);
		Arrays.kitPlayer.put(p.getName(), "Fireman");
		Methods.updatePvPScore(p);
		p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Voce escolheu o kit " + Vars.infoColor + "Fireman"));
		KitManager.tpToRandomLocation(p);
	}
	
	@EventHandler
	public void dano(EntityDamageEvent e){
		if(e.getEntity() instanceof Player){
			Player p = (Player) e.getEntity();
			if(e.getCause() == DamageCause.FIRE
				|| e.getCause() == DamageCause.FIRE_TICK
				|| e.getCause() == DamageCause.LIGHTNING){
					if(fireman.contains(p)){
						e.setCancelled(true);
					}
				}
		}
	}
	
}
