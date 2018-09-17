package me.dodocarlos.kitpvp.kits;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import me.dodocarlos.kitpvp.utils.Arrays;
import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;

public class Hulk implements Listener{

	public static ArrayList<String> hulk = new ArrayList<String> ();
	public static HashMap<String, Long> cooldown = new HashMap<String, Long> ();
	
	public static void darHulk(Player p){
		KitManager.preparePlayer(p);
		p.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
		KitManager.darRecraft(p);
		hulk.add(p.getName());
		Arrays.kitPlayer.put(p.getName(), "Hulk");
		Methods.updatePvPScore(p);
		p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Voce escolheu o kit " + Vars.infoColor + "Hulk"));
		KitManager.tpToRandomLocation(p);
	}
	
	@EventHandler
	public void interagir(PlayerInteractEntityEvent e){
		if(e.getRightClicked() instanceof Player && hulk.contains(e.getPlayer().getName())){
			Player p = e.getPlayer();
			Player clicado = (Player) e.getRightClicked();
			if(Methods.acabouCooldown(p, 3, cooldown)){
				p.setPassenger(clicado);
			}else{
				p.sendMessage(Vars.tag + Vars.defaultColor + "Aguarde o cooldown de " + Vars.infoColor + Methods.getRemainingCooldown(p, 3, cooldown) + Vars.defaultColor + " segundos");
			}
		}
	}
	
}
