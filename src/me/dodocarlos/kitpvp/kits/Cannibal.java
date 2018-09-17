package me.dodocarlos.kitpvp.kits;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import me.dodocarlos.kitpvp.utils.Arrays;
import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;

public class Cannibal implements Listener{
	
	public static ArrayList<Player> cannibal = new ArrayList<Player>();
	
	public static HashMap<Player, Long> cooldown = new HashMap<>();
	
	public static void darCannibal(Player p){
		KitManager.preparePlayer(p);
		p.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
		KitManager.darRecraft(p);
		cannibal.add(p);
		cooldown.put(p, 0l);
		Arrays.kitPlayer.put(p.getName(), "Cannibal");
		Methods.updatePvPScore(p);
		p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Voce escolheu o kit " + Vars.infoColor + "Cannibal"));
		KitManager.tpToRandomLocation(p);
	}
	
	@EventHandler
	public void dano(EntityDamageByEntityEvent e){
		if(e.getEntity() instanceof Player){
			Player levou = (Player) e.getEntity();
			if(e.getDamager() instanceof Player){
				Player bateu = (Player) e.getDamager();
				if(cannibal.contains(bateu)){
					if(System.currentTimeMillis() - cooldown.get(bateu) >= 2 * 1000){
						if(bateu.getFoodLevel() < 20){
							bateu.setFoodLevel((int) (bateu.getFoodLevel() + e.getDamage()));
							levou.setFoodLevel((int) (bateu.getFoodLevel() - e.getDamage() / 2));
							cooldown.put(bateu, System.currentTimeMillis());
						}
					}
				}
			}
		}
	}
	
}
