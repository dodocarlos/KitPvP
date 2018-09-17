package me.dodocarlos.kitpvp.kits;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.dodocarlos.kitpvp.utils.Arrays;
import me.dodocarlos.kitpvp.utils.Methods;
import me.dodocarlos.kitpvp.utils.Vars;

public class Viper implements Listener{
	
	Random r = new Random();
	
	public static ArrayList<Player> viper = new ArrayList<Player>();
	
	public static void darViper(Player p){
		KitManager.preparePlayer(p);
		p.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
		KitManager.darRecraft(p);
		viper.add(p);
		Arrays.kitPlayer.put(p.getName(), "Viper");
		Methods.updatePvPScore(p);
		p.sendMessage(Methods.toColoredString(Vars.tag + Vars.defaultColor + "Voce escolheu o kit " + Vars.infoColor + "Viper"));
		KitManager.tpToRandomLocation(p);
	}
	
	@EventHandler
	public void bater(EntityDamageByEntityEvent e){
			if(e.getEntity() instanceof Player){
				if(e.getDamager() instanceof Player){
					if(viper.contains(e.getDamager()) && !e.isCancelled()){
						if(r.nextInt(2) == 1){
							Player p = (Player) e.getEntity();
							p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20 * 7, 1));
						}
					}
				}
			}
	}
	
}
