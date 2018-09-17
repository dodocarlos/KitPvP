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

public class Turtle implements Listener{

	public static ArrayList<String> turtle = new ArrayList<>();
	
	public static void darTurtle(Player p){
		KitManager.preparePlayer(p);
		p.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
		KitManager.darRecraft(p);
		turtle.add(p.getName());
		Arrays.kitPlayer.put(p.getName(), "Turtle");
		Methods.updatePvPScore(p);
		p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Voce escolheu o kit " + Vars.infoColor + "Turtle"));
		KitManager.tpToRandomLocation(p);
	}
	
	@EventHandler
	public void damage(EntityDamageByEntityEvent e){
		if(e.getEntity() instanceof Player){
			Player p = (Player) e.getEntity();
			if(turtle.contains(p.getName())){
				if(p.isSneaking()){
					e.setDamage(e.getDamage() / 5);
				}
			}
		}
		if(e.getDamager() instanceof Player){
			Player p = (Player) e.getDamager();
			if(turtle.contains(p.getName())){
				if(p.isSneaking()){
					e.setCancelled(true);
				}
			}
		}
	}
	
}
