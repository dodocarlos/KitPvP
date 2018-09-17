package me.dodocarlos.kitpvp.kits;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import me.dodocarlos.kitpvp.utils.Arrays;
import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;

public class Boxer implements Listener{

	public static ArrayList<Player> boxer = new ArrayList<> ();
	
	public static void darBoxer(Player p){
		KitManager.preparePlayer(p);
		p.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
		KitManager.darRecraft(p);
		boxer.add(p);
		Arrays.kitPlayer.put(p.getName(), "Boxer");
		Methods.updatePvPScore(p);
		p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Voce escolheu o kit " + Vars.infoColor + "Boxer"));
		KitManager.tpToRandomLocation(p);
	}
	
	@EventHandler
	public void dano(EntityDamageByEntityEvent e){
		if(e.getEntity() instanceof Player){
			Player levou = (Player) e.getEntity();
			if(boxer.contains(levou)){
				e.setDamage(2D);
			}
		}
		if(e.getDamager() instanceof Player){
			Player bateu = (Player) e.getDamager();
			if(boxer.contains(bateu)){
				if(bateu.getItemInHand().getType() == Material.AIR || bateu.getItemInHand() == null){
					e.setDamage(5D);
				}
			}
		}
	}
	
}
