package me.dodocarlos.kitpvp.kits;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.dodocarlos.kitpvp.utils.Arrays;
import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;

public class CheckPoint implements Listener {

	public static ArrayList<Player> checkpoint = new ArrayList<> ();
	public static HashMap<Player, Location> local = new HashMap<> ();
		
	public static void darCheckpoint(Player p ){
		KitManager.preparePlayer(p);
		p.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
		p.getInventory().addItem(new ItemStack(Material.SIGN));
		KitManager.darRecraft(p);		
		checkpoint.add(p);		
		Arrays.kitPlayer.put(p.getName(), "Checkpoint");
		Methods.updatePvPScore(p);
		p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Voce escolheu o kit " + Vars.infoColor + "CheckPoint"));
		KitManager.tpToRandomLocation(p);

	}
	
	@EventHandler
	public void interagir(PlayerInteractEvent e){
			Player p = e.getPlayer();
			if(checkpoint.contains(p)){
				if(e.getItem() != null){
					if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
						if(e.getItem().getType() == Material.SIGN){
							e.setCancelled(true);
							local.put(p, e.getClickedBlock().getLocation().clone().add(0, 2, 0));
							p.sendMessage(Vars.tag + Vars.defaultColor + "Checkpoint definido !");
							p.getItemInHand().setType(Material.WOOD_BUTTON);
							return;
						}
					}
					if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
						if(e.getItem().getType() == Material.WOOD_BUTTON){
							if(local.get(p) != null){
								e.setCancelled(true);
								p.teleport(local.get(p));
								p.sendMessage(Vars.tag + Vars.defaultColor + "Teletransportado para o local do checkpoint");
								local.remove(p);
								p.getItemInHand().setType(Material.SIGN);
								return;
							}
						}
					}
				}
		}
	}
	
}
