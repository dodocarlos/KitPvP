package me.dodocarlos.kitpvp.kits;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.dodocarlos.kitpvp.utils.Arrays;
import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;

public class Camel implements Listener{
		
	public static ArrayList<Player> camel = new ArrayList<Player> ();
	
	public static void darCamel(Player p){
		KitManager.preparePlayer(p);
		p.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
		KitManager.darRecraft(p);
		camel.add(p);
		Arrays.kitPlayer.put(p.getName(), "Camel");
		Methods.updatePvPScore(p);
		p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Voce escolheu o kit " + Vars.infoColor + "Camel"));
		KitManager.tpToRandomLocation(p);
	}
	
	
	@EventHandler
	public void andar(PlayerMoveEvent e){
		Player p = e.getPlayer();
		if(camel.contains(p)){
			if(e.getFrom().getBlock().getBiome() == Biome.DESERT){
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 1));
			}
		}
	}
	
}
