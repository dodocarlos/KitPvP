package me.dodocarlos.kitpvp.kits;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

import me.dodocarlos.kitpvp.utils.Arrays;
import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;

public class Fisherman implements Listener{

	public static ArrayList<Player> fisherman = new ArrayList<>();
	
	public static void darFisherman(Player p){
		KitManager.preparePlayer(p);
		p.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
		p.getInventory().addItem(new ItemStack(Material.FISHING_ROD));
		KitManager.darRecraft(p);
		fisherman.add(p);
		Arrays.kitPlayer.put(p.getName(), "Fisherman");
		Methods.updatePvPScore(p);
		p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Voce escolheu o kit " + Vars.infoColor + "Fisherman"));
		KitManager.tpToRandomLocation(p);
	}
	
	@EventHandler
	public void pescar(PlayerFishEvent e){
		if(e.getCaught() instanceof Player){
			Player pescado = (Player) e.getCaught();
			Player p = e.getPlayer();
			pescado.teleport(p);
		}
	}
	
}
